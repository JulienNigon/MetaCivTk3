package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inventaire.Objet;

public class A_AddObjectXCogniton extends Action{

	String ObjectName;
	Double variation;
	Integer base;
	TypeCogniton cogniton;
	
	
	public Action effectuer(Humain h) {
		Cogniton cog = h.getEsprit().getCognitonOfType(cogniton);
		if (cog == null) {
			for (int i = 0 ; i < base ; i++) {
				h.getInventaire().addObjets(Configuration.getObjetByName(ObjectName), 1);
			}
		} else {
			int v = base + (int)(cog.getWeigth()*variation);
			for (int i = 0 ; i < v ; i++) {
				h.getInventaire().addObjets(Configuration.getObjetByName(ObjectName), 1);
			}
		}
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);

		if (option.getParametres().get(0).getClass().equals(Objet.class)) {
			ObjectName = ((Objet) option.getParametres().get(0)).getNom();
		} 
		else if (option.getParametres().get(0).getClass() == Integer.class) {
			base = (Integer) option.getParametres().get(0);	
		}
		else if (option.getParametres().get(0).getClass().equals(TypeCogniton.class)){
			cogniton = (TypeCogniton) option.getParametres().get(0);
		}
		else if (option.getParametres().get(0).getClass().equals(Double.class)){
			variation = (Double) option.getParametres().get(0);
		}
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] attrName = {"**Objet**" , "Changed object"};
			String[] var = {"**Double**" , "variation", "-10.0" , "10.0" , "0.1","100"};
			String[] base = {"**Integer**" , "base", "-10" , "10" , "1"};
			String[] cog = {"**Cogniton**" , "Cogniton"};

			schemaParametres.add(attrName);
			schemaParametres.add(var);
			schemaParametres.add(base);
			schemaParametres.add(cog);

		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Add X item to the agent.<br> X is the base value, plus the variation value * weight of teh specified cogniton.<html>";
	}

}
