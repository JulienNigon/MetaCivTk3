package civilisation.individu.plan;

import civilisation.amenagement.Amenagement;
import civilisation.amenagement.Amenagement_Champ;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Cereale;
import civilisation.inventaire.Objet_Viande;
import civilisation.world.World;
import edu.turtlekit2.kernel.environment.Patch;

public class REFLEXE_Manger extends Reflexe {

	Amenagement champCible;
	
	
	public REFLEXE_Manger(Esprit e, Humain h) {
		super(e,h);
	}

	public String getNom(){
		return "Manger";
	}
	
	public void Activer()
	{	
		h.manger();
		
	}

	public int getTempsMax() {
		return 35;
	}
	
	public int getSeuil() {
		return 10;
	}
	
	public int getPriorite() {
		return 10;
	}
	
	
}
