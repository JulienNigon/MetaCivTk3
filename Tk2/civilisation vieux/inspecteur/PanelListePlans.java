package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.*;

import civilisation.individu.Humain;
import civilisation.world.World;
import civilisation.Communaute;
import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Un sous panel pour représenter les croyances d'un agent
 * @author DTEAM
 * @version 1.0 - 3/2013
*/

public class PanelListePlans extends JPanel{

	JLabel titre = new JLabel("Plans");
	JTable tableau;
	Object[][] donnees = new Object[1000][2];
	JTableRendererPlans renderer;

	public PanelListePlans()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Plans" , "poids"};
        tableau = new JTable(donnees, entetes);
        renderer = new JTableRendererPlans();
        tableau.setDefaultRenderer(Object.class, renderer);

        
		this.add(tableau, BorderLayout.CENTER);

		
		this.setVisible(true);
	}


	
	public void actualiser(Turtle t)
	{
		for (int i=0; i < donnees.length;i++)
		{
			donnees[i][0] = null;
			donnees[i][0] = null;
		}
		for (int i = 0; i < ((Humain) t).getEsprit().getPlans().size();i++)
		{
			donnees[i][0] = ((Humain) t).getEsprit().getPlans().get(i);
			donnees[i][1] = ((Humain) t).getEsprit().getPlans().get(i);
		}
		this.updateUI();
		
	}
}
	


