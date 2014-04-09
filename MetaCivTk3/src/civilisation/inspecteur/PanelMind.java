package civilisation.inspecteur;

import java.awt.Color;
import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.CCogniton;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.NPlanPondere;
import civilisation.inspecteur.animations.JJAnimationOpacite;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.GLien;
import civilisation.inspecteur.simulation.GPlan;
import civilisation.inspecteur.simulation.GTrigger;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.simulation.PanelStructureCognitive;

@SuppressWarnings("serial")
public class PanelMind extends PanelStructureCognitive{

	Humain h;
	ArrayList<CCogniton> ownedCognitons;
	ArrayList<NPlanPondere> ownedPlans;
	
	ArrayList<GPlan> plansToRemove = new ArrayList<GPlan>();
	ArrayList<GCogniton> cognitonsToRemove = new ArrayList<GCogniton>();

	public PanelMind(Humain h) {
		super(true);
		this.setDelay(5);
		this.h = h;
		
		initializeArray();
		initializeItemsToDraw();
		initializeDrawing();
	}

	
	protected void initializeItemsToDraw() {

		ownedCognitons = h.getEsprit().getCognitons();
		allCognitons = new ArrayList<NCogniton>();
		plans = new ArrayList<NPlan>();

		for (CCogniton cog : ownedCognitons) {
			System.out.println(cog.getCogniton().getNom());
			allCognitons.add(cog.getCogniton());
		}
		//	allCognitons.addAll(Configuration.cloudCognitons);
		ownedPlans = h.getEsprit().getPlans();
		for (NPlanPondere pl : ownedPlans) {
			plans.add(pl.getPlan());
		}
	}
	
	public void updateData() {
		//Add cognitons
		for (CCogniton cog : h.getEsprit().getCognitons()) {
			if (!this.cognitonIsDrawn(cog.getCogniton())) {
				this.afficherCogniton(cog.getCogniton(), Math.random()*400, Math.random()*400);
				
				this.gCognitons.get(gCognitons.size()-1).setOpacite(0.0);
				this.gCognitons.get(gCognitons.size()-1).addAnimation(new JJAnimationOpacite(50, gCognitons.get(gCognitons.size()-1), 0.02, false));
				
				this.supprimerLiensInfluence();
				this.supprimerLiensConditionnels();
				this.clearTriggerLink();
				
				creerLiensInfluence();
				creerLiensConditionnels();
				createTriggerLink();
			}
		}
		//Add plans
		for (NPlanPondere pl : h.getEsprit().getPlans()) {
			if (!this.planIsDrawn(pl.getPlan())) {
				this.afficherPlan(pl.getPlan(), Math.random()*400, Math.random()*400);
				
				this.gPlan.get(gPlan.size()-1).setOpacite(0.0);
				this.gPlan.get(gPlan.size()-1).addAnimation(new JJAnimationOpacite(50, gPlan.get(gPlan.size()-1), 0.02, false));

				this.supprimerLiensInfluence();
				this.supprimerLiensConditionnels();
				this.clearTriggerLink();
				
				creerLiensInfluence();
				creerLiensConditionnels();
				createTriggerLink();
			}
		}
		//Remove cognitons
		for (NCogniton cog : Configuration.cognitons) {
			if (this.cognitonIsDrawn(cog) && !h.getEsprit().ownCogniton(cog)) {
				this.removeCogniton(cog);

				this.supprimerLiensInfluence();
				this.supprimerLiensConditionnels();
				this.clearTriggerLink();
				
				creerLiensInfluence();
				creerLiensConditionnels();
				createTriggerLink();
			}
		}
		//Remove plans
		for (NPlan pl : Configuration.plans) {
			if (this.planIsDrawn(pl) && !h.getEsprit().ownPlan(pl)) {
				this.removePlan(pl);
	
				this.supprimerLiensInfluence();
				this.supprimerLiensConditionnels();
				this.clearTriggerLink();
				
				creerLiensInfluence();
				creerLiensConditionnels();
				createTriggerLink();
			}
		}
	}
	
	public void removeCogniton(NCogniton cognitonToRemove) {
		for (GCogniton cog : gCognitons) {
			if (cognitonToRemove == cog.getCogniton()) {
				this.remove(cog);
				gCognitons.remove(cog);
				this.add(cog);
				cog.addAnimation(new JJAnimationOpacite(50, cog, -0.02, true));
				break;
			}
		}
	}
	
	public void removePlan(NPlan planToRemove) {
		for (GPlan pl : gPlan) {
			if (planToRemove == pl.getPlan()) {
				this.remove(pl);
				gPlan.remove(pl);
				this.add(pl);
				pl.addAnimation(new JJAnimationOpacite(50, pl, -0.02, true));
				break;
			}
		}
	}

	
	//This panel doesn't show trigger links
	public void createTriggerLink(){}
	public void clearTriggerLink(){}
	public void showTrigger(GCogniton c){}
}
