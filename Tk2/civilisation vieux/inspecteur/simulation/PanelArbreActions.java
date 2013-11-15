package civilisation.inspecteur.simulation;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

import civilisation.Configuration;
import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.animations.JJPanel;

public class PanelArbreActions extends JJPanel{

	JTree arbreActions;
	NPlan plan;
	JToolBar toolBar = new JToolBar();


	public PanelArbreActions(NPlan plan){
		if (plan != null){
			this.setLayout(new BorderLayout());

			this.plan = plan;
			arbreActions = new JTree(new ModeleArbreActions(plan));
			arbreActions.setRootVisible(false);
			ArbreActionsRenderer renderer = new ArbreActionsRenderer();
			arbreActions.setCellRenderer(renderer);


			
			this.add(toolBar , BorderLayout.NORTH);
			this.add(arbreActions , BorderLayout.CENTER);
		}
	}
	
	
	
	
	
}
