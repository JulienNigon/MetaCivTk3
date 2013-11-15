package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Viande;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_SeReposer extends Plan {

	public PLAN_SeReposer(Esprit e, Humain h) {
		super(e,h);
	}

	public String getNom(){
		return "Chasser";
	}
	
	public void Activer()
	{
		if(h.getInventaire().getSize("Viande") >= 6)
		{
			h.rentrerDeposerRecolte();
		}
		else
		{

		}
	}

	public int getTempsMax() {
		return 5;
	}
	
	
	
}
