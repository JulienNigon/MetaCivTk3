package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import turtlekit.kernel.Turtle;

public class A_VoyagerVersUneAutreVille extends Action{

	String attributeToIncrease;
	
	@Override
	public Action effectuer(Humain h) {
		h.getPatch().dropPheromone("passage", 50.0f);
		h.putAttr(attributeToIncrease, h.getAttr().get(attributeToIncrease) + 5 );
		//System.out.println("ma communaute " + h.getCommunaute());
		//System.out.println("start voyager" + h.TurtlesWithRole("Communaute").size() + " " + h.oneOf(h.TurtlesWithRole("Communaute")).toString());
		if (h.getEsprit().getActionData(this) == null)
		{
			h.getEsprit().setActionData(this, h.oneOf(h.TurtlesWithRole("Communaute")));
		}
		//System.out.println("cible voyager" + (Turtle)h.getEsprit().getActionData(this));

		h.allerVers((Turtle)h.getEsprit().getActionData(this));
		if (h.getPatch().x == ((Turtle)h.getEsprit().getActionData(this)).getPatch().x && h.getPatch().y == ((Turtle)h.getEsprit().getActionData(this)).getPatch().y) {					
			h.getEsprit().cleanActionData(this);
			//System.out.println(this + " : arrive");
			return nextAction;
		} else {
			//System.out.println(this);
			return this;
		}
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass() == String.class) {
			attributeToIncrease = (String) option.getParametres().get(0);
		}

		

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] pheroName = {"**Attribute**" , "Attribute"};

			schemaParametres.add(pheroName);
		}
		return schemaParametres;	
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Go to a random city, and each tick increase a specific attribute for testing purpose<html>";
	}


	
}
