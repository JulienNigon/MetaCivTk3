package civilisation.individu.cognitons.skills;

import civilisation.individu.Esprit;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.cognitons.Skill;
import civilisation.individu.plan.PLAN_Chasser;
import civilisation.individu.plan.PLAN_Cueillir;
import civilisation.individu.plan.Plan;

/** 
 * Skill modulable pour représenter le talent d'un agent dans une tâche donnée
 * @author DTEAM
 * @version 1.0 - 3/2013
*/

public class SKILL_Talent extends Skill{

	public String[] plans;
	public int[] poids;
	public static int tauxTransfert = 0;
	String nom;
	public static int[] nInstances = new int[20];

	
	
	/**
	 * Constructeur redéfini pour que tout aille comme on le souhaite
	 */
	public SKILL_Talent(Esprit e, String nom, String[] pl, int[] po) {
		super();

		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

		
		this.nom = nom;
		plans = new String[pl.length];
		poids = new int[po.length];

		for (int i = 0; i < pl.length; i++)
		{
			plans[i] = pl[i];
			poids[i] = po[i];
		}

		evaluer(e);

	}
	
	/**
	 * Augmente les poids du talent
	 * @param boost : poids à ajouter
	 */
	public void ameliorer(int[] po)
	{
		for (int i = 0; i < poids.length; i++)
		{
			poids[i]+=po[i];
		}
	}
	
	public String getNom() { return nom; }
	public String toString() { return "Talent pour l'action : " + nom; }
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
