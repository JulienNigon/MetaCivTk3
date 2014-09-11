package civilisation;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import civilisation.effects.Effect;
import civilisation.group.GroupModel;
import civilisation.individu.cognitons.TypeCulturon;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.individu.decisionMaking.DecisionMaker;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inventaire.Objet;
import civilisation.world.Terrain;

/** 
 * Contain all major data for running the simulation
 * @author DTEAM
 * @version 1.01 - 2/2013
*/



public class Configuration {

	public static String versionNumber = "1.60";
	public static String versionName = "Tuatha";
	
	/*Attributes*/
	public static ArrayList<String> attributesNames;
	public static ArrayList<Double> attributesStartingValues;
	public static HashMap<String,ArrayList<Object[]>> attributesTrigerringValues;
	
	/*Decision maker*/
	public static DecisionMaker decisionMaker;
	public static ArrayList<DecisionMaker> allDecisionMakers;

	/*All starting cognitons*/
	public static ArrayList<TypeCogniton> cognitonsDeBase;
	
	/*All cognitons*/
	public static ArrayList<TypeCogniton> cognitons;
	
	/*All cloud cognitons*/
	public static ArrayList<TypeCulturon> cloudCognitons;
	
	/*all effects*/
	public static ArrayList<Effect> effets;
	
	/*All plans*/
	public static ArrayList<NPlan> plans;
	public static NPlan initiatePlan;
	public static NPlan autoPlan;

	/*Group model*/
	public static ArrayList<GroupModel> groups;
	
	/*Tous les objets*/
	public static ArrayList<Objet> objets;
	
	/*Toutes les action disponibles*/
	public static ArrayList<Action> actions;
	
	/*Toutes les civilisations*/
	public static ArrayList<Civilisation> civilisations;
	
	/*Item pheromones*/
	public static ArrayList<ItemPheromone> itemsPheromones;
	
	/*Terrains*/
	public static ArrayList<Terrain> terrains;
	
	/*Associations couleurs-terrains*/
	public static HashMap<Color, Terrain> couleurs_terrains;
	
	/*Environnement ___ charger*/
	public static String environnementACharger;
	
	/*Path to icon*/
	public static String pathToIcon = "/bin/civilisation/inspecteur/icones";
	
	/*Path to ressources*/
	public static String pathToRessources = "/civilisation/ressources";
	
	/*Modificateurs de l'esprit*/
	public static Double facteurHereditaireMemes = 1.0;
	
	public static Double facteurHereditaireTraits = 1.0;
	
	public static Double facteurDecouverteSkills = 1.0;
	
	public static Double facteurDecouverteMemes = 1.0;
	
	public static Double facteurApparitionDeNouveauxTraits = 1.0;
	

	
	/*Passages minimaux pour une route*/
	public static Integer passagesPourCreerRoute = 5;
	public static Integer EffacementRoute = 1;
	public static final int VisionRadius = 15;
	
	
	/**
	 * Vitesse de deplacement sur les patchs
	 */
	public static Integer VitesseEstimeeParDefaut = 25;
	
	public static Integer VitesseSurPlaine = 20;
	
	public static Integer VitesseSurRoute = 2;
	
	public static Integer VitesseSurForet = 40;
	
	public static Integer VitesseSurLittoral = 40;
	
	public static Integer VitesseSurBanquise = 40;
	
	public static Integer VitesseSurColline = 60;
	
	public static Integer VitesseSurMontagne = 80;
	
	public static Integer VitesseSurEau = 900;
	
	public static Integer VitesseSurDesert = 40;
	
	public static Integer VitesseCheval = 10;
	
	public static Integer TauxDressageCheval = 25;
	
	public static Integer maxAgents = 50000;

	public static Double seuilEmergenceForet = -40.;

	public static void addCogniton(TypeCogniton nouveauCogniton) {
		cognitons.add(nouveauCogniton);
	}
	
	public static void addCloudCogniton(TypeCulturon newCloudCogniton) {
		cloudCognitons.add(newCloudCogniton);
	}
	
	public static void addPlan(NPlan nouveauPlan) {
		plans.add(nouveauPlan);
	}
	
	public static void addCognitonDeBase(TypeCogniton nouveauCogniton) {
		cognitonsDeBase.add(nouveauCogniton);
	}
	
	public static void removeCognitonDeBase(TypeCogniton c){
		for (int i = 0 ; i < cognitonsDeBase.size(); i++){
			if (cognitonsDeBase.get(i).equals(c)){
				cognitonsDeBase.remove(i);
				break;
			}
		}
	}
	
	public static ItemPheromone getPheromoneByName(String s){
		for (int i = 0 ; i < itemsPheromones.size(); i++){
			if (itemsPheromones.get(i).getNom().equals(s)){
				return(itemsPheromones.get(i));
			}
		}
		return null;
	}
	
	public static Terrain getTerrainByName(String s){
		for (int i = 0 ; i < terrains.size(); i++){
			if (terrains.get(i).getNom().equals(s)){
				return(terrains.get(i));
			}
		}
		return null;
	}

	public static Objet getObjetByName(String s){
		for (int i = 0 ; i < objets.size(); i++){
			if (objets.get(i).getNom().equals(s)){
				return(objets.get(i));
			}
		}
		System.exit(-1);
		return null;
	}
	
	public static Action getActionByName(String s){
		for (int i = 0 ; i < actions.size(); i++){
			String[] tab = actions.get(i).getSimpleName().split("\\.");
			if (tab[tab.length - 1].equals(s)){
				return(actions.get(i));
			}
		}
		return null;
	}
	
	public static TypeCogniton getCognitonByName(String s){
		for (int i = 0 ; i < cognitons.size(); i++){
			if (cognitons.get(i).getNom().equals(s)){
				return(cognitons.get(i));
			}
		}
		return null;
	}
	
	public static Civilisation getCivilisationByName(String s){
		for (int i = 0 ; i < civilisations.size(); i++){
			if (civilisations.get(i).getNom().equals(s)){
				return(civilisations.get(i));
			}
		}
		return null;
	}
	
	public static GroupModel getGroupModelByName(String s){
		for (int i = 0 ; i < groups.size(); i++){
			if (groups.get(i).getName().equals(s)){
				return(groups.get(i));
			}
		}
		return null;
	}
	
	public static int getAttributeIndexByName(String s){
		for (int i = 0 ; i < attributesNames.size(); i++){
			if (attributesNames.get(i).equals(s)){
				return i;
			}
		}
		return -1;
	}
	
	public static String getExtension() {
		return ".metaciv";
	}
	
	public static ImageIcon getIcon (String name) {
		try {
			return new ImageIcon(ImageIO.read(Configuration.class.getResourceAsStream("/civilisation/inspecteur/icones/" + name)));
		} catch (IOException e) {
			return null;
		}
	}
	
	public static Image getImage (String name) {
		try {
			return ImageIO.read(Configuration.class.getResourceAsStream("/civilisation/inspecteur/icones/" + name));
		} catch (IOException e) {
			return null;
		}
	}
	
	
	public static Effect getEffectByName(String name)
	{
		int i = 0;
		if(effets.size()  > 0)
		{
			
			while(i < effets.size() && !effets.get(i).getName().equals(name))
			{
				i++;
				
			}
			if(i < effets.size())
			{
				System.out.println(" effet : "+ effets.get(i).getName());
				return effets.get(i);
			}
			else
			{
				System.out.println("null");
				return null;
			}
		}
		else
		{
			System.out.println("null");
			return null;
		}
		
	}

	public static void addObjetUnique(Objet o) {
		// TODO Auto-generated method stub
		int i = 0;
		while(i < objets.size() && !objets.get(i).getNom().equals(o.getNom()))
		{
			++i;
		}
		if(i < objets.size())
		{
			objets.remove(i);
		}
		objets.add(o);
	}

	public static void addEffectUnique(Effect temp) {
		// TODO Auto-generated method stub
		int i = 0;
		if(temp != null)
		{
			while(i < effets.size() && !effets.get(i).getName().equals(temp.getName()))
			{
				++i;
			}
			if(i < effets.size())
			{
				effets.remove(i);
			}
			effets.add(temp);
		}
		
	}
	

}

