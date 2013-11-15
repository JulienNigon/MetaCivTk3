package civilisation.individu.plan;

import java.awt.Color;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Cheval;
import civilisation.inventaire.Objet_Viande;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_DresserCheval extends Plan {

	public PLAN_DresserCheval(Esprit e, Humain h) {
		super(e,h);
	}

	public String getNom(){
		return "Dresser Cheval";
	}
	
	public void Activer()
	{
		if(!(h.getInventaire().getSize("Cheval") == 1) )
		{
			Color coul = h.position.getColor();
			if (Math.random()*100 < Configuration.TauxDressageCheval && coul.getRed() == 60 && coul.getGreen() == 130 && coul.getBlue()== 60) //Test simple pour dŽterminer si un cheval est trouvé
			{
				
					h.getInventaire().add(new Objet_Cheval());
					h.addInfluence(10);
	
			}
			else
			{
				//System.out.println("heading  " + getHeadingToMaxOf("gibier", 1, true) +"    "+ this.getPatchWithMaxOf("gibier", 1).smell("gibier"));
				h.randomHeading();
				//System.out.println(this.nextPatch().getColor() + "     " + this.smellNextPatch("gibier"));
				h.fd(1);
			}
		}
	}

	public int getTempsMax() {
		return 100;
	}
	
	
	
}
