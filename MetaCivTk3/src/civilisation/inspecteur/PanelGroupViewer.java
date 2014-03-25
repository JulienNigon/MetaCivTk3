package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import turtlekit.kernel.Turtle;
import civilisation.Configuration;
import civilisation.group.Group;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inspecteur.advanced.MousePanelGroupListener;
import civilisation.inspecteur.simulation.environnement.ActionsMenuTerrain;
import civilisation.world.WorldViewer;


/**
 * 
 * @author J.Nigon
 *
 */

public class PanelGroupViewer extends JPanel{

	JLabel titre = new JLabel("Groups");
	
	JTable groupTable;
	Object[][] groups = new Object[10][1];  //TODO resizable
	
	JTableRendererCognitons renderer;

	JTable roleTable;
	Object[][] roles = new Object[10][1];
	
	JPopupMenu popup;

	public PanelGroupViewer()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Groups"};
        groupTable = new JTable(groups, entetes);
        groupTable.addMouseListener(new MousePanelGroupListener(this,0));
        //renderer = new JTableRendererCognitons();
        //groupTable.setDefaultRenderer(Object.class, renderer);

        roleTable = new JTable(roles, entetes);
        
		this.add(groupTable, BorderLayout.CENTER);
		this.add(roleTable, BorderLayout.EAST);

		
		this.setVisible(true);
	}


	
	public void actualiser(Turtle t)
	{
		Group[] grps = new Group[0];
		Group[] keys = ((Humain) t).getEsprit().getGroups().keySet().toArray(grps);
		//System.out.println(keys.length + " " + ((Humain) t).getEsprit().getGroups().keySet().size());
		Esprit e = ((Humain) t).getEsprit();
		
		for (int i=0; i < groups.length;i++)
		{
			groups[i][0] = null;
			groups[i][0] = null;
		}
		for (int i = 0; i < keys.length ;i++)
		{
			groups[i][0] = keys[i]/*.getGroupModel().getName() + " ID:"+keys[i].hashCode()*/;
		}
		
		for (int i=0; i < roles.length;i++)
		{
			roles[i][0] = null;
			roles[i][0] = null;
		}
		for (int i = 0; i < keys.length; i++)
		{
			roles[i][0] = e.getGroups().get(keys[i]);
		}

		this.updateUI();
		
	}



	public JTable getGroupTable() {
		return groupTable;
	}



	public void setGroupTable(JTable groupTable) {
		this.groupTable = groupTable;
	}



	public JTable getRoleTable() {
		return roleTable;
	}



	public void setRoleTable(JTable roleTable) {
		this.roleTable = roleTable;
	}
	
	public void afficherPopup(MouseEvent e) {
		
		popup = new JPopupMenu("Groups");
		
		JMenuItem showInWorldViewer = new JMenuItem("Show in world view");
		showInWorldViewer.addActionListener(new MousePanelGroupListener(this,1));
		showInWorldViewer.setIcon(Configuration.getIcon("eye.png"));
		popup.add(showInWorldViewer);
		
		popup.show(this, e.getX(), e.getY());		
	}



	public void changeObservedGroup() {
		WorldViewer.getInstance().setGroupToObserve((Group) groupTable.getValueAt(groupTable.getSelectedRow(), 0));
		
	}
}
	


