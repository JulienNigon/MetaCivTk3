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

import civilisation.Civilisation;
import civilisation.individu.Humain;
import civilisation.world.World;
import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Un sous panel pour représenter les informations sur le patch sélectionné
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class PanelPatch extends JPanel{

	JLabel nomTerrain = new JLabel("Aucun patch sélectionné");
	JTable tableau;
	Object[][] donnees = new Object[10][2];

	public PanelPatch()
	{
		this.setLayout(new BorderLayout());
		this.add(nomTerrain, BorderLayout.NORTH);
		
        String[] entetes = {"Attribut", "Valeur"};
        tableau = new JTable(donnees, entetes);
		this.add(tableau, BorderLayout.CENTER);

		
		this.setVisible(true);
	}

	public void setTerrain(String nouveauNomTerrain) {
		
		nomTerrain.setText(nouveauNomTerrain);
		
	}
	
	public void actualiser(Turtle t)
	{
		nomTerrain.setText(t.getPatchColor().toString());
		
		donnees[0][0] = "Baies";
		donnees[0][1] = t.smell("baies");
		donnees[1][0] = "Gibier";
		donnees[1][1] = t.smell("gibier");
		for (int i = 0; i < Civilisation.getNombreCiv();i++)
		{
			donnees[i+2][0] = "civ"+i;
			donnees[i+2][1] = t.smell("civ"+i);
		}
		
		
		this.updateUI();
		
	}



	
}
