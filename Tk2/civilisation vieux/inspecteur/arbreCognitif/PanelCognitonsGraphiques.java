package civilisation.inspecteur.arbreCognitif;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

import civilisation.individu.Humain;
import civilisation.inspecteur.ActionInspecteurListener;
import civilisation.inspecteur.PanelGenealogie;
import civilisation.inspecteur.PanelInventaire;
import civilisation.inspecteur.PanelListeCognitons;
import civilisation.inspecteur.PanelListePlans;
import civilisation.inspecteur.PanelPatch;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.world.World;

public class PanelCognitonsGraphiques extends JPanel{

	JToolBar toolBar = new JToolBar();
	JButton agentSuivant;
	JButton agentPrecedent;
	PanelArbreCognitif panelArbre = new PanelArbreCognitif();

	int agentID = 0;
	
	
	
	public PanelCognitonsGraphiques()
	{
		super();
		
		this.setLayout(new BorderLayout());
		

		this.add(toolBar, BorderLayout.NORTH);
		this.add(panelArbre, BorderLayout.CENTER);

		ImageIcon Suivant = new ImageIcon(this.getClass().getResource("../icones/arrow-000-medium.png"));
		ImageIcon Precedent = new ImageIcon(this.getClass().getResource("../icones/arrow-180-medium.png"));

		
		agentSuivant = new JButton(Suivant);
		agentPrecedent = new JButton(Precedent);
		agentSuivant.addActionListener(new ActionPanelCognitonsGraphiques(this, 0));
		agentPrecedent.addActionListener(new ActionPanelCognitonsGraphiques(this, 1));
		toolBar.add(agentPrecedent);
		toolBar.add(agentSuivant);


		
	}
	
	public void actualiser(){
		System.out.println("Actualisation");
		this.remove(panelArbre);
		panelArbre = new PanelArbreCognitif((Humain) World.getInstance().getTurtleWithID(agentID));
		this.add(panelArbre, BorderLayout.CENTER);

	}
	
	
	public void incrementerAgentID()
	{
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
			actualiser();
		}
	}
}
