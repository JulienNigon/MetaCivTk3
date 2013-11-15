package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_Mourrir extends Action{

	@Override
	public Action effectuer(Humain h) {
		//System.out.println("mourrir?");
		//h.setVie(0);
		return nextAction;
	}

	
	
	
}
