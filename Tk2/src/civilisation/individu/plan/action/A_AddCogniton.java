package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.NCogniton;
import civilisation.inventaire.Objet;

public class A_AddCogniton extends Action{
	
	NCogniton cogniton;

	
	@Override
	public Action effectuer(Humain h) {
		
		h.getEsprit().addCogniton(cogniton);
		return nextAction;
		
	}

	@Override
	public ImageIcon getIcon(){
		return new ImageIcon(this.getClass().getResource("../../../inspecteur/icones/processor.png"));
	}
	
	@Override
	public int getNumberActionSlot(){
		return 2;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Add a new cogniton to the agent.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(NCogniton.class)){
			cogniton = (NCogniton) option.getParametres().get(0);
		}
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			
			
			String[] cog = {"**Cogniton**" , "Cogniton"};
			
			schemaParametres.add(cog);


		}
		return schemaParametres;	
	}
	
}
