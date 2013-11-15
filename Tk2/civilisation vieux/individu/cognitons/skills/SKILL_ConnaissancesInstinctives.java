package civilisation.individu.cognitons.skills;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.cognitons.Skill;
import civilisation.individu.plan.PLAN_Chasser;
import civilisation.individu.plan.PLAN_ChercherCompagnon;
import civilisation.individu.plan.PLAN_Combattre;
import civilisation.individu.plan.PLAN_Cueillir;
import civilisation.individu.plan.Plan;
import civilisation.individu.plan.REFLEXE_Manger;

public class SKILL_ConnaissancesInstinctives extends Skill{

	public static String[] plans = {};
	public static int[] poids = {};
	public static int tauxTransfert = 10;
	public static String nom = "Connaissances instinctives";
	public static String descriptif = "Connaissances instinctives : l'agent dispose des actions de base.";
	public static String[] chaine = {"civilisation.individu.cognitons.skills.SKILL_Agriculture", "civilisation.individu.cognitons.skills.SKILL_ExploitationMiniere", "civilisation.individu.cognitons.skills.SKILL_FabricationOutils"};
	public static int[] tauxChaine = {6,  4, 6};
	public static int[] nInstances = new int[20];

	
	public SKILL_ConnaissancesInstinctives(Esprit e) {
		super(e);			
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			Plan[] nouveauxProjets = {new PLAN_Chasser(e, e.getHumain()) , new PLAN_Cueillir(e, e.getHumain()) , new REFLEXE_Manger(e, e.getHumain()) , new PLAN_Combattre(e, e.getHumain()) };
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public String[] getChaine(){return chaine;}
	public int[] getTauxChaine(){return tauxChaine;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

}
