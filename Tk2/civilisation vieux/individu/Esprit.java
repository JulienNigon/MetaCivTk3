package civilisation.individu;

import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.cognitons.memes.MEM_BesoinSocial;
import civilisation.individu.cognitons.memes.MEM_InfluenceExterieure;
import civilisation.individu.cognitons.memes.MEM_Survivre;
import civilisation.individu.cognitons.percepts.PERCEPT_EstChezSoi;
import civilisation.individu.cognitons.percepts.PERCEPT_Faim;
import civilisation.individu.cognitons.percepts.PERCEPT_FaimLegere;
import civilisation.individu.cognitons.skills.SKILL_ConnaissancesInstinctives;
import civilisation.individu.cognitons.skills.SKILL_Talent;
import civilisation.individu.cognitons.traits.TRAIT_Extraverti;
import civilisation.individu.cognitons.traits.TRAIT_Timide;
import civilisation.individu.plan.GroupPlan;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.NPlanPondere;
import civilisation.individu.plan.Plan;
import civilisation.individu.plan.Reflexe;
import civilisation.individu.plan.action.Action;
import civilisation.inventaire.ObjetInventaire;
import civilisation.individu.cognitons.memes.MEM_Croire;

/** 
 * Classe de gestion des comportements des agents humain
 * @author DTEAM
 * @version 1.0 - 2/2013
*/



public class Esprit {
	
	/* Les diff≈Ωrentes listes contenants les croyances de l'agent */
	ArrayList<Cogniton> skills;
	ArrayList<Cogniton> memes;
	ArrayList<Cogniton> percepts;
	ArrayList<Cogniton> traits;
	ArrayList<Cogniton> beliefs;
	ArrayList<NCogniton> cognitons;
	
	/* La liste des projets envisageable par l'agent*/
	ArrayList<Plan> projets;
	ArrayList<NPlanPondere> plans;

	/*Autres attributs:*/
	Boolean engagement;
	Humain h;
	Plan proj; //Le projet en cours
	NPlanPondere planEnCours;
	Action actionEnCours;
	
	int poidsTotal;
	int timer;
	int progression;
	int poidsTotalPlan;
	


	
	
	public Esprit(Humain h)
	{
		skills = new ArrayList<Cogniton>();
		memes = new ArrayList<Cogniton>();
		percepts = new ArrayList<Cogniton>();
		traits = new ArrayList<Cogniton>();
		beliefs = new ArrayList<Cogniton>();
		cognitons = new ArrayList<NCogniton>();

		projets = new ArrayList<Plan>();
		plans = new ArrayList<NPlanPondere>();

		proj = null;
		engagement = false;
		this.h = h;
		timer = 0;
		progression = 0;
		poidsTotalPlan = 0;
		
		
		/*Si l'agent a des parents (ie : il n'a pas ≈Ωt≈Ω g≈Ωn≈Ωr≈Ω au d≈Ωbut), on calcul ce qu'il obtient de ses parents*/
		if (h.getMere() != null)
		{
			heritage(h.getPere().getEsprit(), h.getMere().getEsprit());
			nouvellesIdees();
			nouvellePersonalite();
		}
		else
		{
			initialisationStandard();
		}
	}
	
	/**
	 * Initialise les cognitons
	 */
	private void initialisationStandard()
	{
		skills.add(new SKILL_ConnaissancesInstinctives(this));
		//this.addCognitonByName("civilisation.individu.croyances.savoirs.SKILL_ConnaissancesInstinctives");
		//skills.add(new SKILL_Agriculture(this));
		memes.add(new MEM_Survivre(this));
		memes.add(new MEM_BesoinSocial(this));
		//String plan[] = {"PLANGR_FonderVille"};
		//int poids[] = {1};
		//memes.add(new MEM_InfluenceExterieure(this,plan,poids, this.getHumain(), null));

		cognitons.addAll(Configuration.cognitonsDeBase); /*Les cognitons de bases, definis a l'initialisation.*/
		for (int i = 0; i < cognitons.size(); i++){
			cognitons.get(i).mettreEnPlace(this);
		}

	}
	
	/**
	 * G≈ΩnÔøΩre les traits et skills h≈Ωrit≈Ωes des parents
	 * D≈Ωclenche ≈Ωventuellement l'apparition d'autres traits
	 */
	private void heritage(Esprit ePere, Esprit eMere)
	{	
		//------------------ HERITAGE PERE ----------------------		
		for (int i = 0; i < ePere.getSkills().size(); i++) {
			if (!containsCogniton(ePere.getSkills().get(i)) && ePere.getSkills().get(i).getTauxTransfert() != 0){   //Une SKILL � 0 n'utilise pas les r�gles normales de transfert
				this.addCognitonByName(ePere.getSkills().get(i).getClass().getName());
			}
		}
		for (int i = 0; i < ePere.getTraits().size(); i++) {
			if (!containsCogniton(ePere.getTraits().get(i)) && ePere.getTraits().get(i).getTauxTransfert()*Configuration.facteurHereditaireTraits > (Math.random()*100)){
				this.addCognitonByName(ePere.getTraits().get(i).getClass().getName());
			}
		}
		for (int i = 0; i < ePere.getMemes().size(); i++) {
			if (!containsCogniton(ePere.getMemes().get(i)) && ePere.getMemes().get(i).getTauxTransfert()*Configuration.facteurHereditaireMemes > (Math.random()*100)){
				this.addCognitonByName(ePere.getMemes().get(i).getClass().getName());
				
				//CAS SPECIAL: CROIRE; le seuil est = à la moyenne du seuil des parents /2
				if(ePere.getMemes().get(i).getNom().equals("Croire"))
				{
					int seuilPere = ((MEM_Croire) ePere.getCognitonByName("Meme", "Croire")).getSeuilFoi();
					int seuilMere = ((MEM_Croire) eMere.getCognitonByName("Meme", "Croire")).getSeuilFoi();
					((MEM_Croire) getCognitonByName("Meme", "Croire")).setSeuilFoi(Math.round((seuilPere + seuilMere) /2));
				}
			}	
		}
		
		//------------------ HERITAGE MERE ----------------------		
		for (int i = 0; i < eMere.getSkills().size(); i++) {
			if (!containsCogniton(eMere.getSkills().get(i)) && eMere.getSkills().get(i).getTauxTransfert() != 0){   //Une SKILL � 0 n'utilise pas les r�gles normales de transfert
				this.addCognitonByName(eMere.getSkills().get(i).getClass().getName());
			}
		}
		for (int i = 0; i < eMere.getTraits().size(); i++) {
			if (!containsCogniton(eMere.getTraits().get(i)) && eMere.getTraits().get(i).getTauxTransfert()*Configuration.facteurHereditaireTraits > (Math.random()*100)){
				this.addCognitonByName(eMere.getTraits().get(i).getClass().getName());
			}
		}
		for (int i = 0; i < eMere.getMemes().size(); i++) {
			if (!containsCogniton(eMere.getMemes().get(i)) && eMere.getMemes().get(i).getTauxTransfert()*Configuration.facteurHereditaireMemes > (Math.random()*100)){
				this.addCognitonByName(eMere.getMemes().get(i).getClass().getName());
				
				//CAS SPECIAL: CROIRE; le seuil est = à la moyenne du seuil des parents /2
				if(eMere.getMemes().get(i).getNom().equals("Croire"))
				{
					int seuilPere = ((MEM_Croire) ePere.getCognitonByName("Meme", "Croire")).getSeuilFoi();
					int seuilMere = ((MEM_Croire) eMere.getCognitonByName("Meme", "Croire")).getSeuilFoi();
					((MEM_Croire) getCognitonByName("Meme", "Croire")).setSeuilFoi(Math.round((seuilPere + seuilMere) /2));
				}
			}	
		}
	}
	
	/**
	 * Ajoute ≈Ωventuellement de nouvelles Skills ou de nouveaux memes en suivant les ramifications des cognitons
	 */
	private void nouvellesIdees()
	{
		int taille = skills.size();
		for (int i = 0; i < taille; i++) {
			if (skills.get(i).getChaine() != null)
			{
				for (int j = 0; j < skills.get(i).getChaine().length; j++){
					if (getSkills().get(i).getTauxChaine()[j]*Configuration.facteurDecouverteSkills > (Math.random()*100)){
						this.addCognitonByName(getSkills().get(i).getChaine()[j]);
					}
				}
			}
		}
		
		taille = memes.size();
		for (int i = 0; i < taille; i++) {
			if (memes.get(i).getChaine() != null)
			{
				for (int j = 0; j < memes.get(i).getChaine().length; j++){
					if (memes.get(i).getTauxChaine()[j]*Configuration.facteurDecouverteMemes > (Math.random()*100)){
						this.addCognitonByName(memes.get(i).getChaine()[j]);
					}
				}
			}
		}
	}
	
	private void nouvellePersonalite()
	{
		for (int i = 0; i < Configuration.traitsDisponibles.length; i++)
		{
			if (!containsCogniton("Trait", Configuration.traitsDisponibles[i]) && Configuration.tauxApparitionTraits[i]*Configuration.facteurApparitionDeNouveauxTraits > (Math.random()*100))
			{
				this.addCognitonByName(Configuration.traitsDisponibles[i]);

			}
		}
	}
	
	/**
	 * Fonction appell≈Ωe ÀÜ chaque activation d'un Humain.
	 * D≈Ωtermine le projet ÀÜ effectuer.
	 */
	public void penser()
	{

		//clearPercepts();
		//percevoir();
		//fadeInfluencesExterieures();
		
		//int reflexeActivable = reflexes();
		
		//if (h.isHere(h.getCommunaute()) && !h.getCommunaute().possedeHutte(h))  //Pour les besoins du test, on fait construire les huttes aux agents dÔøΩs que possible
		//{
		//	h.construireHutte();
		//}
		
		//if (reflexeActivable >= 0)
		//{
		//	projets.get(reflexeActivable).Activer();
		//}
		//else
		//{
			//timer --;
			if ((planEnCours == null && actionEnCours == null)|| timer == 0)
			{
				int alea = (int) (Math.random()*(poidsTotalPlan + 1));
				int i = 0;
				while (alea > plans.get(i).getPoids() /*|| plans.get(i).getType() == 1*/)
				{
					alea -= plans.get(i).getPoids();				
					i++;
				}
				planEnCours = plans.get(i);
				//timer = proj.getTempsMax();
				//System.out.println("Nouveau projet : " + proj);
			}
			planEnCours.activer(actionEnCours);
			

		//}

	
	}
	
	private void fadeInfluencesExterieures() {
		for (int i = 0; i < memes.size(); i++)
		{
			memes.get(i).fade(this);
		}
		
	}

	/**
	 * Met en place les percepts du moment actuel
	 */
	private void percevoir()
	{
		if (h.getVie() <= 50)
		{
			percepts.add(new PERCEPT_Faim(this));
		}
		if (h.getVie() <= 100 && h.getVie() >= 50)
		{
			percepts.add(new PERCEPT_FaimLegere(this));
		}
		if (h.getCommunaute().position.equals(h.position))
		{
			percepts.add(new PERCEPT_EstChezSoi(this));
		}
	}
	
	private int reflexes()
	{
		int index = - 1;
		int priorite = -1;
		for (int i = 0; i < projets.size(); i++)
		{
			if (projets.get(i).getType() == 1)
			{
				if (((Reflexe) projets.get(i)).isTriggered() && ((Reflexe) projets.get(i)).getPriorite() > priorite)
				{
					index = i;
				}
			}
		}
		return index;
	}
	
	/**
	 * Vide les percepts (il y a de l'optimisation ÔøΩ faire ici)
	 */
	private void clearPercepts()
	{
		while (!percepts.isEmpty()){
			percepts.get(0).supprimer(this);
		}
	}
	
	/**
	 * Met ÀÜ jour l'esprit pour indiquer que le projet actuel est termin≈Ω.
	 */
	public void finProjet()
	{
		proj = null;
	}
	
	
	/**
	 * Modifie le poids total d'une valeur donn≈Ωe.
	 * @param variation de poids
	 */
	public void ajouterPoidsTotal(int poids)
	{
		poidsTotal += poids;
	}
	
	/**
	 * Ajoute un nouveau projet aux projets disponible pour l'agent.
	 * @param nouveauProjet
	 */
	public void addProjet(Plan projet)
	{
		projets.add(projet);
	}
	


	/**
	 * Change le poids actuel d'un projet
	 * @param nom du projet ÀÜ modifier
	 * @param poids ÀÜ ajouter
	 */
	public void changePoids(String nom, int p) 
	{
		int i = 0;
		//System.out.println(projets.get(i).getClass().getName());
		while ( i < projets.size() && !projets.get(i).getClass().getName().contains(nom))
		{
			//System.out.println(projets.get(i).getClass().getName() + "   " + nom);
			//System.out.println("While " + i);
			i++;
		}
		if (projets.size() > 0 && i < projets.size())
		{
			projets.get(i).changerPoids(p);
		}
		
	}

	
	/**
	 * Change le poids actuel d'un projet
	 * @param nom du projet ÀÜ modifier
	 * @param poids ÀÜ ajouter
	 */
	public void changePoidsPlanGroupe(String nom, int p, Humain leader) 
	{
		int i = 0;
		boolean boucle = true;
		
		while ( i < projets.size() && boucle)
		{
			if (projets.get(i).getNom().equals(nom)){

				if (leader == null)
				{
					if (((GroupPlan) projets.get(i)).getLeader() == null){
						boucle = false;
					} 
				}
				else{
					if (((GroupPlan) projets.get(i)).getLeader() != null){
						if (((GroupPlan) projets.get(i)).getLeader().equals(leader)){
							boucle = false;
						}
					} 
				}

			}
			i++;
		}
		if (projets.size() > 0 && i <= projets.size())
		{
			projets.get(i-1).changerPoids(p);
		}
		
		
	}
	
	
	/**
	 * Retire un projet de la liste des projets
	 * @param plan : un projet de mÔøΩme classe que celui ÀÜ retirer
	 */
	public void retirerProjet(Plan plan) {
		int i = 0;
		while ( projets.get(i).getNom() != plan.getNom())
		{
			i++;
		}
		projets.remove(i);
		
	}
	
	/**
	 * Supprime un cogniton
	 * @param le cogniton ÀÜ supprimer
	 */
	public void retirerCogniton(Cogniton cogniton) {	
		ArrayList<Cogniton> tab = getTabCogniton(cogniton.getType());
		
		int i = 0;
		while (!tab.get(i).equals(cogniton))
		{
			i++;
		}
		tab.remove(i);	
	}

	
	public void recalculerPoids()
	{
		poidsTotal = 0;
		for (int i = 0; i < projets.size(); i++)
		{
			projets.get(i).setPoids(0);
		}
		
		for (int i = 0; i < skills.size(); i++)
		{
			skills.get(i).recalculer(this);
		}
		for (int i = 0; i < percepts.size(); i++)
		{
			percepts.get(i).recalculer(this);
		}
		for (int i = 0; i < traits.size(); i++)
		{
			traits.get(i).recalculer(this);
		}
		for (int i = 0; i < beliefs.size(); i++)
		{
			beliefs.get(i).recalculer(this);
		}
		for (int i = 0; i < memes.size(); i++)
		{
			memes.get(i).recalculer(this);
		}
		
	}

	/**
	 * @return index d'une skill du nom pass≈Ω en paramÔøΩtre. -1 si elle n'existe pas.
	 */
	public int getIndexSkill(String s)
	{
		int i = 0;
		while (i < skills.size() && skills.get(i).getNom() != s)
		{
			i++;
		}
		if (i < skills.size())
		{
			return i;
		}
		else
		{
			return -1;
		}	
	}
	
	/**
	 * Am≈Ωliore une skill de type talent dont le nom est pass≈Ω en paramÔøΩtre
	 * @param s : nom du skill
	 */
	public void ameliorerSkillTalent(String s, String[] pl, int[] po)
	{		
		int index = getIndexSkill(s);
		if (index == -1)
		{
			skills.add(new SKILL_Talent(this,s,pl,po));
		}
		else
		{
			skills.get(index).ameliorer(po);
		}
	}
	
	/**
	 * Cr≈Ωer dynamiquement un cogniton ÀÜ partir de son nom de classe, si il n'existe pas d≈ΩjÀÜ
	 * @param le nom de la classe
	 */
	public void addCognitonByName(String s)
	{
		String nom = s.split("\\.")[s.split("\\.").length - 1];
		nom = nom.split("_")[nom.split("_").length - 1];
		if (!containsCogniton(nom))  //V≈Ωrification de l'existence du cogniton
		{
			Class c;
			Cogniton cogni;
			try {
				c = Class.forName(s);
				Constructor constructor  = null;
				
				Object[] valeurs = new Esprit[]{this};
				Class[] parametres = new Class[]{this.getClass()};
				
				constructor = c.getConstructor(parametres);
				cogni  = (Cogniton) constructor.newInstance(valeurs);
				
				ajouterCogniton(cogni);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
	
	/**
	 * Ajoute un cogniton si il n'existe pas d≈ΩjÀÜ
	 * @param cogni
	 */
	public void ajouterCogniton(Cogniton cogniton)
	{
		if(!containsCogniton(cogniton))  
		{
			getTabCogniton(cogniton.getType()).add(cogniton);
		}
	}
	
	public boolean containsCogniton(String type, String nomCogniton)
	{
		ArrayList<Cogniton> tab = getTabCogniton(type);
		for(int i = 0; i < tab.size(); i++)
		{
			if(tab.get(i).getNom().equals(nomCogniton)) return true;
		}
		
		return false;
	}
	
	// Version de contains pour les cognitons de type InfluenceExterieur, qui compare les leaders
	public boolean containsCognitonInfluence(Humain leader)
	{
		for(int i = 0; i < memes.size(); i++)
		{
			if(memes.get(i).getNom().equals("InfluenceExterieurer")){
				if(((MEM_InfluenceExterieure) memes.get(i)).getLeader().equals(leader)){
					
				}
			}
		}
		
		return false;
	}
	
	
	// TODO cr√©e pour la r√©trocompatibilit√©, √† utilis√© le moins souvent possible (devrait etre supprim√© dans l'id√©al car oblige a chercher dans toutes les cat√©gories de cognitons au lieu d'en cibler une seule)
	public boolean containsCogniton(String nomCogniton)
	{		
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getNom().equals(nomCogniton)) {return true;}
		}
		for (int i = 0; i < memes.size(); i++) {
			if (memes.get(i).getNom().equals(nomCogniton)) {return true;}
		}
		for (int i = 0; i < traits.size(); i++) {
			if (traits.get(i).getNom().equals(nomCogniton)) {return true;}
		}
		for (int i = 0; i < beliefs.size(); i++) {
			if (beliefs.get(i).getNom().equals(nomCogniton)) {return true;}
		}		
		for (int i = 0; i < percepts.size(); i++) {
			if (percepts.get(i).getNom().equals(nomCogniton)) {return true;}
		}
		
		return false;
	}

	public boolean containsCogniton(Cogniton cogniton)
	{
		ArrayList<Cogniton> tab = getTabCogniton(cogniton.getType());
		for(int i = 0; i < tab.size(); i++)
		{
			if(tab.get(i).getNom() == cogniton.getNom()) return true;
		}
		
		return false;
	}
	
	public ArrayList<Cogniton> getTabCogniton(String type)
	{
		if(type.equals("Trait")) return traits;
		else if(type.equals("Belief")) return beliefs;
		else if(type.equals("Meme")) return memes;
		else if(type.equals("Percept")) return percepts;
		else return skills;
	}
	
	/**
	 * V≈Ωrifie l'existence d'un cogniton dont le nom est pass≈Ω en paramÔøΩtre
	 * @param cogni : le nom du cogniton
	 * @return le cogniton du nom demandÔøΩ, si il existe, null sinon
	 */
	public Cogniton getCognitonByName(String cogni)
	{
		for (int i = 0; i < traits.size(); i++) {
			if (traits.get(i).getNom().equals(cogni)) {return traits.get(i);}
		}
		for (int i = 0; i < beliefs.size(); i++) {
			if (beliefs.get(i).getNom().equals(cogni)) {return beliefs.get(i);}
		}
		for (int i = 0; i < memes.size(); i++) {
			if (memes.get(i).getNom().equals(cogni)) {return memes.get(i);}
		}
		for (int i = 0; i < percepts.size(); i++) {
			if (percepts.get(i).getNom().equals(cogni)) {return percepts.get(i);}
		}
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getNom().equals(cogni)) {return skills.get(i);}
		}
		return null;
	}
	
	public Cogniton getCognitonByName(String type, String nomCogniton)
	{
		ArrayList<Cogniton> tab = getTabCogniton(type);
		for(int i = 0; i < tab.size(); i++)
		{
			if(tab.get(i).getNom().equals(nomCogniton)) return tab.get(i);
		}
		
		return null;
	}
	
	/**
	 * Retire un cogniton ÀÜ partir de son nom complet (chemin compris)
	 * Cette fonction v≈Ωrifie qu'il ne reste pas d'objet dans l'inventaire susceptibles de fournir ce cogniton, auquel cas il n'est pas retir≈Ω
	 * 
	 * @param s : nom du cogniton
	 */
	public void removeCognitonByFullname(String s)
	{
		boolean effetUnique = true;
		String[] noms;

		//On regarde si un objet apporte d≈ΩjÀÜ ce cogniton
		ArrayList<ObjetInventaire> allObjets = h.getInventaire().getAll();
		for (int i = 0; i < allObjets.size(); i++)
		{
			noms = allObjets.get(i).cognitonsLies();
			for (int j = 0; j < noms.length; j++)
			{
				//System.out.println(noms[j]);
				if (noms[j].equals(s)) effetUnique = false;
			}
		}
		
		//Si aucun objet n'apporte ce cogniton, on le supprime
		if (effetUnique)
		{
			s = s.split("\\.")[s.split("\\.").length - 1];
			String type = s.split("_")[0];
			s = s.split("_")[1];
			ArrayList<Cogniton> tab;
			if (type.equals("TRAIT")) tab = traits;
			else if (type.equals("BELIEF")) tab = beliefs;
			else if (type.equals("MEME")) tab = memes;
			else if (type.equals("PERCEPT")) tab = percepts;
			else  {tab = skills;}
			
			for (int i = 0 ; i < tab.size(); i++)
			{
				if (tab.get(i).getNom().equals(s)) tab.remove(i);
			}
		}
	}
	
	/**
	 * Supprime tous les cognitons
	 */
	public void clearAllCognitons() {
		
		
		for (int i = 0; i < skills.size(); i++)
		{
			skills.get(i).supprimer(this);
		}
		for (int i = 0; i < percepts.size(); i++)
		{
			percepts.get(i).supprimer(this);
		}
		for (int i = 0; i < traits.size(); i++)
		{
			traits.get(i).supprimer(this);
		}
		for (int i = 0; i < beliefs.size(); i++)
		{
			beliefs.get(i).supprimer(this);
		}
		for (int i = 0; i < memes.size(); i++)
		{
			memes.get(i).supprimer(this);
		}
		
	}
	
	public Humain getHumain() {
		return h;
	}

	public ArrayList<Cogniton> getSkills() {
		return skills;
	}

	public ArrayList<Cogniton> getMemes() {
		return memes;
	}

	public ArrayList<Cogniton> getPercepts() {
		return percepts;
	}

	public ArrayList<Cogniton> getTraits() {
		return traits;
	}

	public ArrayList<Cogniton> getBeliefs() {
		return beliefs;
	}

	public ArrayList<Plan> getProjets() {
		return projets;
	}

	public Boolean getEngagement() {
		return engagement;
	}

	public Humain getH() {
		return h;
	}

	public Plan getProj() {
		return proj;
	}

	public int getPoidsTotal() {
		return poidsTotal;
	}

	public int getTimer() {
		return timer;
	}

	public void recevoirInfluence(GroupPlan plan, Humain leader) {
		String listePlan[] = {"PLANGR_"+plan.getNom()};
		int poids[] = {plan.getPoidsInfluence()};
		if (plan.getTarget() == null){
			memes.add(new MEM_InfluenceExterieure(this,listePlan, poids, leader, null));
		}
		else{
			memes.add(new MEM_InfluenceExterieure(this,listePlan, poids, leader, plan.getTarget()));
		}
		
	}

	public void setTimer(int tempsMax) {
timer = tempsMax;		
	}

	public ArrayList<Cogniton> getAllCognitons(){
		ArrayList<Cogniton> all = new ArrayList<Cogniton>();
		all.addAll(skills);
		all.addAll(memes);
		all.addAll(traits);
		all.addAll(beliefs);
		all.addAll(percepts);

		return all;
	}

	//-------------------------------Nouvelles fonctions version Singleton-------------------------------
	
	public int getProgression() {
		return progression;
	}

	public ArrayList<NCogniton> getCognitons() {
		return cognitons;
	}

	/**
	 * Ajoute un nouveau plan aux plans disponible pour l'agent.
	 * @param nouveauProjet
	 */
	public void addPlan(NPlan plan)
	{
		plans.add(new NPlanPondere( 0 , plan , this.getHumain() , this));
	}

	/**
	 * Retire un projet de la plan des plans
	 * @param plan : le plan à retirer
	 */
	public void removePlan(NPlan plan) {
		int i = 0;
		while (!plans.get(i).getPlan().equals(plan))
		{
			i++;
		}
		projets.remove(i);
		
	}

	public ArrayList<NPlanPondere> getPlans() {
		return plans;
	}

	/**
	 * Change le poids actuel d'un plan
	 * @param plan : Le plan à modifier
	 * @param p : le poids à ajouter
	 */
	public void modifierPoids(NPlan plan, int p) 
	{
		int i = 0;
		//System.out.println(projets.get(i).getClass().getName());
		while ( i < plans.size() && !plans.get(i).getPlan().equals(plan))
		{
			System.out.println(plan);
			System.out.println(plans.get(i).getPlan());
			//System.out.println(projets.get(i).getClass().getName() + "   " + nom);
			//System.out.println("While " + i);
			i++;
		}

		if (plans.size() > 0 && i < plans.size())
		{
			plans.get(i).changerPoids(p);
		}
		else{


		}
		
	}

	/**
	 * Modifie le poids total d'une valeur donn≈Ωe.
	 * @param variation de poids
	 */
	public void addPoidsTotal(int p)
	{

		poidsTotalPlan += p;

	}

	public void redefinirPoids() {
		poidsTotalPlan = 0;

		for (int i = 0; i < plans.size(); i++)
		{
			plans.get(i).setPoids(0);
		}
		
		for (int i = 0; i < cognitons.size(); i++)
		{
			cognitons.get(i).appliquerPoids(this);
		}
		
	}

	public void progresser() {
		progression++;	
	}

	public Action getActionEnCours() {
		return actionEnCours;
	}

	public void setActionEnCours(Action actionEnCours) {
		this.actionEnCours = actionEnCours;
	}

	public NPlanPondere getPlanEnCours() {
		return planEnCours;
	}

	public void setPlanEnCours(NPlanPondere planEnCours) {
		this.planEnCours = planEnCours;
	}

	
	
	
	
	
	
}
