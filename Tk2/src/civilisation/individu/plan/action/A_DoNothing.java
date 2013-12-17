package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_DoNothing extends Action{

	@Override
	public Action effectuer(Humain h) {
		System.out.println("do nothing");
		return nextAction;
	}

	
	@Override
	public String getInfo() {
		return super.getInfo() + " Do nothing.<html>";
	}

	
	
}
