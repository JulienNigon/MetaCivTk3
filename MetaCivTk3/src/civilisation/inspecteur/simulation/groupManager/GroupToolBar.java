package civilisation.inspecteur.simulation.groupManager;

import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import civilisation.Configuration;
import civilisation.group.GroupModel;
import civilisation.inspecteur.simulation.ActionStructureCognitive;

public class GroupToolBar extends JToolBar{

	PanelGroupManager panelGroupManager; //the associated panel for viewing group and role
	JComboBox comboRole;
	JButton buttonNewCulturon;
	JButton buttonAddExistingCulturon;
	JButton buttonRemoveRole;
	JButton buttonAddRole;

	public GroupToolBar(PanelGroupManager panelGroupManager) {
		super();
		
		buttonNewCulturon = new JButton(Configuration.getIcon("weather-cloud.png"));
		buttonNewCulturon.addActionListener(new ActionsToolBarGroupManager(this,1));
		buttonNewCulturon.setToolTipText("Add an existing culturon");
		this.add(buttonNewCulturon);
		
		buttonAddExistingCulturon = new JButton(Configuration.getIcon("weather-cloud.png"));
		buttonAddExistingCulturon.addActionListener(new ActionsToolBarGroupManager(this,2));
		buttonAddExistingCulturon.setToolTipText("Create a new culturon");
		this.add(buttonAddExistingCulturon);
		
		buttonRemoveRole = new JButton(Configuration.getIcon("weather-cloud.png"));
		buttonRemoveRole.addActionListener(new ActionsToolBarGroupManager(this,3));
		buttonRemoveRole.setToolTipText("Remove currently selected role (can't remove the last role)");
		this.add(buttonRemoveRole);
		
		buttonAddRole = new JButton(Configuration.getIcon("weather-cloud.png"));
		buttonAddRole.addActionListener(new ActionsToolBarGroupManager(this,4));
		buttonAddRole.setToolTipText("Add a new role to this group");
		this.add(buttonAddRole);
		
		comboRole = new JComboBox();
		this.add(comboRole);
		comboRole.addActionListener(new ActionsToolBarGroupManager(this,0));
		this.panelGroupManager = panelGroupManager;
		

		
	}
	
	public void groupSelectionChanged() {
		panelGroupManager.changeSelection();
	}

	public void changeSelection(GroupModel gm) {
		comboRole.removeActionListener(comboRole.getActionListeners()[0]);
		comboRole.removeAllItems();
		Set<String> roles = gm.getCulturons().keySet();
		Object[] rolesArray = roles.toArray();
		for (int i = 0 ; i < roles.size() ; i++) {
			comboRole.addItem(rolesArray[i]);
		}
		comboRole.addActionListener(new ActionsToolBarGroupManager(this,0));
	}

	public String getSelectedRole() {
		return (String) comboRole.getSelectedItem();
	}
	
}
