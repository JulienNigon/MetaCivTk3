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

import civilisation.effects.Effect;
import civilisation.group.GroupModel;
import civilisation.individu.cognitons.*;
import civilisation.individu.decisionMaking.DecisionMaker;
import civilisation.individu.decisionMaking.MaxWeightDecisionMaker;
import civilisation.individu.decisionMaking.MaxWeightOx2_DecisionMaker;
import civilisation.individu.decisionMaking.WeightedStochasticDecisionMaker;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inventaire.Objet;
import civilisation.world.Terrain;



public class Initialiseur {

	HashMap<String, TypeCogniton> listeCognitons;
	HashMap<String, TypeCulturon> listCloudCognitons;
	HashMap<String, NPlan> listePlans;
	HashMap<Color, Terrain> couleurs_terrains; //TODO : Gerer le cas ou la meme couleur est utilisee pour deux terrains
	final int passabiliteParDefaut = 30;

	public Initialiseur(){
		
		listeCognitons = new HashMap<String, TypeCogniton>();
		listCloudCognitons = new HashMap<String, TypeCulturon>();
		listePlans = new HashMap<String, NPlan>();
		couleurs_terrains = new HashMap<Color, Terrain>();
		ArrayList<TypeCogniton> cognitonsDeBase = new ArrayList<TypeCogniton>();
		ArrayList<TypeCogniton> tousLesCognitons = new ArrayList<TypeCogniton>();
		ArrayList<TypeCulturon> allCloudCogniton = new ArrayList<TypeCulturon>();
		ArrayList<NPlan> tousLesPlans = new ArrayList<NPlan>();

		String nom;
		


		
		File[] filesAttributes = new File(Configuration.pathToRessources + "/attributes").listFiles();
		ArrayList<String> attributesNames = new ArrayList<String>();
		ArrayList<Double> attributesStartingValues = new ArrayList<Double>();
		for (File file : filesAttributes) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			    if (file.isFile()) {
			    	String name = getChamp("Name" , file)[0];
			    	attributesNames.add(name);
			    	Double startingValue = Double.parseDouble(getChamp("Starting value" , file)[0]);
			    	attributesStartingValues.add(startingValue);
			    }
			}
		}	
		Configuration.attributesNames = attributesNames;
		Configuration.attributesStartingValues = attributesStartingValues;
		
		
		File[] filesPhero = new File(Configuration.pathToRessources + "/itemPheromones").listFiles();
		ArrayList<ItemPheromone> phero = new ArrayList<ItemPheromone>();
		for (File file : filesPhero) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			    if (file.isFile()) {
			    	nom = getChamp("Nom" , file)[0];
			    	phero.add(new ItemPheromone(nom));
			    }
			}
		}	
		Configuration.itemsPheromones = phero;
		
		File[] filesTerrains = new File(Configuration.pathToRessources + "/terrains").listFiles();
		ArrayList<Terrain> terrains = new ArrayList<Terrain>();
		for (File file : filesTerrains) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
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
		File[] files = new File(Configuration.pathToRessources + "/cognitons").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	listeCognitons.put(nom , new TypeCogniton());
		    	TypeCogniton cogni = listeCognitons.get(nom);
		    	cogni.setNom(nom);
		    	cogni.setDescription(getChamp("Description" , file)[0]);
		    	cogni.setStartChance(Integer.parseInt(getChamp("StartChance" , file)[0]));
		    	cogni.setType(TypeDeCogniton.toType( getChamp("Type" , file)[0]));
		    	if (getChamp("Initial" , file)[0].equals("1")){
		    		cognitonsDeBase.add(cogni);
			    	cogni.setRecuAuDemarrage(true);
		    	}
		    	for (int i = 0; i < TypeCogniton.nHues; i++) {
		    		if (getChamp("Hue" + i , file) != null) {
		    			cogni.getHues()[i] = Integer.parseInt(getChamp("Hue" + i , file)[0]);
		    		}
		    	}
		    	//Load triggering attributes
		       	ArrayList<String[]> triggers = Initialiseur.getListeChamp("Trigger", file);
		       	for(int i = 0 ; i < triggers.size(); i++) {
		       		Object[] trig = new Object[3];
		       		trig[0] = cogni;
		       		trig[1] = Double.parseDouble(triggers.get(i)[1]);
		       		trig[2] = Integer.parseInt(triggers.get(i)[2]);
		       		if(Configuration.attributesTrigerringValues.get(triggers.get(i)[0]) == null) { 
		       			Configuration.attributesTrigerringValues.put(triggers.get(i)[0] , new ArrayList<Object[]>());
		       			}
		       		Configuration.attributesTrigerringValues.get(triggers.get(i)[0]).add(trig);
		       		
		       		//Now we add trigger to cognitons to keep the model easy to understand
		       		trig = new Object[3];
		       		trig[0] = triggers.get(i)[0];
		       		trig[1] = Double.parseDouble(triggers.get(i)[1]);
		       		trig[2] = Integer.parseInt(triggers.get(i)[2]);
		       		cogni.getTriggeringAttributes().add(trig);
		       		
	       		
		       	}
		    	
		    	tousLesCognitons.add(cogni);
		    }
			}
		}
		
		files = new File(Configuration.pathToRessources + "/cloudCognitons").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
			//System.out.println("Load culturon : " + file.getName());
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	listCloudCognitons.put(nom , new TypeCulturon());
		    	TypeCulturon cogni = listCloudCognitons.get(nom);
		    	cogni.setNom(nom);
		    	cogni.setDescription(getChamp("Description" , file)[0]);
		    	cogni.setType(TypeDeCogniton.toType( getChamp("Type" , file)[0]));
		    	if (getChamp("Initial" , file)[0].equals("1")){
		    		cognitonsDeBase.add(cogni);
			    	cogni.setRecuAuDemarrage(true);
		    	}
		    	for (int i = 0; i < TypeCogniton.nHues; i++) {
		    		if (getChamp("Hue" + i , file) != null) {
		    			cogni.getHues()[i] = Integer.parseInt(getChamp("Hue" + i , file)[0]);
		    		}
		    	}
		    	allCloudCogniton.add(cogni);
		    }
			}
		}	

		
		Configuration.effets = new ArrayList<Effect>();
		files = new File(Configuration.pathToRessources + "/effects").listFiles();
		if(files != null)
		{
			
			for(File file : files)
			{
				if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension()))
				{
				    if (file.isFile()) 
				    {
				    	nom = Initialiseur.getChamp("Nom" , file)[0];
				    	Effect e = new Effect();
				    	e.setName(nom);
				    	e.setDescription(getChamp("Description" , file)[0]);
				    	e.setTarget(getChamp("Cible" , file)[0]);
				    	e.setType(Integer.parseInt(getChamp("Type" , file)[0]));
				    	e.setVarget(getChamp("NomCible" , file)[0]);
				    	e.setValue(Double.parseDouble(getChamp("Valeur" , file)[0]));
				    	if(getChamp("Permanence" , file)[0].equals("true"))
				    	{
				    		e.setPermanent(true);
				    	}
				    	else
				    	{
				    		e.setPermanent(false);
				    	}
				    	e.setActivation(Integer.parseInt(getChamp("Activation" , file)[0]));
	
				    	Configuration.effets.add(e);
				    }
				}	
			}
		}
		
		Configuration.objets = new ArrayList<Objet>();
		files = new File(Configuration.pathToRessources + "/objets").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	Objet o = new Objet();
		    	o.setNom(nom);
		    	o.setDescription(getChamp("Description" , file)[0]);
		    	o.setIconeFromString(getChamp("Icone", file)[0]);
		    	ArrayList<String[]> eff = Initialiseur.getListeChamp("Effects", file);
				
				int i = 0;
				if(eff.size() > 0)
				{
					while(i < eff.get(0).length && eff.get(0)[i] != null)
					{
						Effect ef = Configuration.getEffectByName(eff.get(0)[i]);
			    		if(ef != null)
			    		{
			    			o.addEffect(ef);
			    		}
			    		++i;
					}
				}
				
				
				
		    	Configuration.objets.add(o);
		    }
			}
		}	
			
		/*Chargement des recettes
		 * 
		 */
		
		files = new File(Configuration.pathToRessources + "/objets").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
		    if (file.isFile()) {
		    	nom = Initialiseur.getChamp("Nom" , file)[0];
		    	
		    	ArrayList<String[]> rec = Initialiseur.getListeChamp("Objets", file);
		    	ArrayList<String[]> nbre = Initialiseur.getListeChamp("Nombre", file);
		    	
				int i = 0;
				if(rec.size() > 0)
				{
					int nb; 
					while(i < rec.get(0).length && rec.get(0)[i] != null)
					{
						
						nb = Integer.parseInt(nbre.get(0)[i]);
						Configuration.getObjetByName(nom).addItemRecipe(rec.get(0)[i], nb);
			    		++i;
					}
				}
				
			
		    }
			}
		}	
			
		

       	/*Preparation d'un jeu d'actions, pour pouvoir facilement les manipuler dans le reste du programme*/
		loadActions();
     /*  	Class action;
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
		}*/
		
		
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
		    		Configuration.initiatePlan = listePlans.get(nom);
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
		    	if (Initialiseur.getChamp("Scattered" , file) != null)
		    		civ.setScatteredModifier(Integer.parseInt(Initialiseur.getChamp("Scattered" , file)[0]));
		    	String[] HSB = getChamp("Couleur" , file);
		    	civ.setCouleur(Color.getHSBColor((float)Double.parseDouble(HSB[0]), (float)Double.parseDouble(HSB[1]), (float)Double.parseDouble(HSB[2])));
		       
		    	ArrayList<String[]> list = Initialiseur.getListeChamp("Cogniton", file);
		       	ArrayList<TypeCogniton> cognis = new ArrayList<TypeCogniton>();		      
		       	for (int i = 0 ; i < list.size(); i++){
		       		cognis.add(Configuration.getCognitonByName(list.get(i)[0]));
		       	}
		       	civ.setStartingCognitons(cognis);
		    	Configuration.civilisations.add(civ);
		    	}
			}
		}	
		
	}
	
	/**
	 * Return the value of the first parameter field encountered
	 * @param field : the field to read
	 * @param f : the file where the research must be done
	 * @return An array containing all field value for the first field encountered (with the name "field")
	 */
	static public String[] getChamp(String field ,  File f){
		
		 Scanner scanner;
		try {
			scanner = new Scanner(new FileReader(f));
			 String str = null;
			 while (scanner.hasNextLine()) {
			     str = scanner.nextLine();
			     if(str.split(" : ")[0].equals(field)){
			    	 return str.split(" : ")[1].split(",");
			     }
			 }
			 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public static void readParameters() {
		
		//Initialize decisions makers
		ArrayList<DecisionMaker> decisionMakers = new ArrayList<DecisionMaker>();
		decisionMakers.add(new WeightedStochasticDecisionMaker(null));
		decisionMakers.add(new MaxWeightDecisionMaker(null));
		decisionMakers.add(new MaxWeightOx2_DecisionMaker(null));
		Configuration.allDecisionMakers = decisionMakers;
		
		//System.out.println("Reading parameters...");
		File params = new File(Configuration.pathToRessources + "/parametres"+Configuration.getExtension());
		if (params.exists()){
	       	String s = getChamp("Carte", params)[0];
	    //   	System.out.println(s);
	       	if (!s.equals("AUCUNE")){
	    		File carte = new File(Configuration.pathToRessources + "/environnements/"+s);
	    		if (carte.isFile()){
	//    			System.out.println("Loading map : "+s);
	    			Configuration.environnementACharger = s.split("\\.")[0];
	    		}
	       	}
	       	String[] str = getChamp("DecisionMaker", params);
	       	if (str == null) {
	       		Configuration.decisionMaker = new WeightedStochasticDecisionMaker(null);
	       	} else {
	       		for (int i = 0 ; i < Configuration.allDecisionMakers.size() ; i++) {
	       			if (Configuration.allDecisionMakers.get(i).toString().equals(str[0])) {
	       				Configuration.decisionMaker = Configuration.allDecisionMakers.get(i);
	       			}
	       		}
	       	}
		}  
		
		//TODO
		//Configuration.decisionMaker = new WeightedStochasticDecisionMaker(null);
		//Configuration.decisionMaker = new MaxWeightDecisionMaker(null);

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
			    	 if(str.split(" : ").length > 1)
			    	 {
			    		 String list = str.split(" : ")[1];
				    	 liste.add(list.split(","));
			    	 }
			    	
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
					     if (ancienneAction != null && (a == null || a.internActionsAreLinked())){
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
	}

	/**
	 * Load every actions in the parameter file
	 */
	private void loadActions()
	{
		File folder = new File(Configuration.pathToRessources+"/actions");
	//	System.out.println(Configuration.pathToRessources+"/actions");
		Configuration.actions = new ArrayList<Action>();
		try
		{
			URL fileURL = folder.toURI().toURL();
			URL urls [] = { fileURL};  
			URLClassLoader ucl = new URLClassLoader(urls);
				
			loadActionsRecursif(ucl, folder, "");
		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
	}
	
	/**
	 * Recursive call to load all actions
	 */
	private void loadActionsRecursif(URLClassLoader loader,File folder,String path)
	{
		File[] files = folder.listFiles();
		if(files != null && files.length > 0)
		{
			for (File f : files)
			{
				if (f.isDirectory())
				{
			//	System.out.println("File : " + f.getName());
				loadActionsRecursif(loader,f, path+f.getName()+".");
				}
				else
				{
					try
					{
						
						Action a = null;
						Class<?> c = loader.loadClass(path+f.getName().substring(0, f.getName().length()-6)); // TODO peut être a modifier le   "substring(0, f.getName().length()-6)"  qui correspond au .class 
						if (Action.class.isAssignableFrom(c))
						{
							Action action = (Action) c.newInstance();
							Configuration.actions.add(action);
						}
					}
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
					catch (InstantiationException e)
					{
						e.printStackTrace();
					}
					catch (IllegalAccessException e)
					{
						e.printStackTrace();
					}
//=======

				}
			}
		}
		
	}
	

	


}
