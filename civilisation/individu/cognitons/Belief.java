package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;

public abstract class Belief extends Cogniton{

	public Belief(Esprit e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public String getType()
	{
		return "Belief";
	}
	
	public Color getColor()
	{
		return Color.ORANGE;
	}
	
	/**
	 * Certains beliefs peuvent être partagés (ex: Il y a un ennemi au nord)
	 * D'autres non, d'un point de vue du programme (ex: j'ai une arme)
	 * Non par défaut
	 */
	public Boolean isPartageable()
	{
		return false;
	}
}
