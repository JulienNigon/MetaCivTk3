package civilisation.individu.cognitons.traits;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Belief;
import civilisation.individu.cognitons.Trait;
import civilisation.individu.plan.Plan;

public class TRAIT_Timide extends Trait{

	public static String[] plans = {"PLAN_ChercherCompagnon","PLAN_Combattre"};
	public static int[] poids = {-1,-1};
	public static int tauxTransfert = 40;
	public static String nom = "Timide";
	public static String descriptif = "Timide : cet agent est très timide.";
	public static String[] cognitonsOpposes = {"civilisation.individu.cognitons.beliefs.TRAIT_Extraverti"};
	public static int[] nInstances = new int[20];

	
	
	public TRAIT_Timide(Esprit e) {
		super(e);
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			Plan[] nouveauxProjets = {};
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public String[] getCognitonsOpposes() {return cognitonsOpposes;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

	
}
