package civilisation.individu.cognitons.memes;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.plan.PLANGR_Attaquer;
import civilisation.individu.plan.PLANGR_FonderVille;
import civilisation.individu.plan.PLAN_ChercherCompagnon;
import civilisation.individu.plan.Plan;

public class MEM_InfluenceLimitee extends Meme{

	public static String[] plans = {"PLANGR_FonderVille" , "PLANGR_Attaquer"};
	public static int[] poids = {3, 5};
	public static int tauxTransfert = 0;
	public static String nom = "InfluenceLimitee";
	public 
	static String descriptif = "InfluenceLimitee : L'agent a une legere influence sur ses proches.";
	public static int[] nInstances = new int[20];

	
	
	public MEM_InfluenceLimitee(Esprit e) {
		super(e);
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			Plan[] nouveauxProjets = {new PLANGR_FonderVille(e, e.getHumain(), null, null) , new PLANGR_Attaquer(e, e.getHumain(), null, null)};
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

	
}
