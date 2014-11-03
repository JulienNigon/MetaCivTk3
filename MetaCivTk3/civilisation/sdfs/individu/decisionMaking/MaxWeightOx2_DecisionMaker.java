package civilisation.individu.decisionMaking;

import civilisation.individu.Esprit;
import civilisation.individu.plan.NPlanPondere;

/**
 * A decision maker which select the max weighted plan, but override the played plan if a plan is at least two time more weighted.
 *
 */
public class MaxWeightOx2_DecisionMaker extends DecisionMaker{

	public MaxWeightOx2_DecisionMaker(Esprit mind) {
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
		} else {
			float max = Integer.MIN_VALUE;
			NPlanPondere plan = null;
			NPlanPondere currentPlan = mind.getPlanEnCours();
			for (int i = 0 ; i < mind.getPlans().size(); i++) {
				float weight = mind.getPlans().get(i).getPoids();
				if (weight > max && weight > 2*currentPlan.getPoids()) {
					max = weight;
					plan = mind.getPlans().get(i);
				}
			}
			if (plan != null) {
				mind.setPlanEnCours(plan);
				mind.getActions().clear();
				mind.getActions().push(null); //end of plan marker
			}
		}
		
	}

	@Override
	public String getDescription() {
		return "A decision maker which select the max weighted plan, but override the played plan if a plan is at least two time more weighted";
	}

	@Override
	public DecisionMaker createNewDecisionMaker(Esprit mind) {
		return new MaxWeightOx2_DecisionMaker(mind);
	}

}
