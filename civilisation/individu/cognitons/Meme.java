package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;

public abstract class Meme extends Cogniton{

	public Meme()
	{
		
	}
	
	public Meme(Esprit e)
	{
		super(e);
	}

	public String getType()
	{
		return "Meme";
	}
	
	/**
	 * Les memes peuvent être transmis à d'autres
	 * Plus le tôt de transfert est élevé, plus c'est aisé
	 */
	public int getTauxTransfert()
	{
		return 0;
	}
	
	public Color getColor()
	{
		return Color.PINK;
	}
	
}
