package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.ObjetInventaire;
import civilisation.inventaire.Objet_Baies;
import civilisation.inventaire.Objet_Outil;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_Fabriquer extends Plan{

	public PLAN_Fabriquer(Esprit e, Humain h) {
		super(e,h);
	}
	
	public String getNom(){
		return "Fabriquer ";
	}

	/**
	 * L'agent fabrique quelquechose
	 */
	
	public void Activer(ObjetInventaire objet)
	{
		int bois = objet.getCoutBois();
		int metal = objet.getCoutMetal();
		int viande = objet.getCoutViande();
		
		if(h.getInventaire().getListObjet("Bois").size() >= bois && h.getInventaire().getListObjet("Objet_Fer").size() >= metal && h.getInventaire().getListObjet("Objet_Viande").size() >= viande )
		{
			h.getInventaire().remove("Bois", bois);
			h.getInventaire().remove("Fer", metal);
			h.getInventaire().remove("Viande", viande);
			h.getInventaire().add(objet);
		}
	}
	

	public int getTempsMax() {
		return 100;
	}
	
}
