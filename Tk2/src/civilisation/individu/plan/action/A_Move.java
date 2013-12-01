package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.individu.Humain;

public class A_Move extends Action{

	double angle;
	
	@Override
	public Action effectuer(Humain h) {
		h.setHeading(angle);
		h.fd(1);
		
		return nextAction;
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		
		if (option.getName().equals("SOUTH")){
			angle = 270.;
		}
		else if (option.getName().equals("WEST")){
			angle = 180.;
		}
		else if (option.getName().equals("NORTH")){
			angle = 90.;
		}
		else if (option.getName().equals("EAST")){
			angle = 0.;
		}
	}
	

	/**
	 * Retourne la structure des paramètres.
	 * Permet de déterminer la présentation de la fenêtre de réglages.
	 */
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] directions = {"NORTH","SOUTH","WEST","EAST"};
			schemaParametres.add(directions);
		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " The agent move in one direction.<html>";
	}

}
