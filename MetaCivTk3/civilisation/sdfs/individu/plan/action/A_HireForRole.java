package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.ItemPheromone;
import civilisation.group.Group;
import civilisation.group.GroupAndRole;
import civilisation.group.GroupModel;
import civilisation.individu.Humain;
import turtlekit.kernel.Turtle;

public class A_HireForRole extends Action{

	GroupModel group;
	String role;

	@Override
	public Action effectuer(Humain h) {
		Group gr = h.getEsprit().getConcreteGroup(group);
		if (gr != null) {
			Humain target = h.oneOfHumanHere();
			if (target != null) {
				target.getEsprit().joinRestrictiveGroup(gr, role);
			}
		}
		
		return nextAction;
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass() == GroupAndRole.class) {
			group = ((GroupAndRole) option.getParametres().get(0)).getGroupModel();
			role = ((GroupAndRole) option.getParametres().get(0)).getRole();
		}

		

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] group = {"**GroupAndRole**" , "GroupToCreate"};
			schemaParametres.add(group);
		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Pick another random agent in the patch and give it a role in a group.<html>";
	}


	
}
