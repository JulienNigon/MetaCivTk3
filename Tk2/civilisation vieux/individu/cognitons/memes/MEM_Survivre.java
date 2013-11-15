package civilisation.individu.cognitons.memes;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.plan.Plan;

public class MEM_Survivre extends Meme{

	public static String[] plans = {"PLAN_Chasser" , "PLAN_Cueillir" , "PLAN_Cultiver"};
	public static int[] poids = {4 , 6 , 3};
	public static int tauxTransfert = 100;
	public static String nom = "Survivre";
	public static String descriptif = "Survivre : l'agent cherche ˆ subsister. Pour cela il chasse et cueille.";
	public static int[] nInstances = new int[20];

	
	
	public MEM_Survivre(Esprit e) {
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
