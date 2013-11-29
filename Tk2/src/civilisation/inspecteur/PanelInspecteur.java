package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.*;



import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.world.World;

/** 
 * Le JPanel contenant toute l'information affichée par l'inspecteur : permet d'obtenir des informations sur les agents
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public class PanelInspecteur extends JPanel{

	JToolBar toolBar = new JToolBar();
	JButton agentSuivant;
	JButton agentPrecedent;
	PanelPatch patch = new PanelPatch();
	PanelInventaire inventaire = new PanelInventaire();
	PanelListeCognitons croyances = new PanelListeCognitons();
	PanelListePlans plans = new PanelListePlans();
	PanelGenealogie genealogie = new PanelGenealogie();

	int agentID = 0;
	JTable tableau;
	Object[][] donnees = new Object[100][2];
	
	
	public PanelInspecteur()
	{
		this.setLayout(new BorderLayout());
		

		this.add(toolBar, BorderLayout.NORTH);
		this.add(patch, BorderLayout.SOUTH);
		this.add(inventaire, BorderLayout.EAST);
		
		JPanel cognitonsEtPlans = new JPanel();
		FlowLayout layoutCognitonsEtPlans = new FlowLayout();
		cognitonsEtPlans.setLayout(layoutCognitonsEtPlans);
		cognitonsEtPlans.add(croyances);
		cognitonsEtPlans.add(plans);
		this.add(cognitonsEtPlans, BorderLayout.WEST);


		
		ImageIcon Suivant = new ImageIcon(this.getClass().getResource("icones/arrow-000-medium.png"));
		ImageIcon Precedent = new ImageIcon(this.getClass().getResource("icones/arrow-180-medium.png"));

		
		agentSuivant = new JButton(Suivant);
		agentPrecedent = new JButton(Precedent);
		agentSuivant.addActionListener(new ActionInspecteurListener(this, 0));
		agentPrecedent.addActionListener(new ActionInspecteurListener(this, 1));
		toolBar.add(agentPrecedent);
		toolBar.add(agentSuivant);

        String[] entetes = {"Variable", "Valeur"};
 
        tableau = new JTable(donnees, entetes);
        //tableau.setDefaultRenderer(Object.class, new CustomRenderer());

        Box boite = new Box(BoxLayout.PAGE_AXIS);
        boite.add(Box.createHorizontalStrut(600));
        boite.add(genealogie);
        boite.add(tableau);
		this.add(boite, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	
	
	public void incrementerAgentID()
	{
		if ((World.getInstance().getTurtleWithID(agentID) != null && World.getInstance().getTurtleWithID(agentID).getClass() == Humain.class))
		{
			((Humain) World.getInstance().getTurtleWithID(agentID)).setIsSelected(false);
		}
		
		agentID++;
		int iteration = 0;
		while ((World.getInstance().getTurtleWithID(agentID) == null || World.getInstance().getTurtleWithID(agentID).getClass() != Humain.class) && iteration <= 50000)
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
		if ((World.getInstance().getTurtleWithID(agentID) != null && World.getInstance().getTurtleWithID(agentID).getClass() == Humain.class))
		{
			((Humain) World.getInstance().getTurtleWithID(agentID)).setIsSelected(false);
		}
		
		agentID--;
		int iteration = 0;
		while ((World.getInstance().getTurtleWithID(agentID) == null || World.getInstance().getTurtleWithID(agentID).getClass() != Humain.class) && iteration <= 50000)
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
			((Humain) World.getInstance().getTurtleWithID(agentID)).setIsSelected(false);
			actualiser();
		}
	}
	
	public void actualiser()
	{
		Humain h = (Humain) World.getInstance().getTurtleWithID(agentID);
		h.setIsSelected(true);
		
		
		donnees[0][0] = "ID";
		donnees[0][1] = agentID;
		donnees[1][0] = "X";
		donnees[1][1] = h.x;
		donnees[2][0] = "Y";
		donnees[2][1] = h.y;
		donnees[3][0] = "Vie";
		donnees[3][1] = h.getVie();
		donnees[4][0] = "n° Civilisation";
		donnees[4][1] = h.getCiv().getIndexCiv();
		donnees[5][0] = "Couleur Civ";
		donnees[5][1] = h.getCiv().getCouleur();
		donnees[6][0] = "Femme?";
		donnees[6][1] = h.getFemme();
		donnees[7][0] = "Projet";
		donnees[7][1] = h.getEsprit().getPlanEnCours().getPlan().getNom();
		donnees[8][0] = "Total plan weight";
		donnees[8][1] = h.getEsprit().getPoidsTotalPlan();
		int var = 9;
		
		for (int i = var ; i < Configuration.attributesNames.size() + var ; i++) {
			donnees[i][0] = Configuration.attributesNames.get(i - var);
			donnees[i][1] = h.getAttr().get(Configuration.attributesNames.get(i - var));
		}
		
		patch.actualiser(World.getInstance().getTurtleWithID(agentID));  //On met a jour le panel du patch
		inventaire.actualiser(World.getInstance().getTurtleWithID(agentID)); //On met à jour l'affichage de l'inventaire
		croyances.actualiser(World.getInstance().getTurtleWithID(agentID)); //On met à jour l'affichage des croyances
		plans.actualiser(World.getInstance().getTurtleWithID(agentID)); //On met à jour l'affichage des plans
		genealogie.actualiser((Humain)World.getInstance().getTurtleWithID(agentID)); //On met à jour l'affichage de la genealogie

		this.updateUI();
		
	//	System.out.println(( World.getInstance().getTurtleWithID(agentID).toString()));
	}

	


	
}




