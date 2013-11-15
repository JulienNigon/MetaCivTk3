package civilisation.individu.cognitons.percepts;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Belief;
import civilisation.individu.cognitons.Percept;
import civilisation.individu.cognitons.Trait;
import civilisation.individu.plan.Plan;

public class PERCEPT_EstChezSoi extends Percept{


	public static String[] plans = {};
	public static int[] poids = {};
	public static int tauxTransfert = 0;
	public static String nom = "EstChezSoi";
	public static String descriptif = "EstChezSoi : cet agent est dans sa communauté.";
	public static int[] nInstances = new int[20];

	
	
	public PERCEPT_EstChezSoi(Esprit e) {
		super(e);
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

	
}