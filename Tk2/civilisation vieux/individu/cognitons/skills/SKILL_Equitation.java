package civilisation.individu.cognitons.skills;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.cognitons.Skill;
import civilisation.individu.plan.PLAN_Chasser;
import civilisation.individu.plan.PLAN_ChercherCompagnon;
import civilisation.individu.plan.PLAN_Cueillir;
import civilisation.individu.plan.PLAN_Cultiver;
import civilisation.individu.plan.PLAN_DresserCheval;
import civilisation.individu.plan.Plan;

public class SKILL_Equitation extends Skill{

	public static String[] plans = {"PLAN_DresserCheval"};
	public static int[] poids = {1};
	public static int tauxTransfert = 5;
	public static String nom = "Equitation";
	public static String descriptif = "Equitation : l'agent sait dresse des chevaux.";	
	public static int[] nInstances = new int[20];

	
	
	public SKILL_Equitation(Esprit e) {
		super(e);
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			Plan[] nouveauxProjets = {new PLAN_DresserCheval(e, e.getHumain())};
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

}
