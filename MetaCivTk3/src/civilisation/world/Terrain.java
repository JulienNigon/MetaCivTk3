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
	 * Conserve les informations relatives aux diff_rents types de territoires qui compose l'environnement des agents.
	 * @version MetaCiv 1.0
	*/
	
	String nom;
	Color couleur;
	int passabilite;
	ArrayList<ItemPheromone> pheromones;
	ArrayList<Double> pheroInitiales;
	ArrayList<Double> pheroCroissance;
	static final int passabiliteParDefaut = 35;
	Boolean infranchissable;
	
	int altmax;
	int altmin;
	int apparition;
	int tempideale;
	
	Terrain[] suivants;
	int[] poidSuivant;
	
	public Terrain(String nom){
		this.nom = nom;
		pheromones = new ArrayList<ItemPheromone>();
		pheroInitiales = new ArrayList<Double>();
		pheroCroissance = new ArrayList<Double>();
		couleur = Color.BLACK;
		passabilite = passabiliteParDefaut;
		infranchissable = false;
		this.suivants = new Terrain[1];
		this.poidSuivant = new int[1];
		
		this.suivants = new Terrain[1];
		this.poidSuivant = new int[1];
		this.altmin = 0;
		this.altmax = 20;
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
	
	public Boolean getInfranchissable() {
		return infranchissable;
	}

	public void setInfranchissable(Boolean infranchissable) {
		this.infranchissable = infranchissable;
	}
	
	public int getAltMax()
	{
		return this.altmax;
	}
	
	public void setAltMax(int max)
	{
		this.altmax = max;
	}
	
	public int getAltMin()
	{
		return this.altmin;
	}
	
	public void setAltMin(int min)
	{
		this.altmin = min;
	}

	public void addPheromoneLiee(ItemPheromone phero, Double init, Double croissance){
		pheromones.add(phero);
		pheroInitiales.add(init);
		pheroCroissance.add(croissance);
	}
	
	public void clearPheromones() {
		pheromones.clear();
		pheroInitiales.clear();
		pheroCroissance.clear();
	}
	
	public void addSuivants(Terrain[] suivants)
	{
		this.suivants = suivants;
	}
	
	public int[] getPoidsuivant()
	{
		return poidSuivant;
	}
	
	public int getApparition()
	{
		return this.apparition;
	}
	
	public void addSuivants(int[] poids)
	{
		this.poidSuivant = poids;
	}
	
	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			
		    float hsb[] = Color.RGBtoHSB( this.getCouleur().getRed(),this.getCouleur().getGreen(),this.getCouleur().getBlue(), null );
			
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Couleur : "+hsb[0]+","+hsb[1]+","+hsb[2]);
			out.println("Passabilite : "+this.getPassabilite());
			out.println("Infranchissable : " + infranchissable);
			out.println("AltMin : " + this.getAltMin());
			out.println("AltMax : " + this.getAltMax());
			out.println("Apparition : " + this.getApparition());

			for (int i = 0; i < this.pheromones.size();i++){
				out.println("Pheromone : " + pheromones.get(i).getNom() + "," + pheroInitiales.get(i)  + "," + pheroCroissance.get(i));
			}
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public int getPheromoneIndexByName(String s){
		for (int i = 0 ; i < pheromones.size(); i++){
			if (pheromones.get(i).getNom().equals(s)){
				return i;
			}
		}
		return -1;
	}

	public ArrayList<ItemPheromone> getPheromones() {
		return pheromones;
	}

	public void setPheromones(ArrayList<ItemPheromone> pheromones) {
		this.pheromones = pheromones;
	}

	public ArrayList<Double> getPheroInitiales() {
		return pheroInitiales;
	}

	public void setPheroInitiales(ArrayList<Double> pheroInitiales) {
		this.pheroInitiales = pheroInitiales;
	}

	public ArrayList<Double> getPheroCroissance() {
		return pheroCroissance;
	}

	public void setPheroCroissance(ArrayList<Double> pheroCroissance) {
		this.pheroCroissance = pheroCroissance;
	}

	
	
	
}
