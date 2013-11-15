package civilisation.individu.cognitons.skills;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.cognitons.Skill;
import civilisation.individu.plan.PLAN_Chasser;
import civilisation.individu.plan.PLAN_ChercherCompagnon;
import civilisation.individu.plan.PLAN_Cueillir;
import civilisation.individu.plan.PLAN_Cultiver;
import civilisation.individu.plan.Plan;

public class SKILL_Agriculture extends Skill{

	public static String[] plans = {};
	public static int[] poids = {};
	public static int tauxTransfert = 5;
	public static String nom = "Agriculture";
	public static String descriptif = "Agriculture : l'agent sait planter des champs.";	
	public static int[] nInstances = new int[20];
	public static String[] chaine = {"civilisation.individu.cognitons.skills.SKILL_Equitation"};
	public static int[] tauxChaine = {6};
	
	
	public SKILL_Agriculture(Esprit e) {
		super(e);
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			Plan[] nouveauxProjets = {new PLAN_Cultiver(e, e.getHumain())};
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public String[] getChaine(){return chaine;}
	public int[] getTauxChaine(){return tauxChaine;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

}
