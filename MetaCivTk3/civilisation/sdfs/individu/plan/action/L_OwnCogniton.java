package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inventaire.Objet;

public class L_OwnCogniton extends LAction{
	
	TypeCogniton cogniton;

	
	@Override
	public Action effectuer(Humain h) {
		
		if (nextAction != null) h.getEsprit().getActions().push(nextAction);
		Action a;
		if (h.getEsprit().ownCogniton(cogniton)) {
			a = listeActions.get(0).effectuer(h);
		} else {
			a = listeActions.get(1).effectuer(h);
		}
		return a;
		
	}

	@Override
	public ImageIcon getIcon(){
		return Configuration.getIcon("processor.png");
	}
	
	@Override
	public int getNumberActionSlot(){
		return 2;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Play first action if the agent own a specific cogniton,<br> the second otherwise.<html>";
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
	
	public boolean internActionsAreLinked() {
		return false;
	}
}
