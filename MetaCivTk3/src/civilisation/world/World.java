package civilisation.world; 

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import turtlekit.kernel.TKEnvironment;
import turtlekit.mle.AbstractMLEAgent;
import turtlekit.pheromone.Pheromone;
import civilisation.AddOn;
import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.Initialiseur;
import civilisation.TurtleGenerator;



/** 
 * The environment of the MAS
 * @version 1.0 - 2/2013
*/


@SuppressWarnings("serial")
public class World extends TKEnvironment
{
	
	private static World instance;
	File carte;
	
	private int tick = 0;
	int x , y;

	
	public World() 
	{
		World.instance = this;
		
	}
	
	public static World getInstance()
	{
		return World.instance;
	}
	
	public void activate()
	{	
		super.activate();
		


		new Initialiseur(); //Initialize simulation
		
		x = Integer.parseInt(Initialiseur.getChamp("Largeur", new File(Configuration.pathToRessources + "/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);
       	y = Integer.parseInt(Initialiseur.getChamp("Hauteur", new File(Configuration.pathToRessources + "/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);

		
		/*Reglages sur les civilisations*/
		for (int i = 0; i < Configuration.civilisations.size(); i++){
			Configuration.civilisations.get(i).postWorldSetup();
		}
		
		/*Init pheromons*/
		for (int i = 0 ; i < Configuration.itemsPheromones.size() ; i++ ) {
			this.addFlavor(Configuration.itemsPheromones.get(i).getNom());
		}
		
		
		//System.out.println(Configuration.environnementACharger);

		if (Configuration.environnementACharger != null){
		   //System.out.println("Chargement de l'environnement");
	       HashMap<Integer,Terrain> typeTerrains = new HashMap<Integer,Terrain>();
	       ArrayList<String[]> listeTerrains = Initialiseur.getListeChamp("Terrain", new File(Configuration.pathToRessources + "/environnements/"+Configuration.environnementACharger+Configuration.getExtension()));
	       for (int i = 0; i < listeTerrains.size(); i++){
			   //System.out.println("hash "+i+" "+listeTerrains.get(i)[0]+" "+Configuration.getTerrainByName(listeTerrains.get(i)[0]));

	    	   typeTerrains.put(i,Configuration.getTerrainByName(listeTerrains.get(i)[0]));
			   //System.out.println(typeTerrains.get(i) + typeTerrains.get(i).getCouleur().toString());
	       }
	       
	       ArrayList<String[]> terrains = Initialiseur.getListeChamp("Rang", new File(Configuration.pathToRessources + "/environnements/"+Configuration.environnementACharger+Configuration.getExtension()));
	       for (int i = 0; i < x; i++){
	    	   for (int j = 0; j < y; j++){
	    		   Terrain t = typeTerrains.get(Integer.parseInt(terrains.get(y-j-1)[i]));
	    		   this.getPatch(i,j).setColor(t.getCouleur());
	    		   for (int k = 0 ; k < t.getPheromones().size() ; k++) {
		    		   this.getPatch(i,j).dropPheromone(t.getPheromones().get(k).getNom() , t.getPheroInitiales().get(k).floatValue());
	    		   }
	    	   }
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
		

		//System.out.println("---End World Setup---"); 

	}

	
	/*
	 * Ajoute une pheromone (utile pour le controle de territoire)
	 */
	public void addFlavor(String nom)
	{
		//TODO
		getPheromone(nom, 0, 0); 
	}

	@Override
	public void update() {
		
	tick++;
		
		if (tick == 1) {
		       /*Install starting civilizations*/
		       ArrayList<String[]> listeCivs = Initialiseur.getListeChamp("Civilisation", new File(Configuration.pathToRessources + "/environnements/"+Configuration.environnementACharger+Configuration.getExtension()));
		       for (int i = 0; i < listeCivs.size(); i++){
		    	   
		    	   int u = Integer.parseInt(listeCivs.get(i)[1]);
		    	   int v = Integer.parseInt(listeCivs.get(i)[2]);

		    	   Communaute c = new Communaute(Configuration.getCivilisationByName(listeCivs.get(i)[0]));
		    	   TurtleGenerator.getInstance().createTurtle(c);
		    	   c.moveTo(u, -v);

		       }
		}

		
		/* Periodic actions of the environment*/ 
		if (tick %150 == 0)
		{
			for (int xx = 0; xx < x; xx++)
			{
				for (int yy = 0; yy < y; yy++)
				{
					Terrain t = Configuration.couleurs_terrains.get(this.getPatch(xx, yy).getColor());
					for (int i = 0 ; i < t.getPheroCroissance().size() ; i++) {
						this.getPatch(xx,yy).dropPheromone(t.getPheromones().get(i).getNom() , t.getPheroInitiales().get(i).floatValue());
					}
					
					Pheromone ph = this.getPheromone("passage");
					float phVal = ph.get(xx, yy);
					//if (phVal >= 100)  System.out.println(phVal);
					if(phVal > Configuration.EffacementRoute)
					{
						ph.set(xx, yy, phVal - Configuration.EffacementRoute);
					}
					else
					{
						ph.set(xx, yy, 0);
					}
					phVal = ph.get(xx, yy);
					if(this.getPatch(xx,yy).isMarkPresent("Route") && phVal< Configuration.EffacementRoute)
					{
						this.getPatch(xx, yy).getMark("Route");
					}
				}
			}
		}
	}
	
	
	
	
	
	/*----------------------GETTERS/SETTERS---------------------------*/
	

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}		

	
	
	
	
}
