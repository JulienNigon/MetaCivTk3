package civilisation.inspecteur.simulation.environnement;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.inspecteur.animations.*;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.world.Terrain;


public class PanelEnvironnement extends JJPanel{

	PanelModificationSimulation panelPrincipal;
	
	/*Parametres de la carte*/
	ArrayList<ArrayList<PseudoPatch>> carte;
	int largeur = 120;
	int hauteur = 120;
	int taillePseudoPatch = 2;
	GCarte gCarte;
	
	/*Parametres d'affichage*/
	int espacementVerticalCarte = 80;
	int espacementHorizontalCarte = 80;
	
	/*Mode de dessin*/
	int dessin = 0; /*0 = crayon , 1 = pot de peinture*/


	
	public PanelEnvironnement(PanelModificationSimulation panel){
		this.setLayout(null);

		panelPrincipal = panel;
		carte = new ArrayList<ArrayList<PseudoPatch>>();
		redimensionner();
		gCarte = new GCarte(this, espacementHorizontalCarte, espacementVerticalCarte, this);
		gCarte.setLocation(40,40);
		this.add(gCarte);

	}
	
	
	protected void redimensionner(){

		Terrain terrainParDefaut = Configuration.terrains.get(0);

		while(carte.size() > hauteur){
			carte.remove(carte.size()-1);
		}
		while(carte.size() < hauteur){
			carte.add(new ArrayList<PseudoPatch>());
		}
		for (int i = 0; i < hauteur; i++){
			while(carte.get(i).size() > largeur){
				carte.get(i).remove(carte.get(i).size()-1);
			}
			while(carte.get(i).size() < largeur){
				carte.get(i).add(new PseudoPatch(terrainParDefaut,carte.get(i).size() - 1,i));
			}
		}
		
	}
	

	public void sauvegarderEnvironnement(String nomDeSauvegarde){
		File environnements = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements");
	if(!environnements.isDirectory()) environnements.mkdir();
	
	PrintWriter out;
	try {
		
		
		out = new PrintWriter(new FileWriter(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+nomDeSauvegarde+Configuration.getExtension()));
		out.println("Nom : " + nomDeSauvegarde);
		out.println("Largeur : " + largeur);
		out.println("Hauteur : " + hauteur);
		
		for (int i = 0; i < Configuration.civilisations.size(); i++){
			if (gCarte.getStartingPositions().containsKey(Configuration.civilisations.get(i))){
				out.println("Civilisation : " + Configuration.civilisations.get(i).getNom() + "," + gCarte.getStartingPositions().get(Configuration.civilisations.get(i)).getX() + "," + gCarte.getStartingPositions().get(Configuration.civilisations.get(i)).getY());
			}
		}
		
		out.println();
		for (int i = 0;i < Configuration.terrains.size(); i++){
			out.println("Terrain : " + Configuration.terrains.get(i).getNom());
		}
		for (int i = 0; i < this.getCarte().size()  ; i++){
			out.print("Rang : ");
			for (int j = 0; j < this.getCarte().get(i).size()  ; j++){
				Terrain t = this.getCarte().get(i).get(j).getTerrain();
				int k = 0;
				while (!t.equals(Configuration.terrains.get(k))){
					k++;
				}
				out.print(k);
				if (j != this.getCarte().get(i).size()-1){
					out.print(",");	
				}
			}
			out.println();
		}
		
		out.close();
	} catch (IOException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	} 
	}

	public void generationEnvironnementViaImage(BufferedImage img){
		largeur = img.getWidth();
		hauteur = img.getHeight();
		redimensionner();
		
		for (int i = 0; i < hauteur; i++){
			System.out.println(i/(double)hauteur *100. +"%");
			for (int j = 0; j < largeur; j++){
				carte.get(i).get(j).setTerrain(getTerrainSemblable(new Color(img.getRGB(j, i))));
			}
		}
		
		gCarte.actualiser();
	}
	
	/*
	 * Retourne le terrain le plus proche en fonction de la couleur passŽe en paramtre
	 */
	private Terrain getTerrainSemblable(Color c){
		int variation = 10000;
		int var;
		Terrain t = null;
		for (int i = 0; i < Configuration.terrains.size(); i++){
			var = 0;
			Color ref = Configuration.terrains.get(i).getCouleur();
			var += Math.abs(c.getRed() - ref.getRed());
			var += Math.abs(c.getBlue() - ref.getBlue());
			var += Math.abs(c.getGreen() - ref.getGreen());
			if (var < variation){
				t = Configuration.terrains.get(i);
				variation = var;
			}

		}
		return t;
	}
	
	public ArrayList<ArrayList<PseudoPatch>> getCarte() {
		return carte;
	}


	public void setCarte(ArrayList<ArrayList<PseudoPatch>> carte) {
		this.carte = carte;
	}


	public int getLargeur() {
		return largeur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}


	public int getHauteur() {
		return hauteur;
	}


	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}


	public int getTaillePseudoPatch() {
		return taillePseudoPatch;
	}


	public void setTaillePseudoPatch(int taillePseudoPatch) {
		this.taillePseudoPatch = taillePseudoPatch;
	}


	public PanelModificationSimulation getPanelPrincipal() {
		return panelPrincipal;
	}

    public void zoom(int z){
    	taillePseudoPatch += z;
		gCarte.actualiser();

		this.revalidate();
    }


	public int getTypeDessin() {
		return dessin;
	}


	public void setTypeDessin(int dessin) {
		this.dessin = dessin;
	}
    
	
    
}
