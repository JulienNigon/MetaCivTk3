package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_Die extends Action{

	@Override
	public Action effectuer(Humain h) {
		System.out.println("mourrir?");
		h.die();
		return nextAction;
	}

	
	@Override
	public String getInfo() {
		return super.getInfo() + " Kill the agent.<html>";
	}

	
	
}
