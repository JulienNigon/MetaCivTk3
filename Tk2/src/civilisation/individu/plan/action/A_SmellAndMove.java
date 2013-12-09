package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.ItemPheromone;
import civilisation.individu.Humain;

public class A_SmellAndMove extends Action{

	ItemPheromone phero;

	@Override
	public Action effectuer(Humain h) {
		System.out.println(phero.getNom());
		System.out.println(h.getHeadingToMaxOf(phero.getNom(), 1, false));

		h.face(h.getPatchWithMaxOf(phero.getNom(), 1));
		h.fd(1);
		return nextAction;
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass() == ItemPheromone.class) {
			phero = (ItemPheromone) option.getParametres().get(0);
		}

		

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] pheroName = {"**Pheromone**" , "PheroToFollow"};

			schemaParametres.add(pheroName);

		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Turn the agent toward the patch in his immediate vicinity with the highest value <br> for a specified pheromone.<html>";
	}


	
}
