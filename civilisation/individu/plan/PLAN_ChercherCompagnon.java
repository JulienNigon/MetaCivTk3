package civilisation.individu.plan;

import java.lang.reflect.InvocationTargetException;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Viande;
import edu.turtlekit2.kernel.environment.Patch;

public class PLAN_ChercherCompagnon extends Plan {

	public PLAN_ChercherCompagnon(Esprit e, Humain h) {
		super(e,h);
	}

	public String getNom(){
		return "ChercherCompagnon";
	}
	
	public void Activer()
	{
		if (h.isHere(h.getCommunaute()))
		{
			try {
				Class[] mesParamsTypes = new Class[0];
				Humain compagnon = (Humain) h.OneOfHumanHereWith(h.getClass().getDeclaredMethod("isFemme",mesParamsTypes), null, h.isFemme() == false);
				if (compagnon != null)
				{
					if (h.isFemme()){
						h.attendreEnfant(compagnon);
					}
					else
					{
						compagnon.attendreEnfant(h);
					}
					
					
					/* Si l'autre agent aussi cherchait un compagnon, on lui dit que c'est fait*/
					if (compagnon.getEsprit().getProj() != null)
					{
						if ( compagnon.getEsprit().getProj().getNom() == this.getNom())
						{
							compagnon.getEsprit().finProjet(); 
						}				
					}
					e.finProjet();
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else
		{
			h.rentrer();
		}

	}

	public int getTempsMax() {
		return 25;
	}
	
	
	
}
