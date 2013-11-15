package civilisation.inspecteur.tableauDeBord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.animations.JJStatDiagramme;
import civilisation.inspecteur.simulation.ActionPanelCognitonsGraphiques;
import civilisation.inspecteur.simulation.PanelArbreActions;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.simulation.PanelStructureCognitive;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;
import civilisation.inspecteur.simulation.environnement.PanelTerrains;
import civilisation.inspecteur.simulation.objets.PanelListeObjets;
import civilisation.inspecteur.simulation.objets.PanelObjets;
import civilisation.world.World;

public class PanelInfos extends JJPanel{
	
	PanelRatioCognitons ratioCognitons;
	JToolBar toolBar;
	JButton sonder;
	JLabel dernierSondage;

	public PanelInfos()
	{
		super();
		this.setLayout(new BorderLayout());

		ratioCognitons = new PanelRatioCognitons();
		ratioCognitons.setSize(300, 800);
		ratioCognitons.setMaximumSize(new Dimension(300,800));
		this.add(ratioCognitons , BorderLayout.CENTER);
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Ratios de cognitons");
		bordure.setTitleJustification(TitledBorder.LEFT);
		ratioCognitons.setBorder(bordure);
		
		toolBar = new JToolBar();
		this.add(toolBar , BorderLayout.NORTH);
		
		ImageIcon icone = new ImageIcon(this.getClass().getResource("../icones/disks-black.png"));	
		sonder = new JButton(icone);
		sonder.setToolTipText("Mettre à joue les données");
		sonder.addActionListener(new ActionPanelInfos(this, 0));
		toolBar.add(sonder);
		
		dernierSondage = new JLabel("---");
		dernierSondage.setToolTipText("tick auquel le sondage a été effectué pour la dernière fois");
		toolBar.add(dernierSondage);
		
		JJStatDiagramme diag = new JJStatDiagramme(this, 25, 40+(1*30), 250, 25);
		diag.addItem(Color.BLUE, Configuration.cognitons.get(0).getNom(), 1.);
		diag.addItem(Color.BLACK, "Agents sans ce cogniton", 1.);
		this.add(diag , BorderLayout.EAST);
	}
	
	public void actualiserRatios(){
		//dernierSondage.setText(World.getInstance().)
		ratioCognitons.actualiser();
	}
	

}
