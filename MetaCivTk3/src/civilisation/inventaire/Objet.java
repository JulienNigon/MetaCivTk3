package civilisation.inventaire;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.effects.Effect;


 

public class Objet {

	String nom;
	ImageIcon icone;
	String nomIcone;
	String description;
	ArrayList<Effect> effets;
	
	static String defaultIconName = "briefcase.png";
	
	public Objet(){
		icone = new ImageIcon(this.getClass().getResource("../inspecteur/icones/"+defaultIconName));
		nomIcone = defaultIconName;
		this.effets = new ArrayList<Effect>();
	}
	
	public Objet(String name){
		nom = name;
		icone = Configuration.getIcon(defaultIconName);
		nomIcone = defaultIconName;
		this.effets = new ArrayList<Effect>();
	}
	
	public Objet(String name,ArrayList<Effect> effets)
	{
		nom = name;
		icone = Configuration.getIcon(defaultIconName);
		nomIcone = defaultIconName;
		this.effets = effets;
	}
	
	public Objet(String name, Effect effet)
	{
		this.effets = new ArrayList<Effect>();
		this.effets.add(effet);
	}

	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Description : " + getDescription());
			out.println("Icone : " + nomIcone);
			out.print("Effects : ");
			if(this.effets.size() > 0)
			{
				for(int i = 0; i < this.effets.size();++i)
				{
					out.print(this.effets.get(i).getName() + ", ");
				}
			}
			
			out.println();
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
	
	public void addEffect(Effect e)
	{
		this.effets.add(e);
	}
	
	public ArrayList<Effect> getEffets()
	{
		return this.effets;
	}
}
