package civilisation.individu.decisionMaking;

import civilisation.individu.Esprit;

/**
 * The standard decision maker of MetaCiv, which select plan randomly in respect with their weigth.
 */
public class WeightedStochasticDecisionMaker extends DecisionMaker{

	public WeightedStochasticDecisionMaker(Esprit mind) {
		super(mind);
	}

	@Override
	public void selectPlan() {
		
		/* Select the new plan if there are no action to do */
		if ((/*planEnCours == null && */mind.getActionEnCours() == null))
		{
			mind.computeTotalWeight(); //TODO : remove and re-write dynamic evolution of total weight
			int alea = (int) (Math.random()*(mind.getPoidsTotalPlan()));
			int i = 0;
			while (alea >= mind.getPlans().get(i).getPoids() /*|| plans.get(i).getType() == 1*/)
			{
				if (mind.getPlans().get(i).getPoids() > 0) {alea -= mind.getPlans().get(i).getPoids();	} /*les poids negatifs ne sont pas pris en compte*/		
				i++;
			}
			//System.out.println(plans.get(i).getPoids() + plans.get(i).getPlan().getNom());
			mind.setPlanEnCours(mind.getPlans().get(i));
			mind.getActions().push(null); //end of plan marker
		}
		
	}

	@Override
	public String getDescription() {
		return "The standard decision maker of MetaCiv, which select plan randomly in respect with their weigth.";
	}

	@Override
	public DecisionMaker createNewDecisionMaker(Esprit mind) {
		return new WeightedStochasticDecisionMaker(mind);
	}

}
