package civilisation.individu.plan.action;

import java.util.ArrayList;

import turtlekit.kernel.Patch;
import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.world.World;

public class A_SmellAndMove extends Action{

	ItemPheromone phero;

	@Override
	public Action effectuer(Humain h) {

		Patch p = h.getPatchWithMaxOf(phero.getNom(), 1);
		if(p != null)
		{
			h.setHeading(h.towards(p));
			h.fd(1);
		}
		else
		{
			h.fd(1);
		}
		
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
