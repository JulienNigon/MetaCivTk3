package civilisation;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import civilisation.group.GroupModel;
import civilisation.individu.cognitons.*;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inventaire.Objet;
import civilisation.world.Terrain;



public class Initialiseur {

	HashMap<String, NCogniton> listeCognitons;
	HashMap<String, Culturon> listCloudCognitons;
	HashMap<String, NPlan> listePlans;
	HashMap<Color, Terrain> couleurs_terrains; //TODO : Gerer le cas ou la meme couleur est utilisee pour deux terrains
	final int passabiliteParDefaut = 30;

	public Initialiseur(){
		
		listeCognitons = new HashMap<String, NCogniton>();
		listCloudCognitons = new HashMap<String, Culturon>();
		listePlans = new HashMap<String, NPlan>();
		couleurs_terrains = new HashMap<Color, Terrain>();
		ArrayList<NCogniton> cognitonsDeBase = new ArrayList<NCogniton>();
		ArrayList<NCogniton> tousLesCognitons = new ArrayList<NCogniton>();
		ArrayList<Culturon> allCloudCogniton = new ArrayList<Culturon>();
		ArrayList<NPlan> tousLesPlans = new ArrayList<NPlan>();

		String nom;
		
		
		//System.out.println("Attributes loading...");
		File[] filesAttributes = new File(Configuration.pathToRessources + "/attributes").listFiles();
		ArrayList<String> attributesNames = new ArrayList<String>();
		ArrayList<Integer> attributesStartingValues = new ArrayList<Integer>();
		for (File file : filesAttributes) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
				//System.out.println("Load attribute : " + file.getName());
			    if (file.isFile()) {
			    	String name = getChamp("Name" , file)[0];
			    	attributesNames.add(name);
			    	Integer startingValue = Integer.parseInt(getChamp("Starting value" , file)[0]);
			    	attributesStartingValues.add(startingValue);
			    }
			}
		}	
		Configuration.attributesNames = attributesNames;
		Configuration.attributesStartingValues = attributesStartingValues;
		
		
		//System.out.println("Chargement des pheromones");
		File[] filesPhero = new File(Configuration.pathToRessources + "/itemPheromones").listFiles();
		ArrayList<ItemPheromone> phero = new ArrayList<ItemPheromone>();
		for (File file : filesPhero) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
				//System.out.println("Creation de la pheromone : " + file.getName());
			    if (file.isFile()) {
			    	nom = getChamp("Nom" , file)[0];
			    	phero.add(new ItemPheromone(nom));
			    }
			}
		}	
		Configuration.itemsPheromones = phero;
		
		//System.out.println("Chargement des terrains");
		File[] filesTerrains = new File(Configuration.pathToRessources + "/terrains").listFiles();
		ArrayList<Terrain> terrains = new ArrayList<Terrain>();
		for (File file : filesTerrains) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("Creation du terrain : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	Terrain t = new Terrain(nom);
		    	terrains.add(t);
		    	
		    	String[] HSB = getChamp("Couleur" , file);
		    	t.setCouleur(Color.getHSBColor((float)Double.parseDouble(HSB[0]), (float)Double.parseDouble(HSB[1]), (float)Double.parseDouble(HSB[2])));
		    	
		       	ArrayList<String[]> pheromonesLiees = Initialiseur.getListeChamp("Pheromone", file);
		       	for (int i = 0; i < pheromonesLiees.size(); i++){
		       		t.addPheromoneLiee(Configuration.getPheromoneByName(pheromonesLiees.get(i)[0]), Double.parseDouble(pheromonesLiees.get(i)[1]), Double.parseDouble(pheromonesLiees.get(i)[2]));
		       	}
		       	
		       	String [] passabilite = getChamp("Passabilite",file);
		       	if (passabilite != null){
		       		t.setPassabilite(Integer.parseInt(passabilite[0]));
		       	}
		       	else{
		       		t.setPassabilite(passabiliteParDefaut);
		       	}
		       	
		       	String [] infranchissable = getChamp("Infranchissable",file);
		       	if (infranchissable != null){
		       		t.setInfranchissable(Boolean.parseBoolean(infranchissable[0]));
		       	}
		       	else{
		       		t.setInfranchissable(false);
		       	}
		       	
		       	couleurs_terrains.put(t.getCouleur(), t);
		    }
		    }
		}	
		
		if (terrains.size() == 0){
			/*Si il n'y a aucun terrain disponible, un terrain pas defaut est cree*/
			Terrain tDefault = new Terrain("Default");
			terrains.add(tDefault);
			tDefault.setCouleur(Color.GREEN);
			tDefault.setPassabilite(passabiliteParDefaut);
       		tDefault.setInfranchissable(false);
	       	couleurs_terrains.put(tDefault.getCouleur(), tDefault);

		}
		
		Configuration.terrains = terrains;
		Configuration.couleurs_terrains = couleurs_terrains;		
		
   		Configuration.attributesTrigerringValues = new HashMap<String , ArrayList<Object[]>>();
		//System.out.println("Loading cognitons...");
		File[] files = new File(Configuration.pathToRessources + "/cognitons").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("\tLoad cogniton : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	listeCognitons.put(nom , new NCogniton());
		    	NCogniton cogni = listeCognitons.get(nom);
		    	cogni.setNom(nom);
		    	cogni.setDescription(getChamp("Description" , file)[0]);
		    	cogni.setStartChance(Integer.parseInt(getChamp("StartChance" , file)[0]));
		    	cogni.setType(TypeDeCogniton.toType( getChamp("Type" , file)[0]));
		    	if (getChamp("Initial" , file)[0].equals("1")){
		    		cognitonsDeBase.add(cogni);
			    	cogni.setRecuAuDemarrage(true);
		    	}
		    	for (int i = 0; i < NCogniton.nHues; i++) {
		    		if (getChamp("Hue" + i , file) != null) {
		    			cogni.getHues()[i] = Integer.parseInt(getChamp("Hue" + i , file)[0]);
		    		}
		    	}
		    	//Load triggering attributes
		       	ArrayList<String[]> triggers = Initialiseur.getListeChamp("Trigger", file);
		       	for(int i = 0 ; i < triggers.size(); i++) {
		       		Object[] trig = new Object[3];
		       		trig[0] = cogni;
		       		trig[1] = Integer.parseInt(triggers.get(i)[1]);
		       		trig[2] = Integer.parseInt(triggers.get(i)[2]);
		       		////System.out.println(trig[0] + " " + trig[1] + " " + trig[2]);
		       		if(Configuration.attributesTrigerringValues.get(triggers.get(i)[0]) == null) { Configuration.attributesTrigerringValues.put(triggers.get(i)[0] , new ArrayList<Object[]>()); }
		       		Configuration.attributesTrigerringValues.get(triggers.get(i)[0]).add(trig);
		       		
		       		//Now we add trigger to cognitons to keep the model easy to understand
		       		trig = new Object[3];
		       		trig[0] = triggers.get(i)[0];
		       		trig[1] = Integer.parseInt(triggers.get(i)[1]);
		       		trig[2] = Integer.parseInt(triggers.get(i)[2]);
		       		cogni.getTriggeringAttributes().add(trig);
		       		
	       		
		       	}
		    	
		    	tousLesCognitons.add(cogni);
		    }
			}
		}
		
		//System.out.println("Loading culturons...");
		files = new File(Configuration.pathToRessources + "/cloudCognitons").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("Load culturon : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	listCloudCognitons.put(nom , new Culturon());
		    	Culturon cogni = listCloudCognitons.get(nom);
		    	cogni.setNom(nom);
		    	cogni.setDescription(getChamp("Description" , file)[0]);
		    	cogni.setType(TypeDeCogniton.toType( getChamp("Type" , file)[0]));
		    	if (getChamp("Initial" , file)[0].equals("1")){
		    		cognitonsDeBase.add(cogni);
			    	cogni.setRecuAuDemarrage(true);
		    	}
		    	for (int i = 0; i < NCogniton.nHues; i++) {
		    		if (getChamp("Hue" + i , file) != null) {
		    			cogni.getHues()[i] = Integer.parseInt(getChamp("Hue" + i , file)[0]);
		    		}
		    	}
		    	allCloudCogniton.add(cogni);
		    }
			}
		}	

		
		//System.out.println("Chargement des objets d'inventaire");
		Configuration.objets = new ArrayList<Objet>();
		files = new File(Configuration.pathToRessources + "/objets").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("Chargement de : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	Objet o = new Objet();
		    	o.setNom(nom);
		    	o.setDescription(getChamp("Description" , file)[0]);
		    	o.setIconeFromString(getChamp("Icone", file)[0]);

		    	Configuration.objets.add(o);
		    }
			}
		}	
		

       	/*Preparation d'un jeu d'actions, pour pouvoir facilement les manipuler dans le reste du programme*/

       	Class action;
       	Configuration.actions = new ArrayList<Action>();
       	System.out.println(Configuration.pathToRessources + "../actions");
		File[] sourcesActions = new File(Configuration.pathToRessources + "/../actions").listFiles();
		for (File file : sourcesActions) {
		    if (file.isFile() && file.getName().endsWith(".java") && file.getName().charAt(1) == '_') {
		    	try {
					action = Class.forName("civilisation.individu.plan.action."+file.getName().split("\\.")[0]);
					//System.out.println("\tLoad action : " + action.getName());
					Configuration.actions.add((Action) action.newInstance());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		    }
		}
		
		
		/*On transmet les informations a la classe de configuration*/
		Configuration.cognitonsDeBase = cognitonsDeBase;
		Configuration.cognitons = tousLesCognitons;
		Configuration.cloudCognitons = allCloudCogniton;
		
		//System.out.println("Loading groups..");
		Configuration.groups = new ArrayList<GroupModel>();
		files = new File(Configuration.pathToRessources + "/groups").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("\tLoad group : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Name" , file)[0];
		    	GroupModel g = new GroupModel(nom);
		    	ArrayList<String[]> culturons = Initialiseur.getListeChamp("Culturon", file);
		    	for (int i = 0 ; i < culturons.size() ; i++) {
		    		g.addCulturonToRole(culturons.get(i)[0], Configuration.getCognitonByName(culturons.get(i)[1]));
		    	}

		    	Configuration.groups.add(g);
		    }
			}
		}	
		
		
		//System.out.println("Loading plans...");
		File[] filesPlans = new File(Configuration.pathToRessources + "/plans").listFiles();
		for (File file : filesPlans) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("Creation du plan : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	listePlans.put(nom , new NPlan());
		    	listePlans.get(nom).setNom(nom);
		       	ArrayList<String[]> actions = Initialiseur.getListeChamp("Action", file);
		       	setupPlans(listePlans.get(nom), file, 0, 0, null);
		       	listePlans.get(nom).setIsBirthPlan(Boolean.parseBoolean(Initialiseur.getChamp("Birth", file)[0]));
		       	listePlans.get(nom).setIsSelfPlan(Boolean.parseBoolean(Initialiseur.getChamp("Self", file)[0]));

		       /*	for (int i = 0; i < actions.size(); i++){
		       		listePlans.get(nom).addAction(actions.get(i));
		       		if (i > 0){
		       			listePlans.get(nom).getActions().get(i-1).setNextAction(listePlans.get(nom).getActions().get(i));
		       		}
		       	}*/
		    	
		    	if (listePlans.get(nom).getIsSelfPlan()) {
		    		Configuration.autoPlan = listePlans.get(nom);
		    	}
		    	else if (listePlans.get(nom).getIsBirthPlan()) {
		    		Configuration.birthPlan = listePlans.get(nom);
		    	}
			    tousLesPlans.add(listePlans.get(nom));
		    	


		       	
		    }
			}
		}	
		
		//System.out.println("Creating links...");
		files = new File(Configuration.pathToRessources + "/cognitons").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("Create links of : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	
		    	/*Les liens inter-cognitons (liens d'apprentissage)*/
		       	ArrayList<String[]> liste = Initialiseur.getListeChamp("Chaine", file);
		       	ArrayList<LienCogniton> liens = new ArrayList<LienCogniton>();		      
		       	for (int i = 0 ; i < liste.size(); i++){
		       		liens.add(new LienCogniton(listeCognitons.get(liste.get(i)[0]), Integer.parseInt(liste.get(i)[1])));
		       	}
	       		listeCognitons.get(nom).setLiens(liens);

		    	/*Les liens cognitons-plans (liens d'influence)*/
		       	liste = getListeChamp("Influence", file);
		       	ArrayList<LienPlan> liensP = new ArrayList<LienPlan>();		      
		       	for (int i = 0 ; i < liste.size(); i++){
		       		liensP.add(new LienPlan(listePlans.get(liste.get(i)[0]), Integer.parseInt(liste.get(i)[1])));
		       	}
	       		listeCognitons.get(nom).setLiensPlans(liensP);
		       	
		    	/*Les liens cognitons-plans debloques (liens conditionnels)*/
		       	liste = Initialiseur.getListeChamp("Permet", file);
		       	ArrayList<NPlan> plans = new ArrayList<NPlan>();		      
		       	for (int i = 0 ; i < liste.size(); i++){
			       	////System.out.println("Le nom qu'on trouve : " + liste.get(i)[0]);
			       	////System.out.println("Hach assoccie : " + listePlans.get(liste.get(i)[0]));
		       		plans.add(listePlans.get(liste.get(i)[0]));
		       	}
		       	////System.out.println("plans autorises : "+ nom + "  : "+plans.toString());
		       //	//System.out.println("array : "+ plans);

	       		listeCognitons.get(nom).setPlansAutorises(plans);
		    }
		}	
		}
		

		Configuration.plans = tousLesPlans;

		////System.out.println("Verification");	
		
		//printAllCognitons();
		
		/*Lecture des civilisations*/
		//System.out.println("Loading civilizations...");
		Configuration.civilisations = new ArrayList<Civilisation>();
		files = new File(Configuration.pathToRessources + "/civilisations").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("Load civilization : " + file.getName());
		    if (file.isFile()) {
		    	Civilisation civ = new Civilisation();
		    	civ.setNom(getChamp("Nom" , file)[0]);
		    	civ.setAgentsInitiaux(Integer.parseInt(Initialiseur.getChamp("Agents" , file)[0]));
		    	String[] HSB = getChamp("Couleur" , file);
		    	civ.setCouleur(Color.getHSBColor((float)Double.parseDouble(HSB[0]), (float)Double.parseDouble(HSB[1]), (float)Double.parseDouble(HSB[2])));
		       
		    	ArrayList<String[]> list = Initialiseur.getListeChamp("Cogniton", file);
		       	ArrayList<NCogniton> cognis = new ArrayList<NCogniton>();		      
		       	for (int i = 0 ; i < list.size(); i++){
		       		cognis.add(Configuration.getCognitonByName(list.get(i)[0]));
		       	}
		       	civ.setStartingCognitons(cognis);
		    	Configuration.civilisations.add(civ);
		    	}
			}
		}	
		
	}
	
	/*Retourne la valeur du premier champ passee en parametre rencontr___________________________*/
	static public String[] getChamp(String champ ,  File f){
		
		 Scanner scanner;
		try {
			scanner = new Scanner(new FileReader(f));
			 String str = null;
			 while (scanner.hasNextLine()) {
			     str = scanner.nextLine();
			     if(str.split(" : ")[0].equals(champ)){
			    	 return str.split(" : ")[1].split(",");
			     }
			 }
			 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public static void readParameters() {
		//System.out.println("Reading parameters...");
		File params = new File(Configuration.pathToRessources + "/parametres"+Configuration.getExtension());
		if (params.exists()){
	       	String s = getChamp("Carte", params)[0];
	       	if (!s.equals("AUCUNE")){
	    		File carte = new File(Configuration.pathToRessources + "/environnements/"+s);
	    		if (carte.isFile()){
	    			//System.out.println("Loading map : "+s);
	    			Configuration.environnementACharger = s.split("\\.")[0];
	    		}
	       	}
		}    
	}
	
	
	public static ArrayList<String[]> getListeChamp(String champ ,  File f){
		
		 Scanner scanner;
		 ArrayList<String[]> liste = new ArrayList<String[]>();
		 
		try {
			scanner = new Scanner(new FileReader(f));
			 String str = null;
			 while (scanner.hasNextLine()) {
			     str = scanner.nextLine();			     
			     if(str.split(" : ")[0].equals(champ)){
			    	 liste.add(str.split(" : ")[1].split(","));
			     }
			 }
			 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		return liste;
	}
	
	private void setupPlans(NPlan p , File f, int iteration, int ligne , Action a){
		
		 int ligneActuelle = 0;
	     Action nouvelleAction = null;
	     Action ancienneAction = null;
		 Scanner scanner;
		 String champ = "";
		 for (int i = 0; i < iteration; i++){
			 champ += "\t";
		 }
		 champ += "Action";
		 
		 ArrayList<String[]> liste = new ArrayList<String[]>();
		 
		try {
			scanner = new Scanner(new FileReader(f));
			 String str = null;
			 boolean recursionLancee = false;

			 while (scanner.hasNextLine()) {
				 int nTab = -1;
			     str = scanner.nextLine();
			     if (str.split("Action").length > 1){
				     nTab = str.split("Action")[0].length();
			     }

			     ligneActuelle++;
			     
			     if (ligneActuelle+1 > ligne){
				     if(str.split(" : ")[0].equals(champ) && nTab == iteration){
					     ancienneAction = nouvelleAction;
					     nouvelleAction = Action.actionFactory(str.split(" : ")[1].split(","));
					     if (ancienneAction != null){
					    	 ancienneAction.setNextAction(nouvelleAction);
					     }
					     if (a == null){
					    	 p.addAction(nouvelleAction);
					     }
					     else {
					    	 a.addSousAction(nouvelleAction);
					     }
				    	 recursionLancee = false;
				     }
				     else if(nTab > iteration && !recursionLancee){
				    	 recursionLancee = true;
				    	 setupPlans(p,f,iteration+1,ligneActuelle, nouvelleAction);
				     }
				     else if (nTab < iteration && nTab != -1){
				    	 break;
				     }
			     }

			     
			 }
			 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	       /*	for (int i = 0; i < actions.size(); i++){
   		listePlans.get(nom).addAction(actions.get(i));
   		if (i > 0){
   			listePlans.get(nom).getActions().get(i-1).setNextAction(listePlans.get(nom).getActions().get(i));
   		}
   	}*/
	}
	

	


}
