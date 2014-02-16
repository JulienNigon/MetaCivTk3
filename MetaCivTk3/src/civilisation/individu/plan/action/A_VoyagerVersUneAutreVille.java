package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.individu.Humain;
import turtlekit.kernel.Turtle;

public class A_VoyagerVersUneAutreVille extends Action{

	@Override
	public Action effectuer(Humain h) {
		System.out.println("ma communaute " + h.getCommunaute());
		System.out.println("start voyager" + h.TurtlesWithRole("Communaute").size() + " " + h.oneOf(h.TurtlesWithRole("Communaute")).toString());
		if (h.getEsprit().getActionData(this) == null)
		{
			h.getEsprit().setActionData(this, h.oneOf(h.TurtlesWithRole("Communaute")));
		}
		System.out.println("cible voyager" + (Turtle)h.getEsprit().getActionData(this));

		h.allerVers((Turtle)h.getEsprit().getActionData(this));
		if (h.getPatch().x == ((Turtle)h.getEsprit().getActionData(this)).getPatch().x && h.getPatch().y == ((Turtle)h.getEsprit().getActionData(this)).getPatch().y) {					
			h.getEsprit().cleanActionData(this);
			System.out.println(this + " : arrive");
			return nextAction;
		} else {
			System.out.println(this);
			return this;
		}
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);	
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		schemaParametres = new ArrayList<String[]>();
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Go back to home.<html>";
	}


	
}
