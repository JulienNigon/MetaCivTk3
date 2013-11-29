package civilisation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import civilisation.individu.cognitons.Culturon;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inventaire.Objet;
import civilisation.world.Terrain;
import civilisation.annotations.*;

/** 
 * Contain all major data for running the simulation
 * @author DTEAM
 * @version 1.0 - 2/2013
*/



public class Configuration {

	
	/*Attributes*/
	public static ArrayList<String> attributesNames;
	public static ArrayList<Integer> attributesStartingValues;
	public static HashMap<String,ArrayList<Object[]>> attributesTrigerringValues;
	
	/*All starting cognitons*/
	public static ArrayList<NCogniton> cognitonsDeBase;
	
	/*All cognitons*/
	public static ArrayList<NCogniton> cognitons;
	
	/*All cloud cognitons*/
	public static ArrayList<Culturon> cloudCognitons;
	
	/*All plans*/
	public static ArrayList<NPlan> plans;
	public static NPlan birthPlan;
	public static NPlan autoPlan;

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
	
	/*Environnement ˆ charger*/
	public static String environnementACharger;
	
	/*Modificateurs de l'esprit*/
	@ParametrisationDouble(min=0.1, max=3.0, pas = 0.1, toolTip = "Une valeur elevee augmente les chances que les memes soient transferes aux enfants.")
	public static Double facteurHereditaireMemes = 1.0;
	
	@ParametrisationDouble(min=0.1, max=3.0, pas = 0.1)
	public static Double facteurHereditaireTraits = 1.0;
	
	@ParametrisationDouble(min=0.1, max=3.0, pas = 0.1)
	public static Double facteurDecouverteSkills = 1.0;
	
	@ParametrisationDouble(min=0.1, max=3.0, pas = 0.1)
	public static Double facteurDecouverteMemes = 1.0;
	
	@ParametrisationDouble(min=0.1, max=3.0, pas = 0.1)
	public static Double facteurApparitionDeNouveauxTraits = 1.0;
	
	
	
	/*Tableau des traits disponibles ˆ la naissance*/
	public static String traitsDisponibles[] = {
		"civilisation.individu.cognitons.traits.TRAIT_Timide" ,
		"civilisation.individu.cognitons.traits.TRAIT_Extraverti" ,
		"civilisation.individu.cognitons.traits.TRAIT_Agressif",
		"civilisation.individu.cognitons.traits.TRAIT_Cupide"
	};

	public static Integer tauxApparitionTraits[] = {
		10 ,
		10 ,
		15,
		15
	};
	
	/*Modificateur des Humains*/
	@ParametrisationInteger(min=5, max=200, toolTip = "Nombre de ticks avant l'accouchement.")
	public static Integer tempsDeGestation = 30;
	
	/*Tableau des memes debloques par l'influence*/
	public static String memesInfluence[] = {
		"civilisation.individu.cognitons.memes.MEM_InfluenceLimitee" ,

	};
	

	public static Integer seuilApparitionMemeInfluence[] = {
		5 ,

	};
	
	/*Taille minimale pour la formation d'un groupe*/
	@ParametrisationInteger(min=1, max=40)
	public static Integer tailleMinimaleGroupe = 10;
	
	/*Taille minimale pour la fondation d'une ville*/
	@ParametrisationInteger(min=1, max=30)
	public static Integer humainsPourFonderVille = 5;
	
	/*Distance minimale entre villes*/
	@ParametrisationInteger(min=1, max=100)
	public static Integer distanceEntreVilles = 14;
	
	/*Passages minimaux pour une route*/
	@ParametrisationInteger(min=5, max=500, pas = 5, toolTip = "Nombre de passage d'un agent necessaires pour qu'un chemin soit trace.")
	public static Integer passagesPourCreerRoute = 30;
	@ParametrisationInteger(min=1, max=150, pas = 1)
	public static Integer EffacementRoute = 5;

	
	
	/**
	 * Vitesse de deplacement sur les patchs
	 */
	@ParametrisationInteger(min=1, max=100, toolTip = "Vitesse estimee par defaut pour un patch que l'agent ne voit pas")
	public static Integer VitesseEstimeeParDefaut = 25;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurPlaine = 20;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurRoute = 2;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurForet = 40;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurLittoral = 40;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurBanquise = 40;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurColline = 60;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurMontagne = 80;
	
	@ParametrisationInteger(min=10, max=1000)
	public static Integer VitesseSurEau = 900;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseSurDesert = 40;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer VitesseCheval = 10;
	
	@ParametrisationInteger(min=1, max=100)
	public static Integer TauxDressageCheval = 25;

	@ParametrisationDouble(min=-500., max=200., pas = 5., toolTip = "Valeur de passage necessaire a l'apparition d'une foret (generalement negative)")
	public static Double seuilEmergenceForet = -40.;

	public static void addCogniton(NCogniton nouveauCogniton) {
		cognitons.add(nouveauCogniton);
	}
	
	public static void addCloudCogniton(Culturon newCloudCogniton) {
		cloudCognitons.add(newCloudCogniton);
	}
	
	public static void addPlan(NPlan nouveauPlan) {
		plans.add(nouveauPlan);
	}
	
	public static void addCognitonDeBase(NCogniton nouveauCogniton) {
		cognitonsDeBase.add(nouveauCogniton);
	}
	
	public static void removeCognitonDeBase(NCogniton c){
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
		return null;
	}
	
	public static Action getActionByName(String s){
		for (int i = 0 ; i < actions.size(); i++){
			if (actions.get(i).getSimpleName().equals(s)){
				return(actions.get(i));
			}
		}
		return null;
	}
	
	public static NCogniton getCognitonByName(String s){
		for (int i = 0 ; i < actions.size(); i++){
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
	
}

