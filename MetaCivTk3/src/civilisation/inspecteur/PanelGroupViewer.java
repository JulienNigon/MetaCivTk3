package civilisation.inspecteur;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import turtlekit.kernel.Turtle;
import civilisation.group.Group;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;


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

	public PanelGroupViewer()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Groups"};
        groupTable = new JTable(groups, entetes);
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
			groups[i][0] = keys[i].getGroupModel().getName() + " ID:"+keys[i].hashCode();
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
}
	


