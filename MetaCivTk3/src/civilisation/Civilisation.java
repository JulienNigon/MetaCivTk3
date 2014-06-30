package civilisation;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import civilisation.individu.cognitons.TypeCogniton;
import civilisation.world.World;

public class Civilisation {

	static int nombreCiv = 0;
	static ArrayList<Civilisation> listeCiv = new ArrayList<Civilisation>();

	String nom;
	Color couleur;
	int indexCiv;
	int agentsInitiaux;
	int scatteredModifier;
	ArrayList<TypeCogniton> startingCognitons = new ArrayList<TypeCogniton>();
	boolean mustBeSaved = true;
	
	
	public Civilisation ()
	{
		couleur = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		indexCiv = nombreCiv; 
		nombreCiv ++;
		listeCiv.add(this);
	}
	
	public Civilisation createDaugtherCivilization() {
		Civilisation civ = new Civilisation();
		civ.startingCognitons = startingCognitons;
		mustBeSaved = false;
		Configuration.civilisations.add(civ);
		return civ;
	}


	
	public void enregistrer(File cible) {
		if (mustBeSaved) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Agents : " + agentsInitiaux);
			out.println("Scattered : " + this.scatteredModifier);

		    float hsb[] = Color.RGBtoHSB( this.getCouleur().getRed(),this.getCouleur().getGreen(),this.getCouleur().getBlue(), null );
			out.println("Couleur : "+hsb[0]+","+hsb[1]+","+hsb[2]);
			
			for (int i = 0 ; i < startingCognitons.size() ; i++) {
				out.println("Cogniton : "+startingCognitons.get(i).getNom());
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} 
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

	public ArrayList<TypeCogniton> getStartingCognitons() {
		return startingCognitons;
	}

	public void setStartingCognitons(ArrayList<TypeCogniton> startingCognitons) {
		this.startingCognitons = startingCognitons;
	}

	public int getScatteredModifier() {
		return scatteredModifier;
	}

	public void setScatteredModifier(int scatteredModifier) {
		this.scatteredModifier = scatteredModifier;
	}
	
	
	
}
