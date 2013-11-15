package civilisation;

import java.awt.Color;
import java.util.ArrayList;

import civilisation.world.World;


/** 
 * Classe représentant une civilisation (une "équipe" d'agents)
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class Civilisation {

	Color couleur;
	static int nombreCiv = 0;
	int indexCiv;
	static ArrayList<Color> listeCouleursCiv = new ArrayList<Color>();
	ArrayList<Color> inaccessibles;
	
	public Civilisation ()
	{
		couleur = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		indexCiv = nombreCiv; //Les civilisations sont indexées à partir de 0
		nombreCiv ++;
		World.getInstance().addFlavor("civ"+indexCiv);
		listeCouleursCiv.add(couleur);
		
		inaccessibles = new ArrayList<Color>();
		inaccessibles.add(new Color(10,10,140));
		inaccessibles.add(new Color(10,10,240));
	}


	
	//-------------GETTERS-------------
	
	public Color getCouleur() {
		return couleur;
	}
	
	public int getIndexCiv()
	{
		return indexCiv;
	}
	
	public static int getNombreCiv()
	{
		return nombreCiv;
	}
	
	public static ArrayList<Color> getListeCouleursCiv()
	{
		return listeCouleursCiv;
	}
	
	public ArrayList<Color> getInaccesibles(){
		 return inaccessibles;
		 }
}
