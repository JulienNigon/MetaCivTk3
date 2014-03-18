package civilisation.inspecteur.simulation.groupManager;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.group.GroupModel;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.simulation.PanelStructureCognitive;

public class PanelGroupManager extends PanelStructureCognitive{
	
	JPanel panelParent;
	GroupToolBar toolBar;
	GroupModel groupModel;
	
	public PanelGroupManager (PanelModificationSimulation panelParent){
		super(panelParent);
		this.panelParent = panelParent;

		revalidate();

	}
	
	protected void initializeItemsToDraw() {

		allCognitons = Configuration.cognitons;
		allCognitons.addAll(Configuration.cloudCognitons);
		plans = Configuration.plans;
		
	}

	public void setToolBar(GroupToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public void changeSelection(GroupModel gm) {
		toolBar.changeSelection(gm);
		
	}
	
	
}
