package civilisation.individu.plan.action;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import turtlekit.kernel.Turtle;
import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inventaire.Objet;

public class A_EmitCogniton extends Action{
	
	TypeCogniton cogniton;
	Double change;

	
	@Override
	public Action effectuer(Humain h) {
		
		List<Turtle> turtles = h.turtlesHere();
		
		for (Turtle t : turtles) {
			if (t instanceof Humain) {
				((Humain) t).getEsprit().AddWeightToCogniton(cogniton, change);
			}
		}
		
		return nextAction;
		
	}

	@Override
	public String getInfo() {
		return super.getInfo() + " Change the weight for a cogniton for all agents on the patch.<br>If agent doesn't have the cogniton, nothing happens.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(TypeCogniton.class)){
			cogniton = (TypeCogniton) option.getParametres().get(0);
		}
		if (option.getParametres().get(0).getClass().equals(Double.class)){
			change = (Double) option.getParametres().get(0);
		}
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			
			
			String[] c = {"**Cogniton**" , "Cogniton"};
			String[] n = {"**Double**" , "n", "-10.0" , "10.0" , "0.1","100"};

			schemaParametres.add(c);
			schemaParametres.add(n);


		}
		return schemaParametres;	
	}
	
}
