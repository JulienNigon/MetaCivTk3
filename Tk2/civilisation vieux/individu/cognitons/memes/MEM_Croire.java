package civilisation.individu.cognitons.memes;


import utils.Utils;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.plan.PLAN_Prier;
import civilisation.individu.plan.Plan;

public class MEM_Croire extends Meme{
	
	public static int tauxTransfert = 99;
	public static String nom = "Croire";
	public static String descriptif = "Croire : l'agent pense qu'il doit prier parcequ'il a la foi.";
	public static int[] nInstances = new int[20];	
	public static String[] plans = {"PLAN_Prier"};
	public static int poidsPrierDeBase = 5;
	public int[] poids = {0};
	private int seuilFoi;
	
	
	public MEM_Croire(Esprit e) {
		super();

		nInstances[e.getHumain().getCiv().getIndexCiv()]++;
		seuilFoi = (int) Math.round(Math.random() * 10);
		
		init(e);
	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e)
	{
		Plan[] nouveauxProjets = { new PLAN_Prier(e, e.getHumain()) };
		return nouveauxProjets;
	}
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}
	public int getSeuilFoi()
	{
		return seuilFoi;
	}
	public void incrementSeuilFoi()
	{
		seuilFoi++;
	}
	public void setSeuilFoi(int seuil)
	{
		seuilFoi = seuil;
	}
	public void setPoids(int indice, int p)
	{
		poids[indice] = p;
	}
	public void setPoidsCroire(int poids)
	{
		setPoids(0, poidsPrierDeBase + poids);
	}
	public void incrementPoidsCroire(int poids)
	{
		setPoids(0, getTabPoids()[0] + poids);
	}
}
