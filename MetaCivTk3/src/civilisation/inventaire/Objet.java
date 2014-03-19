package civilisation.inventaire;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;

import civilisation.Configuration;


 

public class Objet {

	String nom;
	ImageIcon icone;
	String nomIcone;
	String description;
	
	static String defaultIconName = "briefcase.png";
	
	public Objet(){
		icone = new ImageIcon(this.getClass().getResource("../inspecteur/icones/"+defaultIconName));
		nomIcone = defaultIconName;
	}
	
	public Objet(String name){
		nom = name;
		icone = Configuration.getIcon(defaultIconName);
		nomIcone = defaultIconName;
	}

	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Description : " + getDescription());
			out.println("Icone : " + nomIcone);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ImageIcon getIcone() {
		return icone;
	}

	public void setIcone(ImageIcon icone) {
		this.icone = icone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setIconeFromString(String s){
		icone = Configuration.getIcon(s);
		nomIcone = s;
	}
	

}
