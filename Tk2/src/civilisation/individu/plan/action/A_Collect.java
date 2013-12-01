package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.ItemPheromone;
import civilisation.individu.Humain;

public class A_Collect extends Action{

	ItemPheromone pheroToCollect;
	Double nPheroToCollect;

	@Override
	public Action effectuer(Humain h) {
		h.emit(pheroToCollect.getNom(), nPheroToCollect);
		
		return nextAction;
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass() == ItemPheromone.class) {
			System.out.println("itemPhero bien reçu");
			pheroToCollect = (ItemPheromone) option.getParametres().get(0);
		}
		else if (option.getParametres().get(0).getClass() == Double.class) {
			nPheroToCollect = (Double) option.getParametres().get(0);
		}
		

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] pheroName = {"**Pheromone**" , "PheroToCollect"};
			String[] n = {"**Double**" , "n", "-10.0" , "10.0" , "0.1","100"};

			schemaParametres.add(pheroName);
			schemaParametres.add(n);

		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Add or remove some pheromones from the cirrent patch.<html>";
	}


	
}
