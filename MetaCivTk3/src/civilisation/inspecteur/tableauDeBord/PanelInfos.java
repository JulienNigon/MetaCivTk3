package civilisation.inspecteur.tableauDeBord;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.inspecteur.animations.JJPanel;

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
		
		ImageIcon icone = Configuration.getIcon("disks-black.png");	
		sonder = new JButton(icone);
		sonder.setToolTipText("Mettre _ joue les donn_es");
		sonder.addActionListener(new ActionPanelInfos(this, 0));
		toolBar.add(sonder);
		
		dernierSondage = new JLabel("---");
		dernierSondage.setToolTipText("tick auquel le sondage a _t_ effectu_ pour la derni_re fois");
		toolBar.add(dernierSondage);

	}
	
	public void actualiserRatios(){
		//dernierSondage.setText(World.getInstance().)
		ratioCognitons.actualiser();
	}
	

}
