package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;
import civilisation.individu.plan.Plan;

public abstract class Percept extends Cogniton{

	public Percept(Esprit e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
	
	public String getType()
	{
		return "Percept";
	}

	public Color getColor()
	{
		return new Color(76,166,107);
	}
	
	public Plan[] getTabNouveauxPlans(Esprit e) 
	{
		Plan[] nouveauxProjets = {};
		return nouveauxProjets;
	};
}
