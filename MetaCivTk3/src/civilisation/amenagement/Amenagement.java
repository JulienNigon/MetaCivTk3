package civilisation.amenagement;

import java.awt.Graphics;
import java.util.ArrayList;

import civilisation.effects.Effect;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;
import turtlekit.kernel.Patch;

public class Amenagement {

	Patch position; //Le patch contenant l'am___nagement
	Humain possesseur; //Possesseur de l'amenagement
	String type;
	ArrayList<Effect> effets;
	ArrayList<Objet> recette;
	ArrayList<Integer> nombre;
	
	
	public Amenagement(Patch p, Humain h)
	{
		position = p;
		possesseur = h;
		p.dropMark(type, this);
	}
	
	public void dessiner(Graphics g,int x,int y,int cellS)
	{
		
	}
	
	public void Utiliser()
	{
		for(int i = 0; i < this.effets.size();++i)
		{
			if(this.effets.get(i).getActivation() == 1)
			{
				this.effets.get(i).Activer(possesseur);
			}
		}
	}
	
	public String getNom()
	{
		return "---Amenagement---";
	}

	public Humain getPossesseur() {
		return possesseur;
	}

	public void setPossesseur(Humain possesseur) {
		this.possesseur = possesseur;
	}

	public Patch getPosition() {
		return position;
	}

	/**
	 * Les cognitons declench___s par cet amenagement
	 * @return tableau de string repr___sentant les cognitons
	 */
	public String[] cognitonsLies()
	{
		return null;
	}
	
	
}
