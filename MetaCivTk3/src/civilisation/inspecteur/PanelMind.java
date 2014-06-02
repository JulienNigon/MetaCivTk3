package civilisation.inspecteur;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import civilisation.Configuration;
import civilisation.group.Group;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.individu.cognitons.TypeDeCogniton;
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
	ArrayList<Cogniton> ownedCognitons;
	ArrayList<NPlanPondere> ownedPlans;
	
	ArrayList<GPlan> plansToRemove = new ArrayList<GPlan>();
	ArrayList<GCogniton> cognitonsToRemove = new ArrayList<GCogniton>();
	
	Semaphore semaphore;

	public PanelMind(Humain h) {
		super(true);
		this.setDelay(5);
		this.h = h;
		showGroup = true;
		
		semaphore = new Semaphore(1,true);
		
		initializeArray();
		initializeItemsToDraw();
		initializeDrawing();
	}

	
	protected void initializeItemsToDraw() {

		ownedCognitons = h.getEsprit().getCognitons();
		allCognitons = new ArrayList<TypeCogniton>();
		plans = new ArrayList<NPlan>();

		for (Cogniton cog : ownedCognitons) {
			allCognitons.add(cog.getCogniton());
		}
		
		//	allCognitons.addAll(Configuration.cloudCognitons);
		ownedPlans = h.getEsprit().getPlans();
		for (NPlanPondere pl : ownedPlans) {
			plans.add(pl.getPlan());
		}
		for (Group gr : h.getEsprit().getGroups().keySet()) {
			groups.add(gr);
			for (TypeCogniton cog : gr.getArrayListOfCognitonType(h.getEsprit().getGroups().get(gr))) {
				allCognitons.add(cog);
			}
		}
	}
	
	public void updateData() {
		
		try { semaphore.acquire(); }
		catch (InterruptedException ex) { System.out.println(ex); }
		
		groups = new ArrayList<Group>();
		for (Group gr : h.getEsprit().getGroups().keySet()) {
			groups.add(gr);
		}
		
		//Add cognitons
		for (Cogniton cog : h.getEsprit().getCognitons()) {
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
		for (Group gr : groups) {
			for (Cogniton cog : gr.getRolesAndCulturons().get(h.getEsprit().getGroups().get(gr))) {
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
		}
	
		//Add plans
		for (NPlanPondere pl : h.getEsprit().getPlans()) {
			if (!this.planIsDrawn(pl.getPlan())) {
				this.afficherPlan(pl.getPlan(), Math.random()*400, Math.random()*400,pl);
				
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
		for (TypeCogniton cog : Configuration.cognitons) {
			if (cog.getType() != TypeDeCogniton.CULTURON && this.cognitonIsDrawn(cog) && !h.getEsprit().ownCogniton(cog)) {
				this.removeCogniton(cog);

				this.supprimerLiensInfluence();
				this.supprimerLiensConditionnels();
				this.clearTriggerLink();
				
				creerLiensInfluence();
				creerLiensConditionnels();
				createTriggerLink();
			} else if (cog.getType() == TypeDeCogniton.CULTURON) {
				boolean exist = false;
				for (Group gr : groups) {		
					if (gr.roleContainsCulturon(cog, h.getEsprit().getGroups().get(gr))) {
						exist = true;
					}
				}
				if (!exist) {
					this.removeCogniton(cog);

					this.supprimerLiensInfluence();
					this.supprimerLiensConditionnels();
					this.clearTriggerLink();
					
					creerLiensInfluence();
					creerLiensConditionnels();
					createTriggerLink();
				}
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
		semaphore.release();
	}
	
	public void removeCogniton(TypeCogniton cognitonToRemove) {
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
	
	protected void initializeDrawing() {
	for (int i = 0; i < allCognitons.size(); i++){		
			afficherCogniton(allCognitons.get(i),80,40+espacement*i);
		}

		
		for (int i = 0; i < plans.size(); i++){	
			afficherPlan(plans.get(i), espaceCognitonsPlans,40+espacement*i,h.getEsprit().getPlan(plans.get(i)));
		}
		
		for (int i = 0; i < groups.size(); i++){	
			showGroup(groups.get(i), h.getEsprit().getGroups().get(groups.get(i)), espaceCognitonsPlans * 1.8,40+espacement*i);
		}
		
		creerLiensInfluence();
		creerLiensConditionnels();
	}
	
	public void animate() {
		try { semaphore.acquire(); }
		catch (InterruptedException ex) { System.out.println(ex); }
		super.animate();
		semaphore.release();
	}
	
	//This panel doesn't show trigger links
	public void createTriggerLink(){}
	public void clearTriggerLink(){}
	public void showTrigger(GCogniton c){}
}
