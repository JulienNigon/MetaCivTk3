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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import utils.Utils;

import civilisation.AllMessage;
import civilisation.Civilisation;
import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.Amenagement_Route;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.memes.MEM_Croire;
import civilisation.individu.plan.GroupPlan;
import civilisation.individu.plan.Plan;
import civilisation.inventaire.Inventaire;
import civilisation.inventaire.ObjetInventaire;
import civilisation.inventaire.Objet_Arc;
import civilisation.inventaire.Objet_Arme;
import civilisation.inventaire.Objet_Armure;
import civilisation.inventaire.Objet_Lance;
import civilisation.marks.ExplosionCombat;
import civilisation.pathfinder.Noeud;
import civilisation.urbanisme.Batiment;
import civilisation.urbanisme.Batiment_Hutte;
import civilisation.world.World;

import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.environment.Patch;


/** 
 * Classe d'agents principale
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

@SuppressWarnings("serial")
public class Humain extends Turtle 
{
	public static String[] couleursDePeau = {"Blanc", "Noir", "Rouge", "Bleu", "jaune", "Orange"};
	
	int visionRadius;
	Civilisation civ;
	Communaute communaute;
	ArrayList<Batiment> batiments;
	ArrayList<Amenagement> amenagements;
	Inventaire inventaire;
	int vie;
	int gestation; // Si l'agent porte un enfant : -1 indique non, sinon indique le temps restant avant enfantement
	int influence;
	Boolean femme; //false=homme true=femme (Pr√É¬©sent pour une √É¬©ventuelle utilisation future)
	public String couleurDePeau;
	Humain pere;
	Humain mere;
	Humain conjoint;
	ArrayList<Humain> enfants;
	public int foi=0;

	Boolean isSelected = false;
	
	Esprit esprit; //Contient les "pens≈Ωes" de l'agent
	
	/*variables pour la gestion des actions*/
	String projet; //M≈Ωmorise le projet que souhaite accomplir l'agent
	Boolean commit; //Indique si ce projet engage l'agent (il ne l'abandonne pas aprÔøΩs avoir effectu≈Ω un r≈Ωflexe par exemple)
	int timer; //Si le projet de l'agent a une dur≈Ωe maximum dans le temps : -1 indique que non
	
	ArrayList<Patch> chemin;
	private ArrayList<AllMessage> MessageBox;
	int tempsPatch;
    public static int poidsActionDiscuter_EtreDansLaMemeCiv = 25;
    public static int poidsActionDiscuter_EtreTimide = 25;
    public static int poidsActionDiscuter_EtreExtraverti = 25;
    public static int gainFoiActionPrier = 4;

	

	public Humain(Civilisation civ , Communaute communaute)
	{
		super("penser");
		pere = null;
		mere = null;
		initialisation(civ, communaute);
		chemin = new ArrayList<Patch>();
		MessageBox = new ArrayList<AllMessage>();
		tempsPatch = 2;
	}
	
	/**
	 * Constructeur si l'agent ÀÜ des parents
	 */
	public Humain(Civilisation civ , Communaute communaute , Humain pere , Humain mere)
	{
		super("penser");
		this.pere = pere;
		this.mere = mere;
		initialisation(civ, communaute);
		chemin = new ArrayList<Patch>();
		MessageBox = new ArrayList<AllMessage>();
		heritage();
		tempsPatch = 2;
	}
	
	public void die()
	{
		gererLesBiensALaMort();
		
		//en cas de mort, on prend un agent au hasard à 3 case max, et on lui donne le cogniton Croire
		Humain h = oneOf(HumaininRadius(3));
		if(h != null)
		{
			Esprit e = h.getEsprit();
			//s'il possède déjà le cogniton, on incrément le seuil
			if(e.containsCogniton("Meme", "Croire")) 
			{
				((MEM_Croire) e.getCognitonByName("Meme", "Croire")).incrementSeuilFoi();
			}
			//sinon on génére le cogniton
			else 
			{
				e.ajouterCogniton(new MEM_Croire(e));
			}
		}
		
		System.out.println("------------------------------------------------");
		System.out.println("Un agent est mort au cours du projet : " + this.getEsprit().getPlanEnCours());
		System.out.println("Vie : " + this.getVie());
		if(this.chemin != null) System.out.println("Chemin : " + this.chemin);
		System.out.println("Inventaire" + this.inventaire);
		
		//Utils.afficherTrace();
		System.out.println("-------------------******-----------------------");
		
		this.getEsprit().clearAllCognitons();
		super.die();

	}
	
	public void gererLesBiensALaMort(){
		Humain heritier = null; 
		if (!enfants.isEmpty()) heritier = enfants.get(0);
		
		while(batiments.isEmpty() == false){
			communaute.retirerBatiment(batiments.get(0));
			if (heritier != null){
				int taille = batiments.get(0).getInventaire().getAll().size();
				ArrayList<ObjetInventaire> liste = batiments.get(0).getInventaire().getAll();
				for (int i = 0; i < taille ; i++){
					heritier.getInventaire().add(liste.get(0));
					i--; taille--;
				}
			}
			batiments.get(0).detruire(); // Actuellement le batiment est detruit, et les biens transferes
			
		}
		while(amenagements.isEmpty() == false){
			amenagements.get(0).getPosition().getMark(amenagements.get(0).getNom());
			amenagements.remove(0);
		}
	}
	
	public void heritage(){
		setInfluence((int) ((pere.getInfluence() + mere.getInfluence()) / 2.)) ;
	}
	
	
	/**
	 * Initialise l'agent de maniÔøΩre stantard
	 */
	private void initialisation(Civilisation civ, Communaute communaute)
	{
		this.civ = civ;
		projet = null;
		commit = false;
		timer = -1;
		gestation = -1;
		influence = 2;
		inventaire = new Inventaire(this);
		esprit = new Esprit(this);
		
		this.communaute = communaute;
		batiments = new ArrayList<Batiment>();
		amenagements = new ArrayList<Amenagement>();
		enfants = new ArrayList<Humain>();
		vie = 200;
		if (Math.random() >= 0.5)
		{
			femme = false;
		}
		else
		{
			femme = true;
		}
		inventaire.add(new Objet_Arc());
		//inventaire.add(new Objet_Lance());

		/*for (int i = 0; i < this.getEsprit().projets.size(); i++)
		{
			System.out.println(this.getEsprit().projets.get(i).getNom() + " : " +this.getEsprit().projets.get(i).getPoids());
		}
		System.out.println("Poids total :" + this.getEsprit().poidsTotal);*/

	}


	/**
	 * Fonction qui centralise le choix des actions de l'agent 
	 * @return la t√É¬¢che √É¬† effectuer
	 */
	public String penser()
	{
		try
		{
			gestion();  //Pour l'instant, la fonction de gestion est appell≈Ωe ici, mais une meilleure fa√É¬ßon de faire devrait √É¬™tre trouv√É¬©e
			esprit.penser();
			return "penser";
		}
		catch(Exception e) {
			debug(e.toString());
		}
		return "penser";
	}
	
	/**
	 * Fonction de gestion basique de l'agent (nourriture...)
	 */
	public void gestion()
	{
		// L'agent perd de la vie (= faim)
		vie--;
		if (vie <= 0)
		{
			System.out.println("L'agent " + this.getName() + " est mort de faim");
			die();
		}
		// Si l'agent est pregnant√â
		if (gestation > 1)
		{
			gestation --;
		}
		else if (gestation == 1)
		{
			naissance();
		}
		

		
		// Positionne le marqueur de sa civilisation
		this.emit("civ"+civ.getIndexCiv(), 0.01);
		
		//ESSAYE DE DISCUTER
		discuter(oneOfHumanHere());
		
		//FOI
		foi--;
		if(foi < 0) foi = 0;
		
		MEM_Croire croire = (MEM_Croire) esprit.getCognitonByName("Meme", "Croire");
		//si le mem existe, on met à jour le poids du PLAN_Prier
		if(croire != null) croire.setPoidsCroire(getPoidsPrier(croire.getSeuilFoi()));
	}
	

	
	/**
	 * Enfante un nouvel agent
	 */
	private void naissance() {
		Humain h = new Humain(civ, communaute, conjoint, this);
		
		//héritage par this
		if(Math.random() < 0.5)
		{
			h.couleurDePeau = couleurDePeau;
		}
		//héritage du conjoint
		else
		{
			h.couleurDePeau = conjoint.couleurDePeau;
		}		
		
		createTurtle(h);
		gestation = -1;
		enfants.add(h);
		conjoint.getEnfants().add(h);
	}




	public void setup()
	{
		super.setup();
		setColor(civ.getCouleur());
		playRole("Humain");
	} 

	
	
	
	
	/**
	 * L'agent mange un aliment de son inventaire
	 */
	public void manger()
	{
		if (inventaire.getFirstAliment() != null)
		{
			vie += inventaire.getFirstAliment().getValeurNutritive();
			inventaire.remove(inventaire.getFirstAliment().toString(), 0);
		}
	}

	/**
	 * L'agent se d≈Ωplace sans but particulier 
	 */
	public void explorer()
	{
		move(1);
	}
	
	/**
	 * L'agent retourne vers sa communaut≈Ω. Une fois arriv≈Ω, il d≈Ωpose ≈Ωventuellement ce qu'il a r≈Ωcolt≈Ω dans la nature
	 */
	public void rentrerDeposerRecolte()
	{
		if (isHere(communaute))
		{
			inventaire.deposerRecolte(batiments.get(0)); //Dans un premier temps, on va supposer qu'un humain n'a qu'une maison√â
			/*L'agent est rentr≈Ω : son projet est termin≈Ω*/
			esprit.finProjet();
		}
		else
		{
			rentrer();
		}
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
					this.chemin.addAll(this.AllerVers(but.position));
					/*for(int i = 0; i < this.chemin.size();i++)
					{
						this.chemin.get(i).setColor(Color.white);
					}*/
				}
				
				if(chemin != null && !chemin.isEmpty())
				{
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
				emit("passage", 1.0);
				if(this.smell("passage") > Configuration.passagesPourCreerRoute && !this.isMarkPresent("Route"))
				{
					Amenagement_Route troncon = new Amenagement_Route(this.position);
					this.addAmenagement(troncon);
					this.dropMark("Route", troncon);
				}
		}
	
	
	 /* Retourne le patch le plus visite autour du patch courant
	 */
	private Double PatchPlusVisiteAutour()
	{	
		return this.getHeadingToMaxOf("passage", 1, true);
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
	 * Ajoute un amenagement ÀÜ l'agent
	 * @param l'amenagement ÀÜ ajouter
	 */
	public void addAmenagement(Amenagement a)
	{
		amenagements.add(a);
		if( a.cognitonsLies() != null)
		{
			for (int i = 0; i < a.cognitonsLies().length; i++)
			{
				this.getEsprit().addCognitonByName(a.cognitonsLies()[i]);
			}
		}
	}
	
	/**
	 * A TESTER!!
	 * Retire un amenagement ÀÜ l'agent
	 * @param l'amenagement ÀÜ retirer
	 */
	public void removeAmenagement(Amenagement a)   
	{
		for (int i = 0; i < amenagements.size(); i++)
		{
			if (amenagements.get(i).equals(a))
			{
				amenagements.remove(i);
			}
		}
		if( a.cognitonsLies() != null)
		{
			for (int j = 0; j < a.cognitonsLies().length; j++)
			{
				this.getEsprit().removeCognitonByFullname(a.cognitonsLies()[j]);
			}
		}
	}
	
	/**
	 * Fait attendre un enfant ÀÜ l'agent
	 */
	public void attendreEnfant(Humain conjoint) {
		if (gestation == -1)
		{
			this.conjoint = conjoint;
			gestation = Configuration.tempsDeGestation;
		}	
	}
	
	/**
	 * L'agent avance ou tourne si un obstacle se pr≈Ωsente
	 */
	public void move(int i)
	{
		while (nextPatch().getColor().equals(World.getColorOcean()) || nextPatch().getColor().equals(World.getColorRivieres()))	
		{
			randomHeading();
		}
		fd(i);
	}
	
	public void fd(int i)
	{
			Color couleur = this.getPatchColor();
			int TempsAPasser = Configuration.couleurs_terrains.get(couleur).getPassabilite();
			TempsAPasser /= 10;
			if(this.tempsPatch > TempsAPasser)
			{
				this.tempsPatch--;
				Color coul = this.nextPatch().getColor();
				//On va modifier pour que ça colle avec les nouveaux terrains
				if(!(coul.getRed() == 10 && coul.getGreen() == 10 && coul.getBlue() == 240 )&&
					!(coul.getRed() == 10 && coul.getGreen() == 10 && coul.getBlue() == 140 ))
				{
					super.fd(1);
					emit("passage",1.0);
					if(!this.position.equals(this.communaute.position))
					{
						this.fd(1);
					}
					
				}
				else
				{
					this.randomHeading();
					this.fd(1);
				
				}
				
				
			}
			if(this.tempsPatch < TempsAPasser)
			{
				this.tempsPatch++;
			}
			if(this.tempsPatch == TempsAPasser)
			{
				Color coul = this.nextPatch().getColor();
				//On va modifier pour que ça colle avec les nouveaux terrains
				if(!(coul.getRed() == 10 && coul.getGreen() == 10 && coul.getBlue() == 240 )&&
					!(coul.getRed() == 10 && coul.getGreen() == 10 && coul.getBlue() == 140 ))
				{
					super.fd(1);
					emit("passage",1.0);
					
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
	 * 
	 */
	public boolean Possede(String objet)
	{
		ArrayList<ObjetInventaire> liste = this.inventaire.getAll();
		for(int i = 0; i<liste.size();i++)
		{
			if(liste.get(i).is(objet))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Change la direction de l'agent afin qu'il se positionne en dans la direction de l'agent cible 
	 * @param cible
	 */
	public void face(Turtle cible)
	{
		double direction = towards(cible.x,cible.y);
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
	 * @return la turtle la plus eloign√Ø¬ø¬Ω de la tortue appelante
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
	 * 
	 * @param cible
	 * @return distance entre la tortue et une tortue cibl√Ø¬ø¬Ωe
	 */
	public double distanceTo(Turtle cible)
	{
		double x = cible.xcor();
		double y = cible.ycor();
		return this.distance(x, y);
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
	
	public Humain oneOf(ArrayList<Humain> humains)
	{
		if(humains == null || humains.size() == 0) return null;
		else return humains.get((int)Math.random()*(humains.size()-1));
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
				if(this.distance(this.x + i - radius, this.y + j - radius) < radius)
				{
					Turtle[] torts = this.turtlesAt(i - radius, j - radius);
					for(int k = 0;k< torts.length;k++)
					{
						tortues.add(torts[k]);
					}					
				}
			}
		}
		return tortues;
	}
	
	/**
	 * 
	 * @param role
	 * @return les tortues jouant le role donn√Ø¬ø¬Ω en parametre
	 */
	@SuppressWarnings("null")
	public ArrayList<Turtle> TurtlesWithRole(String role)
	{
		Turtle[] tortues = this.getTurtlesWithRole(role);
		ArrayList<Turtle> choix = new ArrayList<Turtle>();
		for(int i = 0;i < tortues.length;i++)
		{
			choix.add(tortues[i]);
		}
		return choix;
	}
	
	
	
	/**
	 * 
	 * @param role
	 * @return une tortue presente sur le patch ayant un role donn√Ø¬ø¬Ω
	 */
	
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
 * Retourne une tortue pr≈Ωsente sur le mÔøΩme patch dont le critÔøΩre pass≈Ω en paramÔøΩtre correspond
 * @param m : La m≈Ωthode a utiliser pour effectuer la comparaison
 * @param params : les paramÔøΩtres dont la m≈Ωthode a besoin
 * @param o : la valeur souhait≈Ωe pour la compraison
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
		Turtle[] tortuesIci = this.turtlesHere();
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
	 * Override de la fonction originale pour retourner un point al≈Ωatoire parmi les MaxOf
	 * 
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
				if (! (i == 0 && j == 0)) {
					final Patch tmpP = getPatchAt(i, j);
					final double tmp = tmpP.smell(patchVariable);
					if (tmp >= max) {
						max = tmp;
					}
				}
			}
		}
		/*On conserve tous les patch dont la valeur est le max*/
		for (int i = -inRadius; i <= inRadius ; i++) {
			for (int j = -inRadius; j <= inRadius ; j++) {
				if (! (i == 0 && j == 0)) {
					final Patch tmpP = getPatchAt(i, j);
					final double tmp = tmpP.smell(patchVariable);
					if (tmp == max) {
						p.add(tmpP);
					}
				}
			}
		}
		int index =(int) (Math.random()*(p.size()-1));
		return p.get(index);
	}
	
	/**
	 * Retourne un amenagement dont le nom est pass≈Ω en paramÔøΩtre
	 * @param type : nom de l'am≈Ωnagement
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
	
	
	public void Attaquer(Humain cible,Objet_Arme arme)
	{
		int degats = 0;
		if(arme == null)
		{
			 degats = 5 - cible.getDefense();
		}
		else
		{
			 degats = arme.getPuissance() - cible.getDefense();
		}
		cible.setVie(cible.getVie() - degats);

		cible.dropMark("ExplosionCombat", new ExplosionCombat());
		
		if (cible.getVie() <= 0)
		{
			cible.die();
		}
	}
	
	public int getDefense()
	{
		int defense = 0;
		ArrayList<ObjetInventaire> allObjets = inventaire.getAll();
		for(int i = 0;i < allObjets.size();i++)
		{
			if(allObjets.get(i).is("armure"))
			{
				Objet_Armure armure = (Objet_Armure) allObjets.get(i);
				defense += armure.getDefense();
			}
		}
		return defense;
	}

	
	/**
	 * 
	 * @param cible
	 * @return true si l'agent peut se deplacer sur le Patch cible
	 */
	
	public boolean PasObstacle(Patch cible)
	{
		return !inclus(cible.color,this.civ.getInaccesibles()) ;
	}
	
	
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
						Color couleur = this.getPatchColorAt(i - this.visionRadius, j - this.visionRadius);
				
						int passabilite = Configuration.couleurs_terrains.get(this.getPatchColorAt(i - this.visionRadius, j - this.visionRadius)).getPassabilite();
						map[this.xcor() + i - this.visionRadius][this.ycor()+j - this.visionRadius] = (int) (passabilite - (passabilite/2*1/this.smellAt("passage", i - this.visionRadius, j - this.visionRadius)));
							 
						

				if(this.isMarkPresentAt("Route", i - this.visionRadius, j - this.visionRadius))
				{
					map[this.xcor() + i - this.visionRadius][this.ycor()+j - this.visionRadius] -= 1;
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
		Noeud noeud = new Noeud(this.position.x,this.position.y,0,0);
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
				return liste;
			}
			else
			{
				liste.add(0,this.getPatchAt(x - this.position.x, y - this.position.y));
			}
		}
		
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
	 * @param current le patch visit≈Ω
	 * @param visites le tableau des patchs visit≈Ωs ou non
	 * @return true si il n'y a aucun patch non visit≈Ω autour de current
	 */
	private boolean AucunPatchNonVisiteAutour(Patch current, int[][] visites) {
		// TODO Auto-generated method stub
		Patch test1 = this.getPatchAt(current.x - this.xcor() + 1, current.y - this.ycor());
		Patch test2 = this.getPatchAt(current.x - this.xcor(), current.y - this.ycor()+ 1);
		Patch test3 = this.getPatchAt(current.x - this.xcor()- 1, current.y - this.ycor());
		Patch test4 = this.getPatchAt(current.x - this.xcor() ,current.y - this.ycor() - 1);
		boolean test = true;
		if(visites[test1.x][test1.y] == 0 && this.inclus(test1.color, this.civ.getInaccesibles()))
		{
			test = false;
		}
		if(visites[test2.x][test2.y] == 0 && this.inclus(test2.color, this.civ.getInaccesibles()))
		{
			test = false;
		}
		if(visites[test3.x][test3.y] == 0 && this.inclus(test3.color, this.civ.getInaccesibles()))
		{
			test = false;
		}
		if(visites[test4.x][test4.y] == 0 && this.inclus(test4.color, this.civ.getInaccesibles()))
		{
			test = false;
		}
		return test;
	}

	/**
	 * 
	 * @param cible
	 * @return le Patch raprochant le plus l'agent de sa cible, compte tenu des obstacles
	 * l'agent sera amen≈Ω a faire des choix qui n'optimiseront pas toujours sa decison, la 
	 * faute a un manque d'information inherant a l'agent
	 */
	public ArrayList<Patch> AllerVers(Patch cible)
	{
		return AStar(cible);	
	}
	
	public void Draguer(Humain fille)
	{
		ArrayList<Cogniton> carac = new ArrayList<Cogniton>();
		int i = 0;
		while(i < this.esprit.getTraits().size() && carac.size() < 3)
		{
			this.esprit.getTraits().get(i);
			carac.add(this.esprit.getTraits().get(i));
			i++;
		}
		AllMessage mess = new AllMessage("veux tu m'epouser ?",carac, this);
		this.sendMessage(fille.getAddress(),mess);
	}
	
	public void Repondre1(Humain mec , ArrayList<Cogniton> mess)
	{
		if(this.conjoint == null)
		{
			ArrayList<Cogniton> message = (ArrayList<Cogniton>) mess;
			int coeff = 0;
			for(int i = 0;i<message.size();i++)
			{
				if(this.inclus(message.get(i), this.esprit.traits))
				{
					coeff++;
				}
			}
			AllMessage reponse;
			if((coeff == message.size()) || (coeff == this.esprit.getTraits().size()))
			{
				 reponse = new AllMessage("RE: veux tu m'epouser ?","oui",this);
			}
			else
			{
				 reponse = new AllMessage("RE: veux tu m'epouser ?","non",this);
			}
			mec.LaisserMessage(reponse);
		}
	}
	
	public void ChercherCompagnon()
	{
		if (isHere(communaute) && !this.femme )
		{
			Turtle[] cibles = this.turtlesHere();
			for(int  i =0;i< cibles.length;i++)
			{
				this.Draguer((Humain)cibles[i]);
			}
		}
		else
		{
			rentrer();
		}
	}
	
	public void Epouser(Humain fille)
	{
		this.conjoint = fille;
		fille.attendreEnfant(this);
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
				if(this.distance(this.x + i - radius, this.y + j - radius) < radius)
				{
					Turtle[] torts = this.turtlesAt(i - radius, j - radius);
					for(int k = 0;k< torts.length;k++)
					{
						if(torts[k].isPlayingRole("Humain"))
						{
							tortues.add((Humain)torts[k]);
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
				if(this.distance(this.x + i - radius, this.y + j - radius) < radius)
				{
					Turtle[] torts = this.turtlesAt(i - radius, j - radius);
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
	
	public boolean emettreInfluence(GroupPlan plan)
	{
		Turtle humains[];
		int total = 0;
		for(int x = -1; x <= 1; x++){
			for (int y = -1; y <= 1; y++){
				humains = this.turtlesAt(x, y);
				total += humains.length;
			}
		}
		if (total >= Configuration.tailleMinimaleGroupe)
		{
			for(int x = -1; x <= 1; x++){
				for (int y = -1; y <= 1; y++){
					humains = this.turtlesAt(x, y);
					for(int a = 0; a < humains.length; a++){
						if (humains[a].isPlayingRole("Humain")){
							((Humain) humains[a]).recevoirInfluence(plan, this);
						}
					}
				}
			}
			return true;
		}
		return false;

	}
	
	public void recevoirInfluence(GroupPlan plan, Humain leader)
	{
		esprit.recevoirInfluence(plan, leader);
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
	
	//-----------------GESTION MESAGES ---------------
	
	public AllMessage ReceiveMessageWithSubject(String subject)
	{
		int size = this.getMessageBox().size();
		AllMessage mess;
		for(int i = 0;i<size;i++)
		{
			mess = this.getMessageBox().get(i);
			if(mess.getSubject().equals(subject))
			{
				return mess;
			}
		}
		return null;
	}
	
	//----------------GETTERS/SETTERS-------------------
	
	public ArrayList<Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(ArrayList<Batiment> batiments) {
		this.batiments = batiments;
	}

	public int getVisionRadius() {
		return visionRadius;
	}

	public Civilisation getCiv() {
		return civ;
	}


	public int getVie() {
		return vie;
	}


	public Boolean getFemme() {
		return femme;
	}

	public Inventaire getInventaire(){
		return inventaire;
	}


	public String getProjet() {
		return projet;
	}


	public void setProjet(String projet) {
		this.projet = projet;
	}


	public Boolean getCommit() {
		return commit;
	}


	public void setCommit(Boolean commit) {
		this.commit = commit;
	}


	public int getTimer() {
		return timer;
	}


	public void setTimer(int timer) {
		this.timer = timer;
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

	public Boolean isFemme() {
		return femme;
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

	public void LaisserMessage(AllMessage mess)
	{
		this.getMessageBox().add(mess);
	}
	
	public ArrayList<AllMessage> getMessageBox()
	{
		return this.MessageBox;
	}

	public void setVie(int vie)
	{
		this.vie = vie;
	}

	public Humain getConjoint() {
		return conjoint;
	}

	public boolean avoirUnePioche()
	{
		ArrayList<ObjetInventaire> allObjets = this.getInventaire().getAll();
		for(int i=0; i < allObjets.size(); i++)
		{
			if(allObjets.get(i).is("pioche")) return true;
		}
		
		return false;				
	}
	
	/**
	 * L'agent tente de transmettre un même ou une skill au hasard
	 * @param interlocuteur : agent avec qui on parle
	 */
	public void discuter(Humain interlocuteur)
	{
		if(interlocuteur != null)
		{
			int chanceDeDiscuter = 50;
			 
			if(estTimide()) chanceDeDiscuter -= poidsActionDiscuter_EtreTimide;
			else if(estExtraverti()) chanceDeDiscuter += poidsActionDiscuter_EtreExtraverti;
			 
			if(interlocuteur.estTimide()) chanceDeDiscuter -= poidsActionDiscuter_EtreTimide;
			else if(interlocuteur.estExtraverti()) chanceDeDiscuter += poidsActionDiscuter_EtreExtraverti;
			
			if(estDeLaMemeCivilisation(interlocuteur)) chanceDeDiscuter += poidsActionDiscuter_EtreDansLaMemeCiv;
			else chanceDeDiscuter -= poidsActionDiscuter_EtreDansLaMemeCiv;
			 
			//DISCUSSION
			if(Math.random() * 100 < chanceDeDiscuter)
			{	 
				Esprit e = this.getEsprit();
				Cogniton cognitonATransmettre=null;			

				if (Math.random() < 1) // Parle de mêmes
				{
					cognitonATransmettre = e.getMemes().get(Utils.rand(0, e.getMemes().size() -1));
				}
				else // Parle de skill
				{
					cognitonATransmettre = e.getSkills().get(Utils.rand(0, e.getSkills().size() -1));
				}

				//Utils.debug("[DISCUSSION]Une discussion à eu lieu. Cogniton à Tranmettre: "+ cognitonATransmettre.getNom());
				if(!interlocuteur.getEsprit().containsCogniton(cognitonATransmettre) && cognitonATransmettre != null)
				{
					//Utils.debug(2, "[DISCUSSION]DOESN'T ALREADY EXIST ! "+ cognitonATransmettre.getNom());
					double chance = Math.random() * 100;
					if(chance < cognitonATransmettre.getTauxTransfert())
					{
						Utils.debug(2, "[DISCUSSION]SUCCESS TRANSMISSION ! "+ cognitonATransmettre.getNom());
						interlocuteur.getEsprit().addCognitonByName(cognitonATransmettre.getClass().getName());
					}
					else Utils.debug(2, "[DISCUSSION]ECHEC de discussion: "+ chance +" / 100 < "+ cognitonATransmettre.getTauxTransfert());
				}
				//else Utils.debug("[DISCUSSION]ECHEC de discussion: cognitonATransmettre déjà connu");
			}
			//else Utils.debug("[DISCUSSION]ECHEC de discussion: mauvais tirage");
		}
		//else Utils.debug("[DISCUSSION]ECHEC de discussion: pas d'interlocuteur");
	 }
	 
	 public boolean estTimide()
	 {
		 ArrayList<Cogniton> listeTraits = this.getEsprit().getTraits();
		 int i = 0;
		 while(i < listeTraits.size())
		 {
			 if(listeTraits.get(i).getNom() == "Timide") return true;
			 i++;
		 }
		
		 return false;
	 }
	 
	 public boolean estExtraverti()
	 {
		 ArrayList<Cogniton> listeTraits = this.getEsprit().getTraits();
		 int i = 0;
		 while(i < listeTraits.size())
		 {
			 if(listeTraits.get(i).getNom() == "Extraverti") return true;
			 i++;
		 }
		
		 return false;
	 }
	 
	 public boolean estDeLaMemeCivilisation(Humain h)
	 {
		return getCiv().getIndexCiv() == h.getCiv().getIndexCiv();
	 }

	 public int getInfluence() {
		 return influence;
	 }

	 /**
	  * Modifie l'influence de l'agent et adapte ses cognitons en fonction
	  * @param influence : la nouvelle valeur d'influence
	  */
	 public void setInfluence(int influence) {
		 int oldInfluence = this.influence;
		 this.influence = influence;	 
		 //On verifie pour chaque meme d'influence si on doit l'ajouter ou le retirer
		 int tailleTab = Configuration.memesInfluence.length;
		 for (int i = 0; i < tailleTab; i++) {
			 if (oldInfluence >= Configuration.seuilApparitionMemeInfluence[i] && influence < Configuration.seuilApparitionMemeInfluence[i]){
				 esprit.removeCognitonByFullname(Configuration.memesInfluence[i]);
			 }
			 else if (oldInfluence < Configuration.seuilApparitionMemeInfluence[i] && influence >= Configuration.seuilApparitionMemeInfluence[i]){
				 esprit.addCognitonByName(Configuration.memesInfluence[i]);
			 }
		 } 
	 }
	 
	 
	 public void addInfluence(int influence){
		 setInfluence(this.influence + influence);
	 }


public int getPoidsPrier(int seuil)
{
	 float difference = seuil - foi;
	 if(foi > 0) Utils.debug(2, "[FOI]"+ (int) Math.round(difference));
	 return (int) Math.round(difference);
}

public void prier()
{
	Utils.debug(2, foi + "/"+  ((MEM_Croire) esprit.getCognitonByName("Meme", "Croire")).getSeuilFoi());
	foi += gainFoiActionPrier;
	Utils.debug(2, "apres: "+ foi  + "/"+ ((MEM_Croire) esprit.getCognitonByName("Meme", "Croire")).getSeuilFoi());
}
}










