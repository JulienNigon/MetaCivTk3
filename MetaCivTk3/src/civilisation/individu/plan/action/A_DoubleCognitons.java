package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.NCogniton;
import civilisation.inventaire.Objet;

public class A_DoubleCognitons extends Action{
	
	NCogniton cogniton1 , cogniton2;

	
	@Override
	public Action effectuer(Humain h) {
		
		h.getEsprit().addCogniton(cogniton1);
		h.getEsprit().addCogniton(cogniton2);
		return nextAction;
		
	}

	@Override
	public ImageIcon getIcon(){
		return new ImageIcon(this.getClass().getResource("../../../inspecteur/icones/processor.png"));
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Add a new cogniton to the agent.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(NCogniton.class)){
			if (option.getName().equals("Cogniton1")) {
				cogniton1 = (NCogniton) option.getParametres().get(0);
			} else if (option.getName().equals("Cogniton2")) {
				cogniton2 = (NCogniton) option.getParametres().get(0);
			}
			
		}

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			
			
			String[] cog1 = {"**Cogniton**" , "Cogniton1"};
			String[] cog2 = {"**Cogniton**" , "Cogniton2"};

			schemaParametres.add(cog1);
			schemaParametres.add(cog2);


		}
		return schemaParametres;	
	}
	
}
