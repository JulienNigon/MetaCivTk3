package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class L_Repeat extends LAction{
	
	Integer n;
	
	@Override
	public Action effectuer(Humain h) {
		
		for (int i = 0 ; i < n-1 ; i++) {
			h.getEsprit().getActions().push(listeActions.get(0));
		}
		return listeActions.get(0).effectuer(h);
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
