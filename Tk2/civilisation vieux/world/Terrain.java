package civilisation.world;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.ItemPheromone;

public class Terrain {

	/** 
	 * Conserve les informations relatives aux différents types de territoires qui compose l'environnement des agents.
	 * @version MetaCiv 1.0
	*/
	
	String nom;
	Color couleur;
	int passabilite;
	ArrayList<ItemPheromone> pheromones;
	ArrayList<Double> pheroInitiales;
	ArrayList<Double> pheroCroissance;
	static final int passabiliteParDefaut = 35;
	
	public Terrain(String nom){
		this.nom = nom;
		pheromones = new ArrayList<ItemPheromone>();
		pheroInitiales = new ArrayList<Double>();
		pheroCroissance = new ArrayList<Double>();
		couleur = Color.BLACK;
		passabilite = passabiliteParDefaut;
	}

	public int getPassabilite() {
		return passabilite;
	}

	public void setPassabilite(int passabilite) {
		this.passabilite = passabilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	public void addPheromoneLiee(ItemPheromone phero, Double init, Double croissance){
		pheromones.add(phero);
		pheroInitiales.add(init);
		pheroCroissance.add(croissance);
	}
	
	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			
		    float hsb[] = Color.RGBtoHSB( this.getCouleur().getRed(),this.getCouleur().getGreen(),this.getCouleur().getBlue(), null );
			
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Couleur : "+hsb[0]+","+hsb[1]+","+hsb[2]);
			out.println("Passabilite : "+passabiliteParDefaut);

			for (int i = 0; i < this.pheromones.size();i++){
				out.println("Pheromone : " + pheromones.get(i).getNom() + "," + pheroInitiales.get(i)  + "," + pheroCroissance.get(i));
			}
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	
	
}
