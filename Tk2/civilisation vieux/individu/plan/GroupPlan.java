package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Cogniton;

/** 
 * Classe abstraite représentant un "plan", projet au sens large poursuivi par un agent
 * Dans le cas du GroupPlan, c'est un plan specifique lie à un leader
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public abstract class GroupPlan extends Plan{

	protected Object target;
	protected Humain leader;
	protected boolean ordreDonne;
	
	public GroupPlan(Esprit e, Humain h, Object target, Humain leader) {
		super(e, h);
		this.target = target; //null = soi meme, et donc que la cible reste à decider, ou qu'il n'y a pas de cible
		this.leader = leader; //null = soit même
		ordreDonne = false;

	}

	public boolean equals(GroupPlan p)
	{
		return target.equals(p.getTarget()) && leader.equals(p.getLeader()) && this.getPoids() == p.getPoids() && this.getNom().equals(p.getNom());	
	}
	
	public boolean equals(Plan p)
	{
		return false;	
	}
	
	public Object getTarget(){
		return target;
	}
	
	public Humain getLeader(){
		return leader;
	}
	
	// Retourne le poids de ce projet quand il est transmis par l'influence
	public int getPoidsInfluence(){
		return 5;
	}
}
