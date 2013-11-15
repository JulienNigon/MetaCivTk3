package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;

/** 
 * Classe abstraite représentant un "réflexe", un plan se déclenchant automatiquement si le seuil est atteint
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class Reflexe extends Plan{

	public Reflexe(Esprit e, Humain h) {
		super(e, h);
	}

	/**
	 * Le seuil à partir duquel le réflexe se déclenche (10 par défaut)
	 * @return le seuil du réflexe
	 */
	public int getSeuil() {
		return 10;
	}
	
	/**
	 * @return la priorité de ce réflexe par rapport aux autres (dans le cas ou plusieurs sont déclenchés simultanement
	 */
	public int getPriorite() {
		return 0;
	}
	
	/**
	 * Le seuil à partir duquel le réflexe se déclenche (10 par défaut)
	 * @return le seuil du réflexe
	 */
	public boolean isTriggered() {
		return (getSeuil() <= getPoids());
	}
	
	public int getType(){
		return 1; //1 = reflexe
	}
	
	/**
	 * Modifie le poids du projet d'un poids p,
	* Ne modifie pas le poids total
	 */
	public void changerPoids(int p)
	{

		poids += p;
	}
}
