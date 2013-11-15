package civilisation.individu.plan;

import java.util.ArrayList;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.ObjetInventaire;
import civilisation.inventaire.Objet_Arme;
import civilisation.inventaire.Objet_Baies;
import civilisation.inventaire.Objet_Or;
import civilisation.inventaire.Objet_Viande;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Prier extends Plan {

	public PLAN_Prier(Esprit e, Humain h) {
		super(e,h);
	}
	
	public String getNom(){
		return "Prier";
	}

	/**
	 * L'agent satifait son besoin de prier
	 */
	
	public void Activer()
	{
		h.prier();
		e.finProjet();
	}
	

	public int getTempsMax() {
		return 100;
	}
	
	
}
