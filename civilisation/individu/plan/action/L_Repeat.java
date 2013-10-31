package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class L_Repeat extends Action{
	
	Integer n;
	
	@Override
	public Action effectuer(Humain h) {
		System.out.println("INIT");
		if (h.getEsprit().getActionData(this) == null) {
			h.getEsprit().setActionData(this, 0);
		} 
		else 
		{
			Integer iterations = (Integer) h.getEsprit().getActionData(this);
			if (iterations < n) {
				h.getEsprit().setActionData(this, (iterations + 1));
				System.out.println(iterations);
				listeActions.get(0).effectuer(h);
			} else {
				System.out.println("CLEAN");
				h.getEsprit().cleanActionData(this);
				System.out.println(nextAction);
				return nextAction;
			}
		}
		return this;
	}

	@Override
	public ImageIcon getIcon(){
		return new ImageIcon(this.getClass().getResource("../../../inspecteur/icones/processor.png"));
	}
	
	@Override
	public int getNumberActionSlot(){
		return -1;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Allow to repeat actions inside this logical controller n time.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(Integer.class)){
			n = (Integer) option.getParametres().get(0);
		}
		else
		{
			System.out.println("Mauvaise initialisation d'une action!");
		}

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] nRepeat = {"**Integer**" , "n", "0" , "10000" , "1"};
			schemaParametres.add(nRepeat);
		}
		return schemaParametres;	
	}
	
}
