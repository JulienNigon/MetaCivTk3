package civilisation.individu.cognitons.traits;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Belief;
import civilisation.individu.cognitons.Trait;
import civilisation.individu.plan.Plan;

public class TRAIT_Agressif extends Trait{

	public static String[] plans = {"PLAN_Combattre" , "PLANGR_Attaquer"};
	public static int[] poids = {1 , 1};
	public static int tauxTransfert = 60;
	public static String nom = "Agressif";
	public static String descriptif = "Agressif : cet agent est très agressif.";
	public static String[] cognitonsOpposes = {};
	public static int[] nInstances = new int[20];

	
	
	public TRAIT_Agressif(Esprit e) {
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
