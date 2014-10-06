package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inventaire.Objet;

public class A_AddCogniton extends Action{
	
	TypeCogniton cogniton;

	
	@Override
	public Action effectuer(Humain h) {
		
		h.getEsprit().addCogniton(cogniton);
		return nextAction;
		
	}

	@Override
	public ImageIcon getIcon(){
		return Configuration.getIcon("processor.png");
	}
	
	@Override
	public int getNumberActionSlot(){
		return 0;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Add a new cogniton to the agent.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(TypeCogniton.class)){
			cogniton = (TypeCogniton) option.getParametres().get(0);
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
