package civilisation.urbanisme;

import civilisation.Communaute;
import civilisation.individu.Humain;
import civilisation.inventaire.NInventaire;



/** 
 * Un bâtiment construit à l'intérieure d'une ville
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class Batiment {

	Humain possesseur; //Si null, indique que ce bâtiment est publique
	Communaute c;
	Boolean termine;
	int bois_requis;
	int pierre_requis;
	NInventaire inventaire;
	
	public Batiment(Humain possesseur , Communaute c)
	{
		this.possesseur = possesseur;
		inventaire = new NInventaire();
		termine = false;
		this.c = c;
		if (possesseur != null)
		{
			possesseur.getBatiments().add(this);
		}
	}
	
	
	/**
	 * Retire le batiment dans la liste de son possesseur et de la ville
	 **/
	public void detruire()
	{
		/* On supprime le batiment de la liste de son possesseur */
		if (possesseur != null)
		{
			for (int i = 0; i < possesseur.getBatiments().size(); i++)
			{
				if (possesseur.getBatiments().get(i) == this)
				{
					possesseur.getBatiments().remove(i);
				}
			}	
		}
		for (int i = 0; i < c.getBatiments().size(); i++)
		{
			if (c.getBatiments().get(i) == this)
			{
				c.getBatiments().remove(i);
			}
		}	
	}
	
	/**
	 * Avance la construction en bois du bâtiment
	 **/
	public void construireBois()
	{
		bois_requis--;
		if (bois_requis == 0 && pierre_requis == 0)
		{
			termine = true;
		}
	}
	
	/**
	 * Avance la construction en pierre du bâtiment
	 **/
	public void construirePierre()
	{
		pierre_requis--;
		if (bois_requis == 0 && pierre_requis == 0)
		{
			termine = true;
		}
	}
	
	public void setTermine(Boolean b)
	{
		termine = b;
	}
	
	public NInventaire getInventaire()
	{
		return inventaire;
	}

	public Communaute getCommunaute()
	{
		return c;
	}
	
	public Humain getPossesseur()
	{
		return possesseur;
	}


	public void setCommunaute(Communaute c) {
		this.c = c;
	}


	
}
