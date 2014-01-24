package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_Die extends Action{

	@Override
	public Action effectuer(Humain h) {
		h.die();
		//TODO : must stop all actions for this agent.
		return nextAction;
	}

	
	@Override
	public String getInfo() {
		return super.getInfo() + " Kill the agent.<html>";
	}

	
	
}
