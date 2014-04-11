
package civilisation.inspecteur.viewer;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import turtlekit.viewer.AbstractViewer;
import civilisation.individu.Humain;
import civilisation.inspecteur.PanelInspecteur;
import civilisation.inspecteur.PanelMind;
import civilisation.inspecteur.PanelOptions;
import civilisation.inspecteur.PanelPerformances;
import civilisation.inspecteur.groupPanel.PanelGroupOfAnAgent;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.tableauDeBord.PanelInfos;


/** 
 * Viewer contenat l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerHuman extends AbstractViewer{

		JTabbedPane contentPane;

		PanelInspecteur panelInspecteur;
		PanelMind panelMind;
		Humain h;
		PanelGroupOfAnAgent panelGroupOfAnAgent;
	  
		public ViewerHuman(Humain h) {
			super();
			this.h = h;
		}
		
		@Override
		protected void render(Graphics arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void setupFrame(JFrame frame) {
			super.setupFrame(frame);
			
			panelInspecteur = new PanelInspecteur(h);
			panelMind = new PanelMind(h);
			panelGroupOfAnAgent = new PanelGroupOfAnAgent(h);

		    contentPane = new JTabbedPane();
		    contentPane.addTab("Agent", panelInspecteur);
		    contentPane.addTab("Mind", new JScrollPane(panelMind));
		    contentPane.addTab("Groups", new JScrollPane(panelGroupOfAnAgent));


		    frame.setContentPane(contentPane);
			frame.setLocation(50, 0);
		}

		@Override
		public void observe(){
			panelInspecteur.actualiser();
			panelMind.updateData();
		}
}
