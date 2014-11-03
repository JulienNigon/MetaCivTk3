package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_AvanceAleatoirement extends Action{

	@Override
	public Action effectuer(Humain h) {
		h.setHeading(Math.random()*360.);
		h.fd(1);
		
		return nextAction;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + " The agent move in a random direction.<html>";
	}
	
	
}
