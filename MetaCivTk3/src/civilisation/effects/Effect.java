package civilisation.effects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;

public class Effect {

	private String target; // la cible est un attribut ou un cogniton ?
	private String Varget; // nom de l'attribut ou du cogniton ciblé
	private Double value; // valeur de la modification
	private String name;
	private boolean permanent;
	private int type; // 0 = add or replace , 1 = modify , 2 = remove
	private int activation; //0 = posses , 1 = use
	private String Description;
	
	public Effect()
	{
		
	}
	
	public Effect(String name,String target,String varget, Double value,boolean permanent,int type)
	{
		this.Varget = varget;
		this.target = target;
		this.name = name;
		this.permanent = permanent;
		this.value = value;
		this.setType(type);
	}
	
	/**
	 * TODO faire la partie modification de cognitons
	 * @param h
	 * @param e
	 */

	public void Activer(Humain h)
	{
		if(this.target.equals("attribut"))
		{
			switch(type){
			case 0 :	//add or replace
				h.getAttr().put(Varget, value.intValue());
				break;
				
			case 1 :	//modify
				int attr = h.getAttr().get(Varget);
				h.getAttr().put(Varget, attr + value.intValue());
				break;
			
			case 2 :	//remove
				h.getAttr().remove(Varget);
				break;
			default :
				break;
			}
		}
		else
		{
			switch(type){
			case 0 :	//add or replace
				h.getEsprit().setCogniton(Configuration.getCognitonByName(Varget), value);
				break;
				
			case 1 :	//modify
				h.getEsprit().AddWeightToCogniton(Configuration.getCognitonByName(Varget), value);
				break;
			
			case 2 :	//remove
				h.getEsprit().removeCogniton(Configuration.getCognitonByName(Varget));
				break;
			default :
				break;
			}
		}
	}
	
	public void Desactiver(Humain h)
	{
		if(this.target == "attribut")
		{
			if(type == 1)
			{
				int attr = h.getAttr().get(Varget);
				h.getAttr().put(Varget, attr - value.intValue());
			}
		}
		else
		{
			if(type == 1)
			{
				h.getEsprit().AddWeightToCogniton(Configuration.getCognitonByName(Varget), -value);
			}
		}
	}
	
	
	public void enregistrer(File cible) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(cible.getPath()+"/"+getName()+Configuration.getExtension()));
			out.println("Nom : " + getName());
			out.println("Description : " + getDescription());
			out.println("Cible : " + getTarget());
			out.println("NomCible : " + getVarget());
			out.println("Valeur : " + getValue());
			out.println("Permanence : " + isPermanent());
			out.println("Type : " + getType());
			out.println("Activation : "+ getActivation());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public boolean isPermanent()
	{
		return this.permanent;
	}
	
	public void setPermanent(boolean perm)
	{
		this.permanent = perm;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getVarget() {
		return Varget;
	}
	public void setVarget(String varget) {
		this.Varget = varget;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public int getActivation() {
		return activation;
	}

	public void setActivation(int activation) {
		this.activation = activation;
	}
}
