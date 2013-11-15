package civilisation.individu.cognitons.percepts;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Belief;
import civilisation.individu.cognitons.Percept;
import civilisation.individu.cognitons.Trait;
import civilisation.individu.plan.Plan;

public class PERCEPT_Faim extends Percept{

	public static String[] plans = {"REFLEXE_Manger"};
	public static int[] poids = {5};
	public static int tauxTransfert = 0;
	public static String nom = "Faim";
	public static String descriptif = "Faim : cet agent a faim.";
public static int[] nInstances = new int[20];



public PERCEPT_Faim(Esprit e) {
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
