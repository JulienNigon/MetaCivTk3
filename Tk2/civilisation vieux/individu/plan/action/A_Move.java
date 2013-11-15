package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_Move extends Action{

	double angle;
	
	@Override
	public Action effectuer(Humain h) {
		h.setHeading(angle);
		h.fd(1);
		
		return nextAction;
	}

	protected void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		switch (option) {
        case SOUTH:  angle = 270.;
                 break;
        case WEST:  angle = 180.;
                 break;
        case NORTH:  angle = 90.;
                 break;
        case EAST:  angle = 0.;

        default: 
                 break;
    }
	}
	

	
}
