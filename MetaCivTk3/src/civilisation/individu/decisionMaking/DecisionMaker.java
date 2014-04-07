package civilisation.individu.decisionMaking;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;

public abstract class DecisionMaker implements Cloneable {

	Esprit mind;
	
	public DecisionMaker (Esprit mind) {
		this.mind = mind;
	}
	
	public Esprit getMind() {
		return mind;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public void setMind(Esprit mind) {
		this.mind = mind;
	}

	public DecisionMaker clone() {
		DecisionMaker o = null;
		try {
			o = (DecisionMaker) super.clone();
		} catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}
		return o;
	}

	public abstract void selectPlan();
	public abstract String getDescription();
	public abstract DecisionMaker createNewDecisionMaker(Esprit mind);

}
