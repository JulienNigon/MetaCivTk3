package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_Collecter extends Action{


	@Override
	public Action effectuer(Humain h) {
		System.out.println("collecter");
		h.fd(1);
		
		return nextAction;
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		

	}
	

	
}
