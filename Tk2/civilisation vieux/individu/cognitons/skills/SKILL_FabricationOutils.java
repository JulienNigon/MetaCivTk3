package civilisation.individu.cognitons.skills;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.cognitons.Skill;
import civilisation.individu.plan.PLAN_Chasser;
import civilisation.individu.plan.PLAN_ChercherCompagnon;
import civilisation.individu.plan.PLAN_Cueillir;
import civilisation.individu.plan.PLAN_Cultiver;
import civilisation.individu.plan.PLAN_Deboiser;
import civilisation.individu.plan.PLAN_Fabriquer_Arc;
import civilisation.individu.plan.PLAN_Fabriquer_Beche;
import civilisation.individu.plan.PLAN_Fabriquer_Gants;
import civilisation.individu.plan.PLAN_Fabriquer_Pioche;
import civilisation.individu.plan.PLAN_Fabriquer_Scie;
import civilisation.individu.plan.Plan;

public class SKILL_FabricationOutils extends Skill{

	public static String[] plans = {"PLAN_Fabriquer_Arc","PLAN_Fabriquer_Beche","PLAN_Fabriquer_Gants","PLAN_Fabriquer_Pioche","PLAN_Fabriquer_Scie"};
	public static int[] poids = {0,0,0,0,0};
	public static int tauxTransfert = 5;
	public static String nom = "FabricationOutils";
	public static String descriptif = "FabricationOutils : l'agent sait fabriquer des outils.";	
	public static int[] nInstances = new int[20];

	
	
	public SKILL_FabricationOutils(Esprit e) {
		super(e);
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			Plan[] nouveauxProjets = {new PLAN_Deboiser(e, e.getHumain()), new PLAN_Fabriquer_Arc(e, e.getHumain()) , new PLAN_Fabriquer_Beche(e, e.getHumain() ),new PLAN_Fabriquer_Gants(e, e.getHumain()) ,new PLAN_Fabriquer_Pioche(e, e.getHumain()) ,new PLAN_Fabriquer_Scie(e, e.getHumain()     )};
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

}
