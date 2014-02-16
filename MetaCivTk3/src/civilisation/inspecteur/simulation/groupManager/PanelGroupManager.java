package civilisation.inspecteur.simulation.groupManager;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import civilisation.inspecteur.simulation.PanelModificationSimulation;

public class PanelGroupManager extends JPanel{
	
	JPanel panelParent;
	
	public PanelGroupManager (PanelModificationSimulation panelParent){
		super();
		this.panelParent = panelParent;

		revalidate();

	}
}
