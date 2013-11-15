package civilisation.urbanisme;

import civilisation.Communaute;
import civilisation.individu.Humain;

public class Batiment_Grenier extends Batiment{

	public Batiment_Grenier(Humain possesseur, Communaute c) {
		super(possesseur , c);
		bois_requis = 5;
		pierre_requis = 2;
		
	}

	@Override
	public String toString()
	{
		return "Grenier";
	}
	
}
