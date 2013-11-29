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
package civilisation.world; 

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import civilisation.AddOn;
import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.Initialiseur;
import civilisation.TurtleGenerator;
import edu.turtlekit2.kernel.environment.Patch;
import edu.turtlekit2.kernel.environment.PatchVariable;
import edu.turtlekit2.kernel.environment.TurtleEnvironment;


/** 
 * Représente le monde de jeu
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


@SuppressWarnings("serial")
public class World extends TurtleEnvironment
{
	
	private static World instance;
	File carte;
	
	static final Color ColorMontagnes = new Color(170,150,150);
	static final Color ColorCollines = new Color(150,130,130);
	static final Color ColorRivieres = new Color(10,10,240);
	static final Color ColorOcean = new Color(10,10,140);
	static final Color ColorPlaines = new Color(60,130,60);
	static final Color ColorDeserts = new Color(255,255,107);
	static final Color ColorForets = new Color(10,100,10);
	static final Color ColorBanquise = new Color(245,245,247);
	static final Color ColorLittoral = new Color(60,140,60);
	
	int nMontagnes = 22;
	int nContinents = 9; 
	int nForets = 30; 
	int nDesertsNord = 4; 
	int nDesertsSud = 4; 
	int nFleuves = 26;

	
	public World() 
	{
		World.instance = this;
		/*Meilleur endroit a trouver pour : */
		new Initialiseur();
	}
	
	public static World getInstance()
	{
		return World.instance;
	}
	
	/* Détourne l'utilisation normale de fillGrid pour régler à notre manière la taille de l'environnement
	 * Efficace pour l'instant */
	@Override
	public void fillGrid(){
		
		if (Configuration.environnementACharger != null){
			System.out.println("Dimensionnement de l'environnement");
			x = Integer.parseInt(Initialiseur.getChamp("Largeur", new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);
	       	y = Integer.parseInt(Initialiseur.getChamp("Hauteur", new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);

		}
		else{
			/* Dummy values for testing*/
		    x = 200;
			y = 200;
		}
		
		grid = new Patch[x][y];
		initGrid();
		
	}
	

	
	public void setup()
	{	


		
		/*Reglages sur les civilisations*/
		for (int i = 0; i < Configuration.civilisations.size(); i++){
			Configuration.civilisations.get(i).postWorldSetup();
		}
		
		/*Init pheromons*/
		for (int i = 0 ; i < Configuration.itemsPheromones.size() ; i++ ) {
			this.addFlavor(Configuration.itemsPheromones.get(i).getNom());
		}
		
		
		System.out.println(Configuration.environnementACharger);

		if (Configuration.environnementACharger != null){
		   System.out.println("Chargement de l'environnement");
	       HashMap<Integer,Terrain> typeTerrains = new HashMap<Integer,Terrain>();
	       ArrayList<String[]> listeTerrains = Initialiseur.getListeChamp("Terrain", new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+Configuration.environnementACharger+Configuration.getExtension()));
	       for (int i = 0; i < listeTerrains.size(); i++){
			   //System.out.println("hash "+i+" "+listeTerrains.get(i)[0]+" "+Configuration.getTerrainByName(listeTerrains.get(i)[0]));

	    	   typeTerrains.put(i,Configuration.getTerrainByName(listeTerrains.get(i)[0]));
			   //System.out.println(typeTerrains.get(i) + typeTerrains.get(i).getCouleur().toString());
	       }
	       
	       ArrayList<String[]> terrains = Initialiseur.getListeChamp("Rang", new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+Configuration.environnementACharger+Configuration.getExtension()));
	       for (int i = 0; i < x; i++){
	    	   for (int j = 0; j < y; j++){
	    		   Terrain t = typeTerrains.get(Integer.parseInt(terrains.get(y-j-1)[i]));
	    		   this.grid[i][j].setColor(t.getCouleur());
	    		   for (int k = 0 ; k < t.getPheromones().size() ; k++) {
		    		   this.grid[i][j].incrementPatchVariable(t.getPheromones().get(k).getNom() , t.getPheroInitiales().get(k));
	    		   }
	    	   }
	       }
	       
	       /*Installation des premières civilisations*/
	       ArrayList<String[]> listeCivs = Initialiseur.getListeChamp("Civilisation", new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+Configuration.environnementACharger+Configuration.getExtension()));
	       for (int i = 0; i < listeCivs.size(); i++){
	    	   
	    	   int u = Integer.parseInt(listeCivs.get(i)[1]);
	    	   int v = Integer.parseInt(listeCivs.get(i)[2]);

	    	   Communaute c = new Communaute(Configuration.getCivilisationByName(listeCivs.get(i)[0]));
	    	   TurtleGenerator.getInstance().createTurtle(c);
	    	   c.moveTo(u, -v);

	       }
	       
	       /*
    	   AddOn a = new AddOn();
    	   TurtleGenerator.getInstance().createTurtle(a);
	        */
	       
	       
		}
		else{
		/*Old World Generator*/
		//TODO : Adapt to Metaciv
			
		/*	int posX;
			int posY;
			
			for(int i=0;i<this.getWidth();i++)
				for(int j=0;j<this.getHeight();j++)
					this.grid[i][j].setColor(ColorOcean);

			for (int i=0; i<nContinents;i++)
			{
				genererContinents((int)(Math.random()*this.getWidth()) , (int)(Math.random()*this.getHeight()) , 5000 , 8);
			}
			dessinerLesCotes();
			for (int i=0; i< nMontagnes;i++)
			{
				do 
				{
					posX = (int)(Math.random()*this.getWidth());
					posY = (int)(Math.random()*this.getHeight());
				} while (this.grid[posX][posY].getColor() == ColorOcean);	
				genererMassifMontagneux(posX, posY, 300);		
			}
			for (int i=0; i< nForets;i++)
			{
				do 
				{
					posX = (int)(Math.random()*this.getWidth());
					posY = (int)(Math.random()*this.getHeight());
				} while (this.grid[posX][posY].getColor() == ColorOcean);	
				genererForet(posX, posY, 200, 3);		
			}
			for (int i=0; i< nDesertsNord;i++)
			{
				posX = (int)(Math.random()*this.getWidth());
				genererDesert(posX, 200, 3, 23.6);		
			}
			for (int i=0; i< nDesertsSud;i++)
			{
				posX = (int)(Math.random()*this.getWidth());
				genererDesert(posX, 200, 3, -23.6);		
			}
			for (int i=0; i< nFleuves;i++)
			{
				do 
				{
					posX = (int)(Math.random()*this.getWidth());
					posY = (int)(Math.random()*this.getHeight());
				} while (this.grid[posX][posY].getColor() != ColorCollines);	
				while (genererFleuves(posX, posY) == false)
					{
						do 
						{
							posX = (int)(Math.random()*this.getWidth());
							posY = (int)(Math.random()*this.getHeight());
						} while (this.grid[posX][posY].getColor() != ColorCollines);	
					}
			}
			genererLittoral();
			initialiserRessources();*/
		}
		

		System.out.println("---End World Setup---"); 

	}

	
	
	
	
	
	
	/*---------------Fonctions de génération du monde---------------------------*/
	
	/**Creer un continent (terrains de type plaine).
	 */
	public void genererContinents(int x , int y , int longueur , int largeur)
	{
		int nouveauX = x;
		int nouveauY = y;
		int toreX = 0;
		int toreY = 0;

		
		for (int i = 0; i < longueur; i++)
		{
			nouveauX = (int) (Math.random()*3) - 1 + nouveauX;
			nouveauY = (int) (Math.random()*3) - 1 + nouveauY;
			
			for (int j = -largeur; j <= largeur; j++)
			{
				for (int k = -largeur; k <= largeur; k++)
				{
					toreX = effetToreX(nouveauX + j);
					toreY = effetToreY(nouveauY + k);

					this.grid[toreX][toreY].setColor(ColorPlaines);	
					
				}
			}	
		}

	}			
	
	/**Une mÔøΩthode qui rend les cÔøΩtes un peu plus rÔøΩalistes
	 */
	public void dessinerLesCotes()
	{
		for (int x = 0; x < this.getWidth(); x++)
		{
			for (int y = 0; y < this.getHeight(); y++)
			{
				if (Math.random() < 0.2 && this.grid[x][y].getColor() == ColorOcean && countCouleurVoisine(this.grid[x][y], ColorPlaines) == 3)
				{
					this.grid[x][y].setColor(ColorPlaines);
				}
			}
		}
	}
	
	/**Une mÔøΩthode qui transforme toutes les plaines/dÔøΩserts/forÔøΩts sur la cÔøΩte en littoral.
	 */
	public void genererLittoral()
	{
		for (int x = 0; x < this.getWidth(); x++)
		{
			for (int y = 0; y < this.getHeight(); y++)
			{
				if ((this.grid[x][y].getColor() == ColorPlaines || this.grid[x][y].getColor() == ColorDeserts || this.grid[x][y].getColor() == ColorForets) && countCouleurVoisine(this.grid[x][y], ColorOcean) >= 1)
				{
					this.grid[x][y].setColor(ColorLittoral);
				}
			}
		}
	}

	/**Creer un desert. Le desert est crÔøΩÔøΩ au niveau d'une latitude donnÔøΩe.
	 * Se propage sur les plaines et la foret
	 */
	public void genererDesert(int x, int longueur , int largeur , double latitude)  //latitude : ÔøΩ90 (+ --> Nord  / - --> Sud)
	{
		int nouveauX = x;
		int nouveauY = (int) ((this.getHeight()/2)*(1+latitude/90));
		int toreX = 0;
		
		for (int i = 0; i < longueur; i++)
		{
			nouveauX = (int) (Math.random()*3) - 1 + nouveauX;
			nouveauY = (int) (Math.random()*3) - 1 + nouveauY;

			if (estSurLaCarte(nouveauX,nouveauY))
			{
				for (int j = -largeur; j <= largeur; j++)
				{
					for (int k = -largeur; k <= largeur; k++)
					{
						toreX = effetToreX(nouveauX + j);
						if (estSurLaCarte(toreX,nouveauY + k) && (this.grid[toreX][nouveauY + k].getColor() == ColorPlaines || this.grid[toreX][nouveauY + k].getColor() == ColorForets))
						{
							this.grid[toreX][nouveauY + k].setColor(ColorDeserts);
						}
					}
				}
			}
		}
	}
	
	
	
	/**Creer une foret. La foret ne recouvre que des cases de type "plaine".
	 */
	public void genererForet(int x , int y , int longueur , int largeur)
	{
		int nouveauX = x;
		int nouveauY = y;
		int toreX = 0;
		
		for (int i = 0; i < longueur; i++)
		{
			nouveauX = (int) (Math.random()*3) - 1 + nouveauX;
			nouveauY = (int) (Math.random()*3) - 1 + nouveauY;

			if (estSurLaCarte(nouveauX,nouveauY))
			{
				for (int j = -largeur; j <= largeur; j++)
				{
					for (int k = -largeur; k <= largeur; k++)
					{
						toreX = effetToreX(nouveauX + j);
						if (estSurLaCarte(toreX,nouveauY + k) && this.grid[toreX][nouveauY + k].getColor() == ColorPlaines)
						{
							this.grid[toreX][nouveauY + k].setColor(ColorForets);
						}
					}
				}
			}
		}
	}
	


	/**Creer un massif montagneux, comprenant des montagnes entourÔøΩes de collines, entourÔøΩes de plaines
	 */
	public void genererMassifMontagneux(int x , int y , int longueur)
	{
		int nouveauX = x;
		int nouveauY = y;
		int toreX = 0;
		int toreY = 0;

		for (int i = 0; i < longueur; i++)
		{
			nouveauX = (int) (Math.random()*3) - 1 + nouveauX;
			nouveauY = (int) (Math.random()*3) - 1 + nouveauY;


				this.grid[effetToreX(nouveauX)][effetToreY(nouveauY)].setColor(ColorMontagnes);
				for (int j = -1; j <= 1; j++)
				{
					for (int k = -1; k <= 1; k++)
					{
						toreX = effetToreX(nouveauX + j);
						toreY = effetToreY(nouveauY + k);
						if (this.grid[toreX][toreY].getColor() != ColorMontagnes)
						{
							this.grid[toreX][toreY].setColor(ColorCollines);
							Patch voisins[] = this.grid[toreX][toreY].getNeighbors();
							for (int l = 0; l < voisins.length; l++)
							{
								if (voisins[l].getColor() == ColorOcean)
								{
									voisins[l].setColor(ColorPlaines);
								}
							}
						}
					}
				}
			
		}
	}
	
	/**
	 * Calcul un fleuve et retourne faux si il n'atteint pas la mer ou si il est trop petit
	 * Retourne vrai et le trace dans le cas contraire
	 */
	public Boolean genererFleuves(int x , int y)
	{
		int nouveauX = x;
		int nouveauY = y;
		Patch voisins[];
		Patch cible;
		ArrayList<Patch> selection = new ArrayList<Patch>();
		ArrayList<Patch> fleuve = new ArrayList<Patch>();
		fleuve.add(this.grid[x][y]);

		
		while (countCouleurVoisine(this.grid[nouveauX][nouveauY], ColorOcean) == 0 && countCouleurVoisine(this.grid[nouveauX][nouveauY], ColorRivieres) == 0)
		{
			voisins = this.grid[nouveauX][nouveauY].getNeighbors();
			selection.clear();
			for (int l = 0; l < voisins.length; l++)
			{
				if (voisins[l].getColor() == ColorOcean || (( voisins[l].getColor() == ColorPlaines || voisins[l].getColor() == ColorForets || voisins[l].getColor() == ColorDeserts ) && voisinsPatchSontDansListe(voisins[l] , fleuve) == false))
				{
					selection.add(voisins[l]);
				}
			}
			if (selection.size() == 0) return false; //La riviere ne peut plus continuer : On annule et on annonce qu'elle n'a pas ÔøΩtÔøΩ construite
			cible = selection.get((int)(Math.random()*selection.size())); //On selectionne une case au hasard
			nouveauX = cible.x;
			nouveauY = cible.y;
			fleuve.add(cible);
		}
		
		if (fleuve.size() <= 5) return false; //Pour avoir des vrais fleuves
		
		//Si on arrive ici, c'est que le fleuve en memoire est valide : on le dessine
		for (int i = 0; i < fleuve.size(); i++)
		{
			fleuve.get(i).setColor(ColorRivieres);
			voisins = fleuve.get(i).getNeighbors();
			for (int l = 0; l < voisins.length; l++)
			{
				if (voisins[l].getColor() == ColorDeserts)
				{
					voisins[l].setColor(ColorPlaines); //Si une riviÔøΩre passe dans le desert, la zone deviens fertile
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Met en place les attributs des patchs.
	 * On utilise les phéromones pour cela.
	 */
	private void initialiserRessources()
	{

	  
	}
	
	
	
	/**Verifie qu'un point se situe dans les limites de la carte.
	 * Retourne vrai si le point est inclu, faux sinon.
	 */
	private Boolean estSurLaCarte(int x, int y)
	{
		if (x < this.getWidth() && x >= 0 && y >= 0 && y < this.getHeight())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**Verifie si un patch voisin a la couleur donnee.
	 * Retourne vrai si la couleur est presente, faux sinon.
	 */
	private int countCouleurVoisine(Patch p, Color c)
	{
		int resultat = 0;
		Patch voisins[] = p.getNeighbors();

		for (int l = 0; l < voisins.length; l++)
		{
			if (voisins[l].getColor() == c)
			{
				resultat++;
			}
		}
		
		return resultat;
	}
	
	/**Transforme une coordonnÔøΩe x pour qu'elle corresponde au tore
	 */
	private int effetToreX(int x)
	{
		int resultat = x;
		while ( resultat < 0 || resultat >= this.getWidth())
		{
			if (resultat < 0) resultat += this.getWidth();
			if (resultat >= this.getWidth()) resultat += -this.getWidth();
		}
		return resultat;
	}

	/**Transforme une coordonnÔøΩe y pour qu'elle corresponde au tore
	 */
	private int effetToreY(int y)
	{
		int resultat = y;
		while ( resultat < 0 || resultat >= this.getHeight())
		{
			if (resultat < 0) resultat += this.getHeight();
			if (resultat >= this.getHeight()) resultat += -this.getHeight();
		}
		return resultat;
	}
	
	/**Verifie si au moins deux des voisins du patch passÔøΩ en paramÔøΩtre est dans une liste fournie
	 * (Utile pour la gÔøΩnÔøΩration des fleuves)
	 */
	private Boolean voisinsPatchSontDansListe(Patch p , ArrayList<Patch> liste)
	{
		Boolean unVoisin = false;
		Patch voisins[] = p.getNeighbors();
		
		for (int i = 0 ; i < liste.size(); i++)
		{
			for (int j = 0; j < voisins.length; j++)
			{
				if (liste.get(i) == voisins[j])
				{
					if (unVoisin)
					{
						return true;
					}
					else
					{
						unVoisin = true;
					}

				}
			}
		}

		return false;
	}
	
	public int getWidth()
	{
		return this.x;
	}
	
	public int getHeight()
	{
		return this.y;
	}

	/*
	 * Ajoute une pheromone (utile pour le controle de territoire)
	 */
	public void addFlavor(String nom)
	{
		PatchVariable p = new PatchVariable(nom);
		addGridVariable(p);
	}


	
	
	
	
	
	/*----------------------GETTERS/SETTERS---------------------------*/
	
	
	public static Color getColorMontagnes() {
		return ColorMontagnes;
	}

	public static Color getColorCollines() {
		return ColorCollines;
	}

	public static Color getColorRivieres() {
		return ColorRivieres;
	}

	public static Color getColorOcean() {
		return ColorOcean;
	}

	public static Color getColorPlaines() {
		return ColorPlaines;
	}

	public static Color getColorDeserts() {
		return ColorDeserts;
	}

	public static Color getColorForets() {
		return ColorForets;
	}

	public static Color getColorBanquise() {
		return ColorBanquise;
	}

	public static Color getColorLittoral() {
		return ColorLittoral;
	}		

	
	
	
	
}
