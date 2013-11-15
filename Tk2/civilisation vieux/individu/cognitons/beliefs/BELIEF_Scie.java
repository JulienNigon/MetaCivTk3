package civilisation.individu.cognitons.beliefs;

import civilisation.annotations.ParametrisationInteger;
import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Belief;
import civilisation.individu.plan.PLAN_Deboiser;
import civilisation.individu.plan.PLAN_Prier;
import civilisation.individu.plan.Plan;

public class BELIEF_Scie extends Belief{

	public static String[] plans = {"PLAN_Deboiser"};
	public static int[] poids = {5};
	public static int tauxTransfert = 0;
	public static String nom = "Scie";
	public static String descriptif = "Scie : l'agent porte une scie.";
	public static int[] nInstances = new int[20];
	
	
	
	public BELIEF_Scie(Esprit e) {
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
