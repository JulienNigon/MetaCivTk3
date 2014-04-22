package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class A_AddObject extends Action{

	String ObjectName;
	Integer variation;
	
	
	public Action effectuer(Humain h) {
		h.getInventaire().addObjets(Configuration.getObjetByName(ObjectName), variation);
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);

		if (option.getParametres().get(0).getClass().equals(Objet.class)) {
			ObjectName = ((Objet) option.getParametres().get(0)).getNom();
		} 
		else if (option.getParametres().get(0).getClass() == Integer.class) {
			variation = (Integer) option.getParametres().get(0);
		}

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] attrName = {"**Objet**" , "Changed object"};
			String[] n = {"**Integer**" , "n", "-10" , "10" , "1"};

			schemaParametres.add(attrName);
			schemaParametres.add(n);

		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Change the current value of an attribute.<html>";
	}

}
