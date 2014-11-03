package civilisation.inspecteur.simulation.groupManager;

import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import civilisation.Configuration;
import civilisation.group.GroupModel;
import civilisation.inspecteur.simulation.ActionStructureCognitive;

public class GroupTreeToolBar extends JToolBar{

	PanelGroupTree panelGroupTree;
	JButton buttonCreateGroup;


	public GroupTreeToolBar(PanelGroupTree panelGroupTree) {
		super();
		
		buttonCreateGroup = new JButton(Configuration.getIcon("weather-cloud.png"));
		buttonCreateGroup.addActionListener(new ActionsToolBarGroupTreeManager(this,0));
		buttonCreateGroup.setToolTipText("Create a new group");
		this.add(buttonCreateGroup);

		this.panelGroupTree = panelGroupTree;
		

		
	}


	public void addNewGroup() {
		panelGroupTree.addNewGroup();
		
	}



	
}
