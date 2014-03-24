package civilisation.inspecteur.simulation.groupManager;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.group.GroupModel;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.simulation.PanelStructureCognitive;

public class PanelGroupManager extends PanelStructureCognitive{
	
	JPanel panelParent;
	GroupToolBar toolBar;
	GroupModel groupModel;
	
	public PanelGroupManager (PanelModificationSimulation panelParent, GroupModel groupModel){
		super(panelParent);
		this.panelParent = panelParent;
		this.groupModel = groupModel;
		revalidate();

	}
	
	protected void initializeItemsToDraw() {

		if (groupModel != null) {
			allCognitons = groupModel.getCulturonFromRole(toolBar.getSelectedRole());
		//	allCognitons.addAll(Configuration.cloudCognitons);
			plans = Configuration.plans;
		} else {
			super.initializeItemsToDraw();
		}

		
	}

	public void setToolBar(GroupToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public void changeSelection(GroupModel gm) {
		groupModel = gm;
		toolBar.changeSelection(gm);
		changeSelection();
	}

	public void changeSelection() {
		this.removeAll();
		initializeArray();
		initializeItemsToDraw();
		initializeDrawing();		
	}
	
	protected void initializeDrawing() {


		
		for (int i = 0; i < allCognitons.size(); i++){		
			afficherCogniton(allCognitons.get(i),80,40+espacement*i);
		}

		
		for (int i = 0; i < plans.size(); i++){	
			afficherPlan(plans.get(i), espaceCognitonsPlans,40+espacement*i);
		}
		
		creerLiensInfluence();
		creerLiensConditionnels();
		createTriggerLink();
	}
	
	public NCogniton creerCogniton() {
		
		NCogniton newCulturon = super.creerCogniton();
		newCulturon.setType(TypeDeCogniton.CULTURON);
		groupModel.addCulturonToRole(toolBar.getSelectedRole(), newCulturon);
		return newCulturon;
	}
	
}
