package civilisation.inventaire;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.effects.Effect;
import civilisation.individu.cognitons.Cogniton;


 

public class Objet {

	String nom;
	ImageIcon icone;
	String nomIcone;
	String description;
	ArrayList<Effect> effets;
	ArrayList<Objet> recette;
	ArrayList<Integer> nombre;
	
	int qualite;
	
	ArrayList<String> objetQualite;
	ArrayList<String> cognitonsQualite; 
	
	static String defaultIconName = "briefcase.png";
	
	public Objet(){
		icone = new ImageIcon(this.getClass().getResource("../inspecteur/icones/"+defaultIconName));
		nomIcone = defaultIconName;
		this.effets = new ArrayList<Effect>();
		this.recette = new ArrayList<Objet>();
		this.nombre = new ArrayList<Integer>();
	}
	
	public Objet(String name){
		nom = name;
		icone = Configuration.getIcon(defaultIconName);
		nomIcone = defaultIconName;
		this.effets = new ArrayList<Effect>();
		this.recette = new ArrayList<Objet>();
		this.nombre = new ArrayList<Integer>();

	}

	public Objet(String name,ArrayList<Effect> effets)
	{
		nom = name;
		icone = Configuration.getIcon(defaultIconName);
		nomIcone = defaultIconName;
		this.effets = effets;
		this.recette = new ArrayList<Objet>();
		this.nombre = new ArrayList<Integer>();
		
		this.calculQualite();
	}
	
	public Objet(String name, Effect effet)
	{
		this.effets = new ArrayList<Effect>();
		this.effets.add(effet);
		this.recette = new ArrayList<Objet>();
		this.nombre = new ArrayList<Integer>();
		
		this.calculQualite();
	}

	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getNom()+Configuration.getExtension()));
			out.println("Nom : " + getNom());
			out.println("Description : " + getDescription());
			out.println("Icone : " + nomIcone);
			out.print("Effects : ");
			System.out.println("Nombre d'effets nregistres "+this.effets.size());
			if(this.effets.size() > 0)
			{
				for(int i = 0; i < this.effets.size();++i)
				{
					out.print(this.effets.get(i).getName() + ",");
				}
			}
			out.println();
			out.print("Objets : ");
			if(this.recette.size() > 0)
			{
				for(int i = 0; i < this.recette.size();++i)
				{
					out.print(this.recette.get(i).getNom() + ",");
				}
			}
			out.println();
			out.print("Nombre : ");
			if(this.nombre.size() > 0)
			{
				for(int i = 0; i < this.nombre.size();++i)
				{
					out.print(this.nombre.get(i) + ",");
				}
			}
				
			
			out.println();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void calculQualite() {
		// TODO Auto-generated method stub
		int sum = 0;
		double moy = 0;
		for(int i = 0; i < this.objetQualite.size();++i)
		{
			//sum += this.objetQualite.get(i).qualite;
		}
		
		moy = sum / this.objetQualite.size();
		
		sum = 0;
		
	}
	
	public void addItemRecipe(String objet, int nombre)
	{
		int i = 0;
		while(i < this.recette.size() && !this.recette.get(i).getNom().equals(objet))
		{
			++i;
		}
		if(i <  this.recette.size())
		{
			this.recette.remove(i);
			int temp = this.nombre.get(i);
			this.nombre.remove(i);
			this.recette.add(Configuration.getObjetByName(objet));
			this.nombre.add(nombre + temp);
		}
		else
		{
			this.recette.add(Configuration.getObjetByName(objet));
			this.nombre.add(nombre);
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
		int i = 0;
		if(e != null)
		{
			while(i < this.effets.size() && this.effets.get(i).getName().equals(e.getName()))
			{
				i++;
			}
			if(i < this.effets.size())
			{
				Configuration.effets.remove(i);
			}
			this.effets.add(e);
		}
		
		
	}
	
	public ArrayList<Effect> getEffets()
	{
		return this.effets;
	}
	
	public ArrayList<Objet> getRecette()
	{
		return recette;
	}
	
	public ArrayList<String> getRecetteString()
	{
		ArrayList<String> strecette = new ArrayList<String>();
		for(int i = 0; i < this.recette.size();++i)
		{
			strecette.add(this.recette.get(i).getNom());
		}
		return strecette;
	}
	
	public ArrayList<Integer> getNombre()
	{
		return this.nombre;
	}
}
