package civilisation.individu.plan.action;

public class LAction extends Action{

	/**
	 * Logical actions are designed to manage loop, test, etc...
	 */
	
	
	public boolean isLogical() {
		return true;
	}
	
	public boolean internActionsAreLinked() {
		return false;
	}
}
