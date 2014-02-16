/*
 * TurtleKit - An Artificial Life Simulation Platform
 * Copyright (C) 2000-2010 Fabien Michel, Gregory Beurier
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package civilisation.individu;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import civilisation.Civilisation;
import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.Amenagement_Route;
import civilisation.individu.cognitons.NCogniton;
import civilisation.inventaire.NInventaire;
import civilisation.inventaire.Objet;
import civilisation.pathfinder.Noeud;
import civilisation.urbanisme.Batiment;
import civilisation.urbanisme.Batiment_Hutte;
import civilisation.world.World;
import turtlekit.kernel.Turtle;
import turtlekit.kernel.Patch;


/** 
 * Classe d'agents principale
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

@SuppressWarnings("serial")
public class Humain extends Turtle 
{
	
	int visionRadius;
	Civilisation civ;
	Communaute communaute;
	ArrayList<Batiment> batiments;
	ArrayList<Amenagement> amenagements;
	NInventaire inventaire;

	
	Humain pere;
	Humain mere;
	Humain conjoint;
	Boolean woman;
	ArrayList<Humain> enfants;
	
	
	HashMap<String,Integer> attributes;

	/* For map drawing */
	public boolean isSelected = false;
	public boolean isShowGroup = false;
	public boolean isDie = false;
	
	Esprit esprit; //Mind controlling decision making of the agent
		
	ArrayList<Patch> chemin;
	int tempsPatch;

	public Humain(Civilisation civ , Communaute communaute)
	{
		super("penser");
		pere = null;
		mere = null;
		initialisation(civ, communaute);
		chemin = new ArrayList<Patch>();
		tempsPatch = 2;
	}
	
	/**
	 * Constructeur si l'agent ������������ des parents
	 */
	public Humain(Civilisation civ , Communaute communaute , Humain pere , Humain mere)
	{
		super("penser");
		this.pere = pere;
		this.mere = mere;
		initialisation(civ, communaute);
		chemin = new ArrayList<Patch>();
		tempsPatch = 2;
	}
	
	public void die()
	{
		
		//System.out.println("------------------------------------------------");
		//System.out.println("Un agent est mort au cours du projet : " + this.getEsprit().getPlanEnCours());
		//System.out.println("Vie : " + this.getVie());
		//if(this.chemin != null) System.out.println("Chemin : " + this.chemin);
		//System.out.println("Inventaire" + this.inventaire);
		
		//Utils.afficherTrace();
		//System.out.println("-------------------******-----------------------");
		
		//this.getEsprit().clearAllCognitons();   /*TODO*/
		isDie = true; //TODO : verifier

	}
	

	/**
	 * Standard agent initialization
	 */
	private void initialisation(Civilisation civ, Communaute communaute)
	{
		this.civ = civ;
		inventaire = new NInventaire();
		esprit = new Esprit(this);
		
		this.communaute = communaute;
		batiments = new ArrayList<Batiment>();
		amenagements = new ArrayList<Amenagement>();
		enfants = new ArrayList<Humain>();

		attributes = new HashMap<String,Integer>();
		for (int i = 0; i < Configuration.attributesNames.size(); i++){
			attributes.put(Configuration.attributesNames.get(i), Configuration.attributesStartingValues.get(i));
		}

	}


	/**
	 * Fonction qui centralise le choix des actions de l'agent 
	 * @return task to do
	 */
	public String penser()
	{
		if (isDie) {
			return null;
		} else {
			esprit.penser();
		}
		return "penser";
	}

	@Override
	public void activate()
	{
		super.activate();
		this.moveTo(communaute.getPatch().x, communaute.getPatch().y);
		setColor(civ.getCouleur());
		playRole("Humain");
	} 

/**
 * L'agent rentre chez lui
 */
	public void rentrer()
	{

		this.allerVers(this.communaute);
		
	}
	
	
		public void allerVers(Turtle but)
		{
				//double head = this.getHeading()%360;
				if(this.chemin.isEmpty())
				{
					
					face(but);
					this.chemin.addAll(this.AllerVers(but.getPatch()));
					/*for(int i = 0; i < this.chemin.size();i++)
					{
						this.chemin.get(i).setColor(Color.white);
					}*/
				}
				
				if(chemin != null && !chemin.isEmpty())
				{
					System.out.println("My position : " + this.getPatch() + " " + chemin);
					Patch cible = this.chemin.get(0);
					this.face(cible);
				}
				/*else
				{
					this.setHeading(this.PatchPlusVisiteAutour());
					if(this.getHeading() == head)
					{
						this.setHeading((this.getHeading() + 180)%360);
					}
				}*/
				this.fd(1);
				if(this.chemin != null && !this.chemin.isEmpty() && this.chemin.get(0) != null )
				{
					this.chemin.remove(0);
				}
				emit("passage", 1.0f);
				if(this.smell("passage") > Configuration.passagesPourCreerRoute && !this.getPatch().isMarkPresent("Route"))
				{
					Amenagement_Route troncon = new Amenagement_Route(this.getPatch());
					//this.addAmenagement(troncon);  /*TODO : adapter les amenagements*/
					this.getPatch().dropMark("Route", troncon);
				}
		}
	
	/**
	 * L'agent construit une hutte (pour les tests)
	 */
	public void construireHutte()
	{
		communaute.construire(new Batiment_Hutte(this, communaute));
	}
	
	
	/*------------------------------------------------------------------------------*/
	
	
	/**
	 * L'agent avance ou tourne si un obstacle se presente
	 */
	public void move(int i)
	{
		while (getNextPatch().getColor().equals(World.getColorOcean()) || getNextPatch().getColor().equals(World.getColorRivieres()))	
		{
			randomHeading();
		}
		fd(i);
	}
	
	public void fd(double i)
	{

			Color couleur = this.getPatchColor();
			int TempsAPasser = Configuration.couleurs_terrains.get(couleur).getPassabilite();
			TempsAPasser /= 10;
			if(this.tempsPatch > TempsAPasser)
			{
				this.tempsPatch--;
				Color coul = this.getNextPatch().getColor();
				//On va modifier pour que ������a colle avec les nouveaux terrains
				if(!Configuration.couleurs_terrains.get(couleur).getInfranchissable())
				{
					super.fd(1);
					//emit("passage", 1.0);
					if(this.smell("passage") > Configuration.passagesPourCreerRoute && !this.isMarkPresent("Route"))
					{
						Amenagement_Route troncon = new Amenagement_Route(this.getPatch());
						//this.addAmenagement(troncon);  /*TODO : adapter les amenagements*/
						this.getPatch().dropMark("Route", troncon);
					}		
					if(!Configuration.couleurs_terrains.get(coul).getInfranchissable())
					{
						this.fd(1);
					}
					
				}
				else
				{
					this.randomHeading();
					super.fd(1);
					//emit("passage", 1.0);
					if(this.smell("passage") > Configuration.passagesPourCreerRoute && !this.isMarkPresent("Route"))
					{
						Amenagement_Route troncon = new Amenagement_Route(this.getPatch());
						//this.addAmenagement(troncon);  /*TODO : adapter les amenagements*/
						this.getPatch().dropMark("Route", troncon);
					}						
				}
				
				
			}
			if(this.tempsPatch < TempsAPasser)
			{
				this.tempsPatch++;
			}
			if(this.tempsPatch == TempsAPasser)
			{
				
				//TODO TK3 : voir si le comportement du getNextPatch est correct
				Color coul = this.getNextPatch().getColor();
				//TODO : On va modifier pour que ������a colle avec les nouveaux terrains
				if(!Configuration.couleurs_terrains.get(coul).getInfranchissable())
				{
					super.fd(1);
					this.getPheromone("passage").incValue(xcor(), ycor(), 1.0f);
					if(this.smell("passage") > Configuration.passagesPourCreerRoute && !this.isMarkPresent("Route"))
					{
						Amenagement_Route troncon = new Amenagement_Route(this.getPatch());
						//this.addAmenagement(troncon);  /*TODO : adapter les amenagements*/
						this.getPatch().dropMark("Route", troncon);
					}	
					
				}
				else
				{
					this.randomHeading();
					this.fd(1);
					
				}
				this.tempsPatch = 2;
			}
			
	}
	
	/**
	 * Change la direction de l'agent afin qu'il se positionne en dans la direction de l'agent cible 
	 * @param cible
	 */
	public void face(Turtle cible)
	{
		double direction = towards(cible.xcor(),cible.ycor());
		this.setHeading(direction);
	}
	
	/**
	 * Change la direction de l'agent afin qu'il se positionne en dans la direction du patch cible 
	 * @param cible
	 * 
	 */
	public void face(Patch cible)
	{
		double direction = towards(cible.x,cible.y);
		this.setHeading(direction);
	}
	
	/**
	 * @param tortues
	 * @return la turtle la plus eloign��������������������������������������� de la tortue appelante
	 */
	@SuppressWarnings("null")
	public Turtle MaxOneOf(ArrayList<Turtle> tortues)
	{
		ArrayList<Turtle> choix = new ArrayList<Turtle>();
		double min = 0;
		for(int i = 0;i<0;i++)
		{
			int x = tortues.get(i).xcor();
			int y = tortues.get(i).ycor();
			if(this.distance(x, y) > min)
			{
				min = this.distance(x, y);
				choix.clear();
				choix.add(tortues.get(i));
			}
			if(this.distance(x, y) == min)
			{
				choix.add(tortues.get(i));
			}
		}
		return this.oneOf(choix);
	}
	
	/**
	 * @param ArrayList<Turtle>
	 * @return une tortue au hasard parmis les tortues en parametre
	 */
	public Turtle oneOf(ArrayList<Turtle> tortues)
	{
		if(tortues == null || tortues.size() == 0) return null;
		else return tortues.get((int)Math.random()*(tortues.size()-1));
	}
	

	
	/**
	 * @param Turtle[]
	 * @return une tortue au hasard parmis les tortues en parametre
	 */
	public Turtle oneOf(Turtle[] tortues)
	{
		if(tortues == null || tortues.length == 0) return null;
		else return tortues[(int)Math.random()*(tortues.length-1)];
	}
	
	/**
	 * 
	 * @return un agent au hasard qui est sur le meme patch que this
	 */	
	public Humain oneOfHumanHere()
	{
		Turtle[] cibles = turtlesHere();
		ArrayList<Turtle> choix = new ArrayList<Turtle>();
		for(int i = 0;i<cibles.length;i++)
		{
			if(cibles[i].isPlayingRole("Humain")) choix.add(cibles[i]);
		}
		
		if(!choix.isEmpty()) return (Humain) oneOf(choix);
		else return null;
	}
	
	/**
	 * 
	 * @param tortues
	 * @return la tortue la plus proche de lui
	 */
	public Turtle MinOneOf(ArrayList<Turtle> tortues)
	{
		double min = 100000000;
		int imin = 0;
		for(int i = 0;i<tortues.size();i++)
		{
			int xt = tortues.get(i).xcor();
			int yt = tortues.get(i).ycor();
			if(this.distance(xt, yt) < min)
			{
				min = this.distance(xt, yt);
				imin = i;
			}
		}
		if(tortues.isEmpty())
		{
			return null;
		}
		else
		{
			return tortues.get(imin);
		}
		
	}
	
	/**
	 * @param tortues
	 * @return la communaute la plus proche de lui
	 */
	public Communaute MinOneOfOtherCommunaute(ArrayList<Turtle> tortues)
	{
		double min = 100000000;
		int imin = 0;
		for(int i = 0;i<tortues.size();i++)
		{
			if (  tortues.get(i).isPlayingRole("Communaute")   &&   !tortues.get(i).equals(communaute))
			{
				int xt = tortues.get(i).xcor();
				int yt = tortues.get(i).ycor();
				if(this.distance(xt, yt) < min)
				{
					min = this.distance(xt, yt);
					imin = i;
				}
			}
		}
		if(tortues.isEmpty()){
			return null;
		}
		else{
			return (Communaute) tortues.get(imin);
		}	
	}
	
	
	/**
	 * @param tortues
	 * @return la communaute appartenant a une autre civilisation que "civ" la plus proche de l'humain considere
	 */
	public Communaute MinOneOfOtherCommunauteOfOtherCiv(ArrayList<Turtle> tortues , Civilisation civ)
	{
		double min = 100000000;
		int imin = 0;
		for(int i = 0;i<tortues.size();i++)
		{
			if (  tortues.get(i).isPlayingRole("Communaute")   &&   !tortues.get(i).equals(communaute)  && ((Communaute) tortues.get(i)).getCiv() != civ)
			{
				int xt = tortues.get(i).xcor();
				int yt = tortues.get(i).ycor();
				if(this.distance(xt, yt) < min)
				{
					min = this.distance(xt, yt);
					imin = i;
				}
			}
		}
		if(tortues.isEmpty()){
			return null;
		}
		else{
			return (Communaute) tortues.get(imin);
		}	
	}
	
	/**
	 * 
	 * @param radius
	 * @return les tortues a une distance radius de lui
	 */
	public ArrayList<Turtle> inRadius(int radius)
	{
		ArrayList<Turtle> tortues = new ArrayList<Turtle>();
		for(int i = 0;i< radius * 2; i++)
		{
			for(int j = 0;j< radius * 2; j++)
			{
				if(this.distance(this.xcor() + i - radius, this.ycor() + j - radius) < radius)
				{
					List<Turtle> torts = this.getPatchAt(i - radius, j - radius).getTurtles();
					for(int k = 0;k< torts.size();k++)
					{
						tortues.add(torts.get(k));
					}					
				}
			}
		}
		return tortues;
	}
	

	@SuppressWarnings("null")
	public ArrayList<Turtle> TurtlesWithRole(String role)
	{
		Turtle[] tortues = (Turtle[]) World.getInstance().getTurtlesWithRoles(role).toArray();
		ArrayList<Turtle> choix = new ArrayList<Turtle>();
		for(int i = 0;i < tortues.length;i++)
		{
			choix.add(tortues[i]);
		}
		return choix;
	}
	
	
	

	@SuppressWarnings({ "null", "unused" })
	public Turtle OneOfTurtlesHereWithRole(String role)
	{
		Turtle[] cibles = this.turtlesHere();
		ArrayList<Turtle> choix = new ArrayList<Turtle>();
		for(int i = 0;i<cibles.length;i++)
		{
			if(cibles[i].isPlayingRole(role))
			{
				choix.add(cibles[i]);
			}
		}
		if(!choix.isEmpty())
		{
			return this.oneOf(choix);
		}
		else
		{
			return null;
		}
		
	}
	
/**
 * Retourne une tortue pr���������������sente sur le m������������������me patch dont le crit������������������re pass��������������� en param������������������tre correspond
 * @param m : La m���������������thode a utiliser pour effectuer la comparaison
 * @param params : les param������������������tres dont la m���������������thode a besoin
 * @param o : la valeur souhait���������������e pour la compraison
 * @return
 * @throws IllegalArgumentException
 * @throws IllegalAccessException
 * @throws InvocationTargetException
 */
	
	@SuppressWarnings({ "null", "unused" })
	public Turtle OneOfHumanHereWith(Method m, Object[] params, Object o) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Turtle[] cibles = this.turtlesHere();
		ArrayList<Turtle> choix = new ArrayList<Turtle>();
		for(int i = 0;i<cibles.length;i++)
		{
			if (cibles[i].isPlayingRole("Humain"))
			{
				if( m.invoke((Humain) cibles[i], params).equals(o))
				{
					choix.add(cibles[i]);
				}
			}
		}
		if(!choix.isEmpty())
		{
			return this.oneOf(choix);
		}
		else
		{		
			return null;
		}
		
	}
	
	
	/**
	 * 
	 * @param cible
	 * @return vrai si la tortue cible est sur le meme patch que la tortue appelante
	 */
	public boolean isHere(Turtle cible)
	{
		Turtle[] tortuesIci = (Turtle[]) this.getOtherTurtles(0, true).toArray(); //TODO to check
		for(int i = 0;i < tortuesIci.length;i++)
		{
			if(tortuesIci[i].equals(cible))
			{
				return true;
			}
		}
		return false;
		
	}

	/**

	 * @param patchVariable
	 * @param inRadius the highest distance from the agent which should be considered
	 * @return the patch which has the highest value for <code>patchVariable</code>
	 */
	public Patch getPatchWithMaxOf(String patchVariable,int inRadius){
    	double max = -Double.MAX_VALUE;
    	ArrayList<Patch> p = new ArrayList<Patch>();
    	
    	/*On cherche le max*/
		for (int i = -inRadius; i <= inRadius ; i++) {
			for (int j = -inRadius; j <= inRadius ; j++) {
				if (! (i == 0 && j == 0) && this.isInsideEnvironmentBounds(i, j)) {
					final Patch tmpP = getPatchAt( i, j);
					//System.out.println(patchVariable);
					//System.out.println(tmpP.x +" " +tmpP.y);
					final double tmp = this.smellAt(patchVariable,i,j);
					if (tmp >= max) {
						max = tmp;
					}
				}
			}			
		}
		/*On conserve tous les patch dont la valeur est le max*/
		for (int i = -inRadius; i <= inRadius ; i++) {
			for (int j = -inRadius; j <= inRadius ; j++) {
				if (! (i == 0 && j == 0) && this.isInsideEnvironmentBounds(i, j)) {
					Patch tmpP = getPatchAt(i, j);
					double tmp = this.smellAt(patchVariable,i,j);
					if (tmp == max) {
						p.add(tmpP);
					}
				}
			}
		}
		int index = (int) (Math.floor(Math.random()*(p.size())));
	/*	if (this.isInsideEnvironmentBounds(1,1) && p.get(index) == getPatchAt(1,1)) {
			System.out.println("11");
		}
		if (this.isInsideEnvironmentBounds(1,1) && p.get(index) == getPatchAt(1,-1)) {
			System.out.println("1-1");
		}*/
		return p.get(index);
	}
	
	/**
	 * Retourne un amenagement dont le nom est pass��������������� en param������������������tre
	 * @param type : nom de l'am���������������nagement
	 * @return
	 */
	public Amenagement getOneOfAmenagement(String type)
	{
		ArrayList<Amenagement> selection = new ArrayList<Amenagement>();
		for (int i = 0; i < amenagements.size(); i++)
		{
			if (amenagements.get(i).getNom().equals(type))
			{
				selection.add(amenagements.get(i));
			}
		}
		
		if (selection.size() > 0)   return (selection.get((int) (Math.random()*(selection.size()))));
		return null;
	}
	
	/***
	 * 
	 * @param objet
	 * @param liste
	 * @return true si l'objet est inclus dans la liste
	 */
	
	public boolean inclus(Object objet , ArrayList<?> liste)
	{
		int i = 0;
		while(i < liste.size() && !liste.get(i).equals(objet))
		{
			i++;
		}
		return i < liste.size();
	}
	
	/**
	 * 
	 * @param cible
	 * @return true si l'agent peut se deplacer sur le Patch cible
	 */
	
	/*public boolean PasObstacle(Patch cible)
	{
		return !inclus(cible.color,this.civ.getInaccesibles()) ;
	}*/
	
	
	private double distanceBetween(Patch a, Patch b)
	{
		return Math.sqrt( (b.x - a.x)*(b.x - a.x) + (b.y - a.y)*(b.y -a.y) );
	}
	
	private ArrayList<Patch> plusProche(ArrayList<Patch> test1, ArrayList<Patch> test2, ArrayList<Patch> test3, ArrayList<Patch> test4) {
		// TODO Auto-generated method stub
		double dis1;
		double dis2;
		double dis3;
		double dis4;
		if(test1 == null)
		{
			dis1 = 1000000000;
		}
		else
		{
			dis1 = this.distance(test1.get(0).x,test1.get(0).y);
			for(int i = 1;i<test1.size();i++)
			{
				dis1 += this.distanceBetween(test1.get(i-1), test1.get(i));
			}
		}
		if(test2 == null)
		{
			dis2 = 1000000000;
		}
		else
		{
			dis2 = this.distance(test2.get(0).x,test2.get(0).y);
			for(int i = 1;i<test2.size();i++)
			{
				dis2 += this.distanceBetween(test2.get(i-1), test2.get(i));
			}
		}
		if(test3 == null)
		{
			dis3 = 1000000000;
		}
		else
		{
			dis3 = this.distance(test3.get(0).x,test3.get(0).y);
			for(int i = 1;i<test3.size();i++)
			{
				dis3 += this.distanceBetween(test3.get(i-1), test3.get(i));
			}
		}
		if(test4 == null)
		{
			dis4 = 1000000000;
		}
		else
		{
			dis4 = this.distance(test4.get(0).x,test4.get(0).y);
			for(int i = 1;i<test4.size();i++)
			{
				dis4 += this.distanceBetween(test4.get(i-1), test4.get(i));
			}
		}
		if(test1 == null && test2 == null && test3 == null && test4 == null)
		{
			return null;
		}
		else
		{
			if(dis1 < dis2)
			{
				if(dis3 < dis4)
				{
					if(dis1 < dis3)
					{
						return test1;
					}
					else
					{
						return test3;
					}
				}
				else
				{
					if(dis1 < dis4)
					{
						return test1;
					}
					else
					{
						return test4;
					}
				}
			}
			else
			{
				if(dis3 < dis4)
				{
					if(dis2 < dis3)
					{
						return test2;
					}
					else
					{
						return test3;
					}
				}
				else
				{
					if(dis2 < dis4)
					{
						return test2;
					}
					else
					{
						return test4;
					}
				}
			}
		}
	}

	public ArrayList<Patch> AStar(Patch cible)
	{
		//System.out.println("ASTAR :" + cible + this.getPatch());
		int[][] map = new int[this.getWorldWidth()][this.getWorldHeight()];
		int minx = Math.min(cible.x, this.xcor());
		int maxx = Math.max(cible.x, this.xcor());
		int miny = Math.min(cible.y, this.ycor());
		int maxy = Math.max(cible.y, this.ycor());
		
		for(int i = minx - 10;i< maxx + 10;i++)
		{
			for(int j = miny - 10; j < maxy + 10 ; j++)
			{
				map[i][j] = Configuration.VitesseEstimeeParDefaut;
			}
		}
		
		for(int i = 0; i < this.visionRadius * 2 ; i++)
		{
			for(int j = 0; j < this.visionRadius * 2 ; j++)
			{
				Color couleur = this.getPatchAt(i - this.visionRadius, j - this.visionRadius).getColor();
				
				int passabilite = Configuration.couleurs_terrains.get(this.getPatchAt(i - this.visionRadius, j - this.visionRadius).getColor()).getPassabilite();
				map[this.xcor() + i - this.visionRadius][this.ycor()+j - this.visionRadius] = (int) (passabilite - (passabilite/2*1/this.smellAt("passage", i - this.visionRadius, j - this.visionRadius)));
							 
				if(this.getPatchAt(i - this.visionRadius, j - this.visionRadius).isMarkPresent("route"))
				{
					map[this.xcor() + i - this.visionRadius][this.ycor()+j - this.visionRadius] /= 10;
				}
					
			}
		}
		/*for(int i = 0; i < map.length; i++)
		{
			System.out.print("[");
			for(int j = 0;j < map[i].length;j++)
			{
				if(map[i][j] != 1000)
				{
					System.out.print(map[i][j]);
				}
			}
			System.out.println("]");
		}*/
		ArrayList<Noeud> open_list = new ArrayList<Noeud>();
		ArrayList<Noeud> close_list = new ArrayList<Noeud>();
		Noeud noeud = new Noeud(this.getPatch().x,this.getPatch().y,0,0);
		noeud.setDistanceRacine(0);
		close_list.add(noeud);
		int cpt = 1;
		for(int i = -1; i < 2;i++)
		{
			for(int j = -1;j < 2 ; j++)
			{
				int x = noeud.getPosX();
				int y = noeud.getPosY();
				if( (x+i < this.getWorldWidth() && x+i > 0) && (y+i < this.getWorldHeight() && y+i > 0) && (i!= 0 || j != 0) && map[x+i][y+i] != 10000 )
				{
					Noeud noeu = new Noeud(x+i,y+i,0,cpt);
					int distanceRacine = map[x+i][y+i];
					noeu.setDistanceRacine(distanceRacine);
					open_list.add(noeu);
					cpt++;
				}
			}
		}
		Noeud suivant = this.PlusProcheNoeud(open_list, cible);
		if(suivant != null)
		{
			if(suivant.getParent() != noeud.getId())
			{
				
				for(int i = 0; i< close_list.size();i++)
				{
					if(close_list.get(i).getId() > suivant.getParent())
					{
						close_list.remove(i);
					}
				}
				
			}
			close_list.add(suivant);
		}
		noeud = suivant;
		while(noeud != null && (noeud.getPosX() != cible.x || noeud.getPosY() != cible.y) )
		{
			open_list.remove(noeud);
			for(int i = -1; i < 2;i++)
			{
				for(int j = -1;j < 2 ; j++)
				{
					int x = noeud.getPosX();
					int y = noeud.getPosY();
					if( (x+i < this.getWorldWidth() && x+i > 0) && (y+i < this.getWorldHeight() && y+i > 0) && (i!= 0 || j != 0) && map[x+i][y+i] != 10000)
					{
						Noeud noeu = new Noeud(x+i,y+i,noeud.getId(),cpt);
						if(! doublons(open_list,noeud) && !doublons(close_list,noeud))
						{
							int distanceRacine = map[x+i][y+i] + noeud.getDistanceRacine();
							noeu.setDistanceRacine(distanceRacine);
							open_list.add(noeu);
							cpt++;
						}	
					}
				}
			}
			suivant = this.PlusProcheNoeud(open_list, cible);
			if(suivant != null)
			{
				if(suivant.getParent() != noeud.getId())
				{
					
					for(int i = 0; i< close_list.size();i++)
					{
						if(close_list.get(i).getId() > suivant.getParent())
						{
							close_list.remove(i);
						}
					}
					
				}
				close_list.add(suivant);
			}
			noeud = suivant;
		}
		
		
		
		
		ArrayList<Patch> liste = new ArrayList<Patch>();
		for(int i = 0;i < close_list.size();i++)
		{
			int x = close_list.get(i).getPosX();
			int y = close_list.get(i).getPosY();
			if(map[x][y] >= Configuration.VitesseEstimeeParDefaut)
			{
				//System.out.println("ASTARLISTE" + liste);
				return liste;
			}
			else
			{
				liste.add(0,this.getPatchAt(x, y));
			}
		}
		//System.out.println("ASTARLISTE" + liste);
		return liste;
	}
	
	public boolean doublons(ArrayList<Noeud> liste, Noeud noeud )
	{
		for(int i = 0; i < liste.size(); i++)
		{
			if(liste.get(i).getPosX() == noeud.getPosX() && liste.get(i).getPosY() == noeud.getPosY())
			{
				return true;
			}
		}
		return false;
	}
	
	public Noeud PlusProcheNoeud(ArrayList<Noeud> liste , Patch cible)
	{
		Noeud court = null;
		double dist = 10000000;
		double distanceRacine = 0;
		for(int i = 0; i < liste.size(); i++)
		{
			int x = liste.get(i).getPosX();
			int y = liste.get(i).getPosY();
			Patch courant = this.getPatchAt(x - this.xcor(), y - this.ycor());
			distanceRacine = 10 * Math.max(Math.abs(cible.x - x), Math.abs(cible.y - y));
			double distance = liste.get(i).getDistanceRacine() + distanceRacine;
			if(distance < dist)
			{
				dist = distance;
				court = liste.get(i);
			}
		}
		return court;
	}
	/**
	 * 
	 * @param current le patch visit���������������
	 * @param visites le tableau des patchs visit���������������s ou non
	 * @return true si il n'y a aucun patch non visit��������������� autour de current
	 */
	private boolean AucunPatchNonVisiteAutour(Patch current, int[][] visites) {
		// TODO Auto-generated method stub
		Patch test1 = this.getPatchAt(current.x - this.xcor() + 1, current.y - this.ycor());
		Patch test2 = this.getPatchAt(current.x - this.xcor(), current.y - this.ycor()+ 1);
		Patch test3 = this.getPatchAt(current.x - this.xcor()- 1, current.y - this.ycor());
		Patch test4 = this.getPatchAt(current.x - this.xcor() ,current.y - this.ycor() - 1);
		boolean test = true;
		if(visites[test1.x][test1.y] == 0 /*&& this.inclus(test1.color, this.civ.getInaccesibles())*/)
		{
			test = false;
		}
		if(visites[test2.x][test2.y] == 0 /*&& this.inclus(test2.color, this.civ.getInaccesibles())*/)
		{
			test = false;
		}
		if(visites[test3.x][test3.y] == 0 /*&& this.inclus(test3.color, this.civ.getInaccesibles())*/)
		{
			test = false;
		}
		if(visites[test4.x][test4.y] == 0 /*&& this.inclus(test4.color, this.civ.getInaccesibles())*/)
		{
			test = false;
		}
		return test;
	}

	/**
	 * 
	 * @param cible
	 * @return le Patch raprochant le plus l'agent de sa cible, compte tenu des obstacles
	 * l'agent sera amen��������������� a faire des choix qui n'optimiseront pas toujours sa decison, la 
	 * faute a un manque d'information inherant a l'agent
	 */
	public ArrayList<Patch> AllerVers(Patch cible)
	{
		return AStar(cible);	
	}
	

	/**
	 * 
	 * @param radius
	 * @return les tortues a une distance radius de lui
	 */
	public ArrayList<Humain> HumaininRadius(int radius)
	{
		ArrayList<Humain> tortues = new ArrayList<Humain>();
		for(int i = 0;i< radius * 2; i++)
		{
			for(int j = 0;j< radius * 2; j++)
			{
				if(this.distance(this.getX() + i - radius, this.getY() + j - radius) < radius)
				{
					List <Turtle> torts = this.getPatchAt(i - radius, j - radius).getTurtles();
					for(int k = 0;k< torts.size();k++)
					{
						if(torts.get(k).isPlayingRole("Humain"))
						{
							tortues.add((Humain)torts.get(k));
						}
						
					}					
				}
			}
		}
		return tortues;
	}
	
	public ArrayList<Communaute> CommunauteInRadius(int radius)
	{
		ArrayList<Communaute> tortues = new ArrayList<Communaute>();
		for(int i = 0;i< radius * 2; i++)
		{
			for(int j = 0;j< radius * 2; j++)
			{
				if(this.distance(this.getPatch().x + i - radius, this.getPatch().y + j - radius) < radius)
				{
					Turtle[] torts = (Turtle[])this.getPatchAt(i - radius, j - radius).getTurtles().toArray();
					for(int k = 0;k< torts.length;k++)
					{
						if(torts[k].isPlayingRole("Communaute"))
						{
							tortues.add((Communaute)torts[k]);
						}
						
					}					
				}
			}
		}
		return tortues;
	}

	public ArrayList<Humain> humainsFromCiv(ArrayList<Humain> humains, Civilisation civ)
	{
		ArrayList<Humain> humainsTries = new ArrayList<Humain>();
		for(int i = 0;i< humains.size(); i++)
		{
			if (humains.get(i).getCiv().equals(civ)){
				humainsTries.add(humains.get(i));
			}
		}
		return humainsTries;
	}
	
	
	/**
	 * @param tortues
	 * @return une tortue au hasard parmis les tortues en parametre
	 */
	public Humain oneOfHumain(ArrayList<Humain> tortues)
	{
		return tortues.get((int)Math.random()*(tortues.size()-1));
	}	
	
	/**
	 * Deplace les biens de l'agent dans une nouvelle communaute.
	 * Le transfert est simplifie au maximum, car simuler le detail de cette migration dans la simulation ne nous semble pas pertinent.
	 * @param nouvelleCommunaute : la communaute d'accueil
	 */
	public void migrer(Communaute nouvelleCommunaute){
		for (int i = 0; i < batiments.size(); i++){
			batiments.get(i).setCommunaute(nouvelleCommunaute);
			communaute.retirerBatiment(batiments.get(i));
		}
		communaute = nouvelleCommunaute;

	}
	
	public Object getMark(String s) {
		return this.getPatch().getMark(s);
	}
	
	public boolean isMarkPresent(String s) {
		return this.getPatch().isMarkPresent(s);
	}
	
	public Turtle[] turtlesHere() {
		return (Turtle[]) this.getOtherTurtles(0, true).toArray();
	}
	
	//----------------GETTERS/SETTERS-------------------
	
	public ArrayList<Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(ArrayList<Batiment> batiments) {
		this.batiments = batiments;
	}

	public Boolean isWoman() {
		return woman;
	}

	public void setWoman(Boolean woman) {
		this.woman = woman;
	}

	public int getVisionRadius() {
		return visionRadius;
	}

	public Civilisation getCiv() {
		return civ;
	}

	public NInventaire getInventaire(){
		return inventaire;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}


	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}


	public Communaute getCommunaute() {
		return communaute;
	}


	public Esprit getEsprit() {
		return esprit;
	}

	public Humain getPere() {
		return pere;
	}

	public Humain getMere() {
		return mere;
	}

	public ArrayList<Amenagement> getAmenagements() {
		return amenagements;
	}

	public void setAmenagements(ArrayList<Amenagement> amenagements) {
		this.amenagements = amenagements;
	}

	public ArrayList<Humain> getEnfants() {
		return enfants;
	}

	public Humain getConjoint() {
		return conjoint;
	}
	 
	 public boolean estDeLaMemeCivilisation(Humain h)
	 {
		return getCiv().getIndexCiv() == h.getCiv().getIndexCiv();
	 }

	public HashMap<String, Integer> getAttr() {
		return attributes;
	}

	public void setAttr(HashMap<String, Integer> attributes) {
		this.attributes = attributes;
	}
	
	public void putAttr(String s , Integer d) {
		ArrayList<Object[]> triggers = Configuration.attributesTrigerringValues.get(s);
		if (triggers != null) {
			for (int i = 0 ; i < triggers.size(); i++) {
				//System.out.println("TRIGGER : " + ((NCogniton)triggers.get(i)[0]).getNom() + " " + triggers.get(i)[1] + " " + triggers.get(i)[2]);
				Integer v = (Integer) triggers.get(i)[1];
				//System.out.println(v +" "+ d + " " + attributes.get(s));
				
				//new value > old value
				if (v >= attributes.get(s) && v <= d && d > attributes.get(s)) {
					Integer cmp = (Integer) triggers.get(i)[2];
					NCogniton c = (NCogniton) triggers.get(i)[0];
					//System.out.println("Difference franchie montante: " + c.getNom());

					 switch (cmp) {
			            case 2: if (d > v) esprit.addCogniton(c);
			                    break;
			                    
			            case 1: if (d >= v && attributes.get(s) != v) esprit.addCogniton(c);
			                    break;
			                    
			            case 0: if (d == v) esprit.addCogniton(c);
	    						else esprit.removeCogniton(c);
			            		break;
			            		
			            case -1: if (d <= v && attributes.get(s) != v) ;
	    						else esprit.removeCogniton(c);
			            		break;
			            		
			            case -2: if (d < v);
	    						else esprit.removeCogniton(c);
			            		break;
		
			            default:
			            		break;
					 }	
				}
				
				//old value > new value
				else if (v <= attributes.get(s) && v >= d && d < attributes.get(s)) {
					Integer cmp = (Integer) triggers.get(i)[2];
					NCogniton c = (NCogniton) triggers.get(i)[0];
					//System.out.println("Difference franchie descente: " + c.getNom());

					 switch (cmp) {
			            case 2: if (d > v);
			            		else esprit.removeCogniton(c);
			                    break;
			                    
			            case 1: if (d >= v && attributes.get(s) != v);
	            				else esprit.removeCogniton(c);
			                    break;
			                    
			            case 0: if (d == v) esprit.addCogniton(c);
	    						else esprit.removeCogniton(c);
			            		break;
			            		
			            case -1: if (d <= v && attributes.get(s) != v) esprit.addCogniton(c);
			            		break;
			            		
			            case -2: if (d < v) esprit.addCogniton(c);
			            		break;
		
			            default:
			            		break;
					 }	
				}
		}		
	}
		attributes.put(s, d);
		
	}

	/**
	 * Alternate version of getNextPatch which returns this patch instead of null
	 */
	public Patch getNextPatch() {
		if (super.getNextPatch() == null) return this.getPatch();
		return super.getNextPatch();
	}

	public boolean isInsideEnvironmentBounds(int offX , int offY){
		World w = World.getInstance();
		if (
				this.getX() + offX >= 0 &&
				this.getY() + offY >= 0 &&
				this.getX() + offX < w.getWidth() &&
				this.getY() + offY < w.getHeight()
				) 
			return true;
		else return false;
		
	}
	
	/**
	 * Return the angle towards the specified patch
	 */
	public double towards(Patch p) {
	    double dx = p.x - xcor();
	    double dy = -(p.y - ycor());
	    double inRads = Math.atan2(dy,dx);

	    if (inRads < 0)
	        inRads = Math.abs(inRads);
	    else
	        inRads = 2*Math.PI - inRads;

	    return Math.toDegrees(inRads);
	}

	/**
	 * Try to understand some behaviour from Tk3
	 */
	public double distance (double a, double b) {
		return super.distance(a+0.5, b+0.5);
	}
}










