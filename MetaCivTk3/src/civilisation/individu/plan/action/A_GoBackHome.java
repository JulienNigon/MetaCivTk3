package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.individu.Humain;

public class A_GoBackHome extends Action{

	@Override
	public Action effectuer(Humain h) {
		
		if (h.getPatch().x == h.getCommunaute().getPatch().x && h.getPatch().y == h.getCommunaute().getPatch().y) {		
			if (h.getEsprit().getActionData(this) != null)
			{
				h.getEsprit().setActionData(this, (Integer)h.getEsprit().getActionData(this) + 1);
			} else {
				h.getEsprit().setActionData(this, 1);
			}
			
			return nextAction;
		} else {
			h.allerVers(h.getCommunaute());
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
