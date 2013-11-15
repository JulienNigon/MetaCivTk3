package civilisation.individu.plan.action;


public enum OptionsActions {

    WEST, EAST, NORTH, SOUTH;
    
    
    public static OptionsActions toOption(String s){
		if (s.equals("WEST")){
			return OptionsActions.WEST;
		}
		else if (s.equals("EAST")){
			return OptionsActions.EAST;
		}
		else if (s.equals("NORTH")){
			return OptionsActions.NORTH;
		}
		else if (s.equals("SOUTH")){
			return OptionsActions.SOUTH;
		}
		else
			return null;
		}
}
