package civilisation.world; 

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import turtlekit.kernel.Patch;
import turtlekit.kernel.TKEnvironment;
import turtlekit.kernel.Turtle;
import turtlekit.mle.AbstractMLEAgent;
import turtlekit.pheromone.Pheromone;
import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.Initialiseur;
import civilisation.TurtleGenerator;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;



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
	
	//For output file
	PrintWriter outTickSecond;
	double oldTime;
	double startTime;
	int oldAgentNumber;
	int startTick;
	ArrayList<EvoluPatch> evolution = new ArrayList<EvoluPatch>();
	EvoluPatch[][] patchs;
	
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
		
		initExportData();

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
		   	boolean test = false;
	       	for(int j = 0; j < Configuration.terrains.size(); ++j)
			{
				for(int k = 0; k < Configuration.terrains.get(j).getPheromones().size();++k)
				{
					if(Configuration.terrains.get(j).getPheromones().get(k).getNom().equals("fertilite"))
					{
						test = true;
					}
				}
			}
	       	
	       	Double max = 0.0;
	       	if(test)
	       	{
	       		for(int j = 0; j < this.x;++j)
		       	{
	 			   for(int k = 0; k < this.y;++k)
	 			   {
	 				  for(int l = 0;l < Configuration.couleurs_terrains.get(this.getPatch(j, k).getColor()).getPheromones().size();++l)
	 				  {
		 					if(Configuration.couleurs_terrains.get(this.getPatch(j, k).getColor()).getPheromones().get(l).equals("fertilite"))
		 					{
		 						if(Configuration.couleurs_terrains.get(this.getPatch(j, k).getColor()).pheroInitiales.get(l) > max)
		 						{
		 							max = Configuration.couleurs_terrains.get(this.getPatch(j, k).getColor()).pheroInitiales.get(l);
		 						}
		 					}
	 				  }
	 			   }
		       	}
	       	}
	       	
		       for (int i = 0; i < listeCivs.size(); i++){
		    	   
		    	   int u = Integer.parseInt(listeCivs.get(i)[1]);
		    	   int v = Integer.parseInt(listeCivs.get(i)[2]);

		    	   Communaute c = new Communaute(Configuration.getCivilisationByName(listeCivs.get(i)[0]));
		    	   TurtleGenerator.getInstance().createTurtle(c);
		    	   c.moveTo(u, -v);
		    	   if(test)
		    	   {
		    		   int tempo = 0;
		    		   for(int j = 0; j < x;++j)
		    		   {
		    			   for(int k = 0; k < y; ++k)
		    			   {
		    				   for(int l = 0;l < Configuration.couleurs_terrains.get(this.getPatch(j, k).getColor()).getPheromones().size();++l)
		 	 				  {
		 		 					if(Configuration.couleurs_terrains.get(this.getPatch(j, k).getColor()).getPheromones().get(l).equals("fertilite"))
		 		 					{
		 		 						if(Configuration.couleurs_terrains.get(this.getPatch(j, k).getColor()).pheroInitiales.get(l) == max)
		 		 						{
		 		 							if(tempo == i)
		 		 							{
		 		 								c.moveTo(j, k);
		 		 							}
		 		 							else
		 		 							{
		 		 								tempo++;
		 		 							}
		 		 						}
		 		 					}
		 	 				  }
		    			   }
		    		   }
		    	   }

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
						this.getPatch(xx,yy).dropPheromone(t.getPheromones().get(i).getNom() , t.getPheroCroissance().get(i).floatValue());
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
		
		exportData();
	}
	
	private void initExportData() {
		try {
			this.outTickSecond = new PrintWriter(new FileWriter(System.getProperty("user.dir") + "/" + "tickSecond" + ".csv"));
			outTickSecond.println("agents,ticks");
			outTickSecond.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void exportData() {
		java.lang.System.currentTimeMillis(); 
		try {
				FileWriter fstream = new FileWriter(System.getProperty("user.dir") + "/" + "tickSecond" + ".csv", true);
				BufferedWriter out = new BufferedWriter(fstream);
				
				if (oldAgentNumber != this.getTurtlesWithRoles("Humain").size()) {
					if (oldAgentNumber != 0) {
						double time;
						time = (java.lang.System.currentTimeMillis() - startTime) / (tick - startTick); 
				    	out.write(oldAgentNumber + "," + time + "\n");
					}
					oldAgentNumber = this.getTurtlesWithRoles("Humain").size();
					startTick = tick;
					startTime = java.lang.System.currentTimeMillis();
				}

		    	out.close();
		    } catch (IOException e) 
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Patch getPatchAt(int xx, int yy) {
		return this.getPatch(xx, yy);
	}
	
	
	
	/*----------------------GETTERS/SETTERS---------------------------*/
	

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}		

	public ArrayList<Humain> getHumansWithTag(String tag) {
		List<Turtle> turtles = this.getTurtlesWithRoles("Humain");
		ArrayList<Humain> humans = new ArrayList<Humain>();
		for (Turtle turtle : turtles) {
			if (((Humain)turtle).getEsprit().ownTag(tag))
			humans.add((Humain)turtle);
		}
		
		return humans;
	}
	
	public ArrayList<Humain> getHumansWithCogniton(TypeCogniton cogni) {
		List<Turtle> turtles = this.getTurtlesWithRoles("Humain");
		ArrayList<Humain> humans = new ArrayList<Humain>();
		for (Turtle turtle : turtles) {
			if (((Humain)turtle).getEsprit().ownCogniton(cogni))
			humans.add((Humain)turtle);
		}
		
		return humans;
	}
	
	
	private void calculerEvolutionPatch() {
		
		for(int i = 0; i < this.x ; i++)
		{
			for(int j = 0; j < this.y; j++)
			{
				EvoluPatch evo = new EvoluPatch(i,j,this.getPatch(i, j));
				this.patchs[i][j] = evo;
			}
		}
		for(int i = 0; i < this.x ; i++)
		{
			for(int j = 0; j < this.y; j++)
			{
				List<Patch> liste = this.getPatch(i, j).getNeighbors(1, false);
				ArrayList<Patch> list = new ArrayList<Patch>();
				for(int k = 0; k < liste.size();k++)
				{
					list.add(liste.get(k));
				}
				this.patchs[i][j].calculEquations(list);
				this.InsererEvo(this.patchs[i][j]);
			}
		}
	}
	
	public void InsererEvo(EvoluPatch patch)
	{
		int i = 0;
		if(this.evolution.size() == 0)
		{
			this.evolution.add(patch);
		}
		else
		{
			while(i < this.evolution.size() && this.evolution.get(i).getLimite() < patch.getLimite())
			{
				++i;
			}
			this.evolution.add(i, patch);
		}
		
		
	}
	
	
}
