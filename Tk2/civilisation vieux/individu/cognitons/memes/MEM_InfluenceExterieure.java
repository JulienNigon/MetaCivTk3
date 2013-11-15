package civilisation.individu.cognitons.memes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.Meme;
import civilisation.individu.plan.GroupPlan;
import civilisation.individu.plan.PLANGR_FonderVille;
import civilisation.individu.plan.PLAN_ChercherCompagnon;
import civilisation.individu.plan.Plan;

/**
 * Cogniton tres particulier qui gere l'influence d'autres agents
 * 
 *
 */


/**
 * @author juliennigon
 *
 */
public class MEM_InfluenceExterieure extends Meme{

	public String[] plans;
	public int[] poids;
	Humain leader;
	Object target;
	public static int tauxTransfert = 0;
	public static String nom = "InfluenceExterieure";
	public static String descriptif = "InfluenceExterieure : L'agent est influence par un autre agent.";
	public static int[] nInstances = new int[20];
	Plan[] nouveauxProjets;
	int fading = 3;

	
	
	public MEM_InfluenceExterieure(Esprit e, String[] pl, int[] po, Humain leader, Object target) {
		super();
		nInstances[e.getHumain().getCiv().getIndexCiv()]++;

		this.leader = leader;
		this.target = target;
		plans = new String[pl.length];
		poids = new int[po.length];
		nouveauxProjets = new Plan[pl.length];

		for (int i = 0; i < pl.length; i++)
		{
			plans[i] = pl[i];
			poids[i] = po[i];
			nouveauxProjets[i] = createPlanByName(pl[i] , e);
		}
		
		modifierProjets(true, e);
		evaluer(e , leader);

	}
	
	public String getNom() { return nom; }
	public String toString() { return descriptif; }
	public int getTauxTransfert() { return tauxTransfert; }
	public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			return nouveauxProjets;
		};
	public int[] getTabPoids() {return poids;}
	public String[] getTabPlan() {return plans;}
	public void destroy(Esprit e) {nInstances[e.getHumain().getCiv().getIndexCiv()]--;}

	





/**
 * Creer un objet de type plan a partir de son nom
 * @param le nom du plan a creer
 */
public Plan createPlanByName(String s, Esprit e)
{

		Class c;
		GroupPlan plan;
		try {
			c = Class.forName("civilisation.individu.plan." + s);
			Constructor constructor  = null;

			//public GroupPlan(Esprit e, Humain h, Object target, Humain leader)
			Object[] valeurs = new Object[]{e, e.getHumain(), target, leader};
			Class[] parametres;
				parametres = new Class[]{e.getClass(), e.getHumain().getClass(), new Object().getClass(), leader.getClass()};


			constructor = c.getConstructor(parametres);
			plan  = (GroupPlan) constructor.newInstance(valeurs);
			

			
			return plan;
			
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (SecurityException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (NoSuchMethodException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
	}


	//Redefinition de evaluer, adaptee au mecanisme d'influence
	public void evaluer(Esprit e, Humain leader)
	{
		for (int i = 0 ; i < this.getTabPlan().length ; i++)
		{
			e.changePoidsPlanGroupe(getTabPlan()[i].split("_")[1],getTabPoids()[i], leader);
		}
	}

	public Humain getLeader() {
		return leader;
	}
	
	public void recalculer(Esprit e) {
		//a enlever
		evaluer(e, leader);
	}

	public void fade(Esprit e) {
		fading --;
		if (fading <= 0){
			this.supprimer(e);
		}
	}
}

