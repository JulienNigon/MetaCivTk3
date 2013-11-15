package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;
import civilisation.individu.plan.Plan;


/** 
 * Classe abstraite de base représentant tout type de croyance (au sens large)
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


 public abstract class Cogniton {
	
	 	public Cogniton()
	 	{
	 		
	 	}
	 
		public Cogniton (Esprit e)
		{
			init(e);
		}
		
		public void evaluer(Esprit e)
		{
			for (int i = 0 ; i < this.getTabPlan().length ; i++)
			{
				e.changePoids(getTabPlan()[i],getTabPoids()[i]);
			}
		}
		
		public void recalculer(Esprit e) {
			//a enlever
			evaluer(e);
		}
		
		public void init(Esprit e)
		{
			modifierProjets(true, e);
			evaluer(e);
		}
		
		/**
		 * Ajoute ou retire des projets au Mind associé
		 * @param nouveauxProjets : liste des projets à ajouter ou enlever
		 * @param add : true indique qu'il faut les ajouter, false les enlever
		 * @param e : le Mind associé
		 */
		public void modifierProjets(Boolean add, Esprit e)
		{
			//.out.println("Modifier projet:" + getNom());
			Plan nPlans[] = getTabNouveauxPlans(e);
			if (nPlans != null)
			{
				//System.out.println("Modifier projet != null:" + getNom());

				if (add)
				{
					for (int i = 0 ; i < nPlans.length ; i++)
					{
						e.addProjet(nPlans[i]);
					}
					e.recalculerPoids();
				}
				else
				{
					for (int i = 0 ; i < nPlans.length ; i++)
					{
						e.retirerProjet(nPlans[i]);
					}
				}
			}
		}
		
		public void supprimer(Esprit e) {
			//System.out.println("Retirer");
			e.retirerCogniton(this);
			//System.out.println("modifierProj");
			modifierProjets(false, e);
			//System.out.println("recalculer");
			e.recalculerPoids();
			destroy(e);
		}

		public String getNom()
		{
			return "Nom indisponible";
		}

		public String toString()
		{
			return "Description indisponible";
		}
		
		public String getType()
		{
			return "--Cogniton--";
		}
		
		public int[] getTabPoids()
		{
			return null;
		}
	
		public String[] getTabPlan()
		{
			return null;
		}

		public Plan[] getTabNouveauxPlans(Esprit e) 
		{
			return null;
		}
		
		public Color getColor()
		{
			return Color.gray;
		}
		
		/**
		 * Augmente les poids du cogniton (si ce type de cogniton l'autorise)
		 * @param boost : poids à ajouter
		 */
		public void ameliorer(int[] boost)
		{
			System.out.println("Erreur : " + getNom() + " ne peut être améliorée");
		}

		public int getTauxTransfert()
		{
			return 0;
		}
		
		public String[] getChaine()
		{
			return null;  // Par défaut
		}

		public int[] getTauxChaine()
		{
			return null;  // Par défaut
		}
		
		public void destroy(Esprit e) {} //Reduit de 1 le nombre d'instances

		public static void test(){};
		
		public boolean equals(Cogniton c){
			return this.getNom().equals(c.getNom());
		}

		public void fade(Esprit e) {
			//Uniquement pour les methodes qui disparaissent avec le temps.
		}
}
