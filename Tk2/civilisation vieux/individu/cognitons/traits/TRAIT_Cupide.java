package civilisation.individu.cognitons.traits;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Belief;
import civilisation.individu.cognitons.Trait;
import civilisation.individu.plan.Plan;

public class TRAIT_Cupide extends Trait{

	public static String[] plans = {"PLAN_Extraire"};
	public static int[] poids = {5};
	public static int tauxTransfert = 70;
	public static String nom = "Cupide";
	public static String descriptif = "Cupide : cet agent est très cupide.";
	public static String[] cognitonsOpposes = {};
	public static int[] nInstances = new int[20];

	
	
	public TRAIT_Cupide(Esprit e) {
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
