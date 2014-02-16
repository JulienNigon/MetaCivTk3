
package civilisation.inspecteur.viewer;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import turtlekit.viewer.AbstractViewer;
import civilisation.inspecteur.PanelInspecteur;
import civilisation.inspecteur.PanelOptions;
import civilisation.inspecteur.PanelPerformances;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.tableauDeBord.PanelInfos;


/** 
 * Viewer contenat l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerTabbed extends AbstractViewer{

		JTabbedPane contentPane;

		PanelInspecteur panelInspecteur;
		PanelPerformances panelPerformances;
	  
		@Override
		protected void render(Graphics arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void setupFrame(JFrame frame) {
			super.setupFrame(frame);
			
			panelPerformances = new PanelPerformances();
			panelInspecteur = new PanelInspecteur();
			
		    contentPane = new JTabbedPane();
		    contentPane.addTab("Agent", panelInspecteur);
		    contentPane.addTab("Simulation", new PanelModificationSimulation());
		    contentPane.addTab("Options", new PanelOptions());
		    contentPane.addTab("Performances", panelPerformances);
		    contentPane.addTab("Tableau de bord", new PanelInfos());

		    frame.setContentPane(contentPane);
			frame.setLocation(50, 0);
		}

		@Override
		public void observe(){
			panelPerformances.actualiser();
			panelInspecteur.actualiser();
		}
}
