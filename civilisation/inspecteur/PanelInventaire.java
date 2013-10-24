package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

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
import civilisation.inventaire.ObjetInventaire;
import civilisation.world.World;
import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Un sous panel pour représenter les informations sur l'inventaire de l'agent selectionne
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class PanelInventaire extends JPanel{

	JLabel titre = new JLabel("Inventaire");
	JTable tableau;
	Object[][] donnees = new Object[100][1];
	JTableRendererInventaire renderer;

	public PanelInventaire()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Objet"};
        tableau = new JTable(donnees, entetes);
		this.add(tableau, BorderLayout.CENTER);
        renderer = new JTableRendererInventaire();
        tableau.setDefaultRenderer(Object.class, renderer);
		
		this.setVisible(true);
	}


	
	public void actualiser(Turtle t)
	{
		for (int i=0; i < donnees.length;i++)
		{
			donnees[i][0] = null;
		}
		
		ArrayList<ObjetInventaire> allObjets = ((Humain) t).getInventaire().getAll();
		for (int i=0; i < ((Humain) t).getInventaire().getSize();i++)
		{
			donnees[i][0] = allObjets.get(i);
		}

		this.updateUI();
		
	}



	
}
