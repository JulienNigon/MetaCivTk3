package civilisation;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import civilisation.world.World;


/** 
 * Classe représentant une civilisation (une "équipe" d'agents)
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class Civilisation {

	String nom;
	Color couleur;
	static int nombreCiv = 0;
	int indexCiv;
	int agentsInitiaux;
	static ArrayList<Civilisation> listeCiv = new ArrayList<Civilisation>();
	ArrayList<Color> inaccessibles;
	
	public Civilisation ()
	{
		couleur = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		indexCiv = nombreCiv; //Les civilisations sont indexées à partir de 0
		nombreCiv ++;
		listeCiv.add(this);
		System.out.println(World.getInstance().gridVariables.size());
		System.out.println("OK : " +  World.getInstance().gridVariables.containsKey("civ"+indexCiv) + " " + "civ"+indexCiv);

		inaccessibles = new ArrayList<Color>();
		inaccessibles.add(new Color(10,10,140));
		inaccessibles.add(new Color(10,10,240));
	}


	
	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Agents : " + agentsInitiaux);

		    float hsb[] = Color.RGBtoHSB( this.getCouleur().getRed(),this.getCouleur().getGreen(),this.getCouleur().getBlue(), null );
			out.println("Couleur : "+hsb[0]+","+hsb[1]+","+hsb[2]);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} 
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
	

	
	public ArrayList<Color> getInaccesibles(){
		 return inaccessibles;
		 }


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public static ArrayList<Civilisation> getListeCiv() {
		return listeCiv;
	}


	public static void setListeCiv(ArrayList<Civilisation> listeCiv) {
		Civilisation.listeCiv = listeCiv;
	}


	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}


	public int getAgentsInitiaux() {
		return agentsInitiaux;
	}


	public void setAgentsInitiaux(int agentsInitiaux) {
		this.agentsInitiaux = agentsInitiaux;
	}



	public void postWorldSetup() {
		World.getInstance().addFlavor("civ"+indexCiv);		
	}
	
	
	
	
}
