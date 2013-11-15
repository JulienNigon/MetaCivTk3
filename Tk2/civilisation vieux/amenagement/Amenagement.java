package civilisation.amenagement;

import java.awt.Graphics;

import civilisation.individu.Humain;

import edu.turtlekit2.kernel.environment.Patch;

public class Amenagement {

	Patch position; //Le patch contenant l'aménagement
	Humain possesseur; //Possesseur de l'amenagement
	
	
	public Amenagement(Patch p, Humain h)
	{
		position = p;
		possesseur = h;
	}
	
	public void dessiner(Graphics g,int x,int y,int cellS)
	{
		
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
	 * Les cognitons declenchés par cet amenagement
	 * @return tableau de string représentant les cognitons
	 */
	public String[] cognitonsLies()
	{
		return null;
	}
	
	
}
