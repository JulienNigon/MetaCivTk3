package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.*;



import civilisation.Communaute;
import civilisation.individu.Humain;
import civilisation.world.World;

/** 
 * Le JPanel contenant toute l'information affichée par l'inspecteur : permet d'obtenir des informations sur les communautés
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public class PanelInspecteurCommunaute extends JPanel{

	JToolBar toolBar = new JToolBar();
	JButton agentSuivant;
	JButton agentPrecedent;
	PanelListeBatiments batiments = new PanelListeBatiments();
	int agentID = 0;
	JTable tableau;
	Object[][] donnees = new Object[100][2];
	
	
	public PanelInspecteurCommunaute()
	{
		this.setLayout(new BorderLayout());
		

		this.add(toolBar, BorderLayout.NORTH);
		this.add(batiments, BorderLayout.EAST);

		ImageIcon Suivant = new ImageIcon(this.getClass().getResource("icones/arrow-000-medium.png"));
		ImageIcon Precedent = new ImageIcon(this.getClass().getResource("icones/arrow-180-medium.png"));

		agentSuivant = new JButton(Suivant);
		agentPrecedent = new JButton(Precedent);
		
		agentSuivant.addActionListener(new ActionInspecteurCommunauteListener(this,0));
		agentPrecedent.addActionListener(new ActionInspecteurCommunauteListener(this,1));

		toolBar.add(agentPrecedent);
		toolBar.add(agentSuivant);

        String[] entetes = {"Variable", "Valeur"};
 
        tableau = new JTable(donnees, entetes);
        //tableau.setDefaultRenderer(Object.class, new CustomRenderer());
		this.add(tableau, BorderLayout.CENTER);


		this.setVisible(true);
	}
	
	
	
	public void incrementerAgentID()
	{
		agentID++;
		int iteration = 0;
		while ((World.getInstance().getTurtleWithID(agentID) == null || World.getInstance().getTurtleWithID(agentID).getClass() != Communaute.class) && iteration <= 50000)
		{
			agentID++;
			iteration++;
			if (agentID >= 50000)
			{
				agentID = 0;
			}
		}
		if (iteration <= 50000)
		{
			actualiser();
		}

	}
	
	public void decrementerAgentID()
	{
		agentID--;
		int iteration = 0;
		while ((World.getInstance().getTurtleWithID(agentID) == null || World.getInstance().getTurtleWithID(agentID).getClass() != Communaute.class) && iteration <= 50000)
		{
			agentID--;
			iteration++;
			if (agentID <= 0)
			{
				agentID = 49999;
			}
		}
		if (iteration <= 50000)
		{
			actualiser();
		}
	}
	
	public void actualiser()
	{
	
		donnees[0][0] = "N° Communauté";
		donnees[0][1] = ((Communaute) World.getInstance().getTurtleWithID(agentID)).getIndex();



		
		batiments.actualiser(World.getInstance().getTurtleWithID(agentID)); //On met à jour l'affichage de l'inventaire
		
		this.updateUI();
		
	}

	


	
}




/**
 * 
 * En construction !
 * (pour afficher la couleur de la civilisation)
 */

/*
class CustomRenderer extends DefaultTableCellRenderer 
{
	private static final long serialVersionUID = 1L;
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

       
		setText(value.toString());
        if (row == 5 && column == 1)
        {
        	Color color = (Color) value;
        	setBackground(color);
        }
        
        return this;
    }

}
*/



