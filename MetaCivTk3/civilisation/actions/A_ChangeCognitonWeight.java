package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.CCogniton;
import civilisation.individu.cognitons.NCogniton;
import civilisation.inventaire.Objet;

public class A_ChangeCognitonWeight extends Action{
	
	NCogniton cogniton;
	Double change;

	
	@Override
	public Action effectuer(Humain h) {
		
		h.getEsprit().changeWeightOfCognitonOfType(cogniton, change);
		
		return nextAction;
		
	}

	@Override
	public String getInfo() {
		return super.getInfo() + " Change the weight for a cogniton.<br>If agent doesn't have the cogniton, nothing happens.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(NCogniton.class)){
			cogniton = (NCogniton) option.getParametres().get(0);
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
