package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.*;

import civilisation.individu.Humain;
import civilisation.world.World;
import civilisation.Communaute;
import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Un sous panel pour représenter la liste des batiments d'une communaute
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class PanelListeBatiments extends JPanel{

	JLabel titre = new JLabel("Batiments");
	JTable tableau;
	Object[][] donnees = new Object[1000][2];

	public PanelListeBatiments()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Batiments" , "Inventaire"};
        tableau = new JTable(donnees, entetes);
		this.add(tableau, BorderLayout.CENTER);

		
		this.setVisible(true);
	}


	
	public void actualiser(Turtle t)
	{
		for (int i=0; i < donnees.length;i++)
		{
			donnees[i][0] = null;
			donnees[i][1] = null;
		}
		for (int i=0; i < ((Communaute) t).getBatiments().size();i++)
		{
			donnees[i][0] = ((Communaute) t).getBatiments().get(i).toString();
			donnees[i][1] = ((Communaute) t).getBatiments().get(i).getInventaire().getSize();
		}

		this.updateUI();
		
	}



	
}
