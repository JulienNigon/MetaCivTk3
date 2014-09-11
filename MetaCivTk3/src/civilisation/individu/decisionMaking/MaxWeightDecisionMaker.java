package civilisation.individu.decisionMaking;

import civilisation.individu.Esprit;
import civilisation.individu.plan.NPlanPondere;

/**
 * This DecisionMaker always select the plan with the max weight.
 */

public class MaxWeightDecisionMaker extends DecisionMaker{

	public MaxWeightDecisionMaker(Esprit mind) {
		super(mind);
	}

	@Override
	public void selectPlan() {
		
		/* Select the new plan if there are no action to do */
		if ((mind.getActionEnCours() == null))
		{
			float max = Integer.MIN_VALUE;
			NPlanPondere plan = null;
			for (int i = 0 ; i < mind.getPlans().size(); i++) {
				float weight = mind.getPlans().get(i).getPoids();
				if (weight > max) {
					max = weight;
					plan = mind.getPlans().get(i);
				}
			}

			//System.out.println(plans.get(i).getPoids() + plans.get(i).getPlan().getNom());
			mind.setPlanEnCours(plan);
			mind.getActions().push(null); //end of plan marker
		}
		
	}

	@Override
	public String getDescription() {
		return "A decision maker which select the max weighted plan.";
	}

	@Override
	public DecisionMaker createNewDecisionMaker(Esprit mind) {
		return new MaxWeightDecisionMaker(mind);
	}

}
