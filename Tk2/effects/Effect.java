package civilisation.effects;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;

public class Effect {

	private String target;
	private String Varget;
	private int value;
	private String name;
	private boolean permanent;
	private String type;
	
	public Effect(String name,String target,String varget, int value,boolean permanent,String type)
	{
		this.Varget = varget;
		this.target = target;
		this.name = name;
		this.permanent = permanent;
		this.value = value;
		this.setType(type);
	}
	
	public Effect(String target,String varget, int value,boolean permanent,String type)
	{
		this.Varget = varget;
		this.target = target;
		this.value = value;
		this.permanent = permanent;
		this.setType(type);
	}
	
	public void Activer(Humain h, Esprit e)
	{
		if(this.target == "corps")
		{
	/*		if(h.get(this.Varget) != "echec")
			{
				Object temp = h.get(this.Varget);
				int tempo = 0;
				if(temp.getClass() == Integer.class)
				{
					tempo = (Integer) temp;
				}
				h.set(this.Varget, tempo + this.value);
			}
			else
			{
				h.set(this.Varget, this.value);
			}*/
		}
		else
		{
			//a faire plus tard
		}
	}
	
	public void Desactiver(Humain h, Esprit e)
	{
		if(this.target == "corps")
		{
	/*		if(h.get(this.Varget) != "echec")
			{
				Object temp = h.get(this.Varget);
				int tempo = 0;
				if(temp.getClass() == Integer.class)
				{
					tempo = (Integer) temp;
				}
				h.set(this.Varget, tempo - this.value);
			}*/
		}
		else
		{
			//a faire plus tard
		}
	}
	
	public boolean isPermanent()
	{
		return this.permanent;
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
