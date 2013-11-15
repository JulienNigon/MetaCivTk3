package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;

public abstract class Skill extends Cogniton{

	
	public Skill()
	{
		
	}
	
	public Skill(Esprit e )
	{
		super(e);
		//modifierProjets(this.getTabPlan(), Boolean add, Esprit e)

	}

	public Color getColor()
	{
		return new Color(116,208,241);
	}


	public String getType()
	{
		return "Skill";  // Par défaut
	}

	
	/**
	 * Les skills peuvent être enseignées à d'autres
	 * Plus le tôt de transfert est élevé, plus c'est aisé
	 */
	public int getTauxTransfert()
	{
		return 0;
	}
	
	public String[] getChaine()
	{
		return null;  // Par défaut
	}
}
