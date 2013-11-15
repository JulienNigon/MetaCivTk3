package civilisation.urbanisme;

import civilisation.Communaute;
import civilisation.individu.Humain;

public class Batiment_Hutte extends Batiment{

	public Batiment_Hutte(Humain possesseur, Communaute c) {
		super(possesseur, c);
		bois_requis = 0;
		pierre_requis = 0;
		termine = true;
	}

	@Override
	public String toString()
	{
		return "Hutte";
	}
	
}
