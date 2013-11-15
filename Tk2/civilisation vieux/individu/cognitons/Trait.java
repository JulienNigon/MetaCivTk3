package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;

public abstract class Trait extends Cogniton{

	
	public Trait(Esprit e) {
		super(e);

		if( getCognitonsOpposes() != null)
		{
			for (int j = 0; j < getCognitonsOpposes().length; j++)
			{
				e.removeCognitonByFullname(getCognitonsOpposes()[j]);
			}
		}
	}

	public String getType()
	{
		return "Trait";
	}
	
	public Color getColor()
	{
		return Color.lightGray;
	}
	
	public String[] getCognitonsOpposes()
	{
		return null;
	}
	
}
