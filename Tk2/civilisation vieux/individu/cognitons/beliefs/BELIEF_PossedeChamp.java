package civilisation.individu.cognitons.beliefs;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Belief;
import civilisation.individu.plan.Plan;

public class BELIEF_PossedeChamp extends Belief{

	public static String[] plans = {"PLAN_Cultiver"};
	public static int[] poids = {8};
	public static int tauxTransfert = 0;
	public static String nom = "PossedeChamp";
	public static String descriptif = "PossedeChamp : l'agent possède un champ.";
	public static int[] nInstances = new int[20];

	
	
	public BELIEF_PossedeChamp(Esprit e) {
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
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

	
}
