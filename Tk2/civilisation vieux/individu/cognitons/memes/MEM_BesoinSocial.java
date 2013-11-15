package civilisation.individu.cognitons.memes;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.plan.PLAN_ChercherCompagnon;
import civilisation.individu.plan.Plan;

public class MEM_BesoinSocial extends Meme{

	public static String[] plans = {"PLAN_ChercherCompagnon"};
	public static int[] poids = {3};
	public static int tauxTransfert = 100;
	public static String nom = "BesoinSocial";
	public static String descriptif = "BesoinSocial : l'agent a besoin d'interactions pour se sentir bien.";
	public static int[] nInstances = new int[20];

	
	
	public MEM_BesoinSocial(Esprit e) {
		super(e);
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			Plan[] nouveauxProjets = {new PLAN_ChercherCompagnon(e, e.getHumain()) };
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

	
}
