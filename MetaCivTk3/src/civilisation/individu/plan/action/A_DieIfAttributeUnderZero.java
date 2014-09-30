package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class A_DieIfAttributeUnderZero extends Action{
	
	String attribute;

	
	@Override
	public Action effectuer(Humain h) {
		

		if (h.getAttr().get(attribute) <= 0) {
			h.die();
		}
		return nextAction;
		
	}

	@Override
	public ImageIcon getIcon(){
		return Configuration.getIcon("headstone-rip.png");
	}

	
	@Override
	public String getInfo() {
		return super.getInfo() + " Kill the agent if the specified attribute is under a specific value.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(String.class)){
			attribute = (String) option.getParametres().get(0);
		}

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			
			
			String[] attr = {"**Attribute**" , "attributeToCompare"};
	
			schemaParametres.add(attr);

		}
		return schemaParametres;	
	}
	
}
