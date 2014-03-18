package civilisation.inspecteur.simulation.groupManager;

import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JToolBar;

import civilisation.group.GroupModel;

public class GroupToolBar extends JToolBar{

	PanelGroupManager panelGroupManager; //the associated panel for viewing group and role
	JComboBox comboRole;
	
	public GroupToolBar(PanelGroupManager panelGroupManager) {
		super();
		comboRole = new JComboBox();
		this.add(comboRole);
		this.panelGroupManager = panelGroupManager;

		
	}

	public void changeSelection(GroupModel gm) {
		comboRole.removeAllItems();
		Set<String> roles = gm.getCulturons().keySet();
		Object[] rolesArray = roles.toArray();
		for (int i = 0 ; i < roles.size() ; i++) {
			comboRole.addItem(rolesArray[i]);
		}
		
	}
	
}
