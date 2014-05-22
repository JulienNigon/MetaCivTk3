
package civilisation.inspecteur.viewer;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import turtlekit.viewer.AbstractViewer;
import civilisation.individu.Humain;
import civilisation.inspecteur.PanelInspecteur;
import civilisation.inspecteur.PanelMind;
import civilisation.inspecteur.PanelMindData;
import civilisation.inspecteur.PanelMiniMap;
import civilisation.inspecteur.PanelOptions;
import civilisation.inspecteur.PanelPerformances;
import civilisation.inspecteur.groupPanel.PanelGroupOfAnAgent;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.tableauDeBord.PanelInfos;
import civilisation.world.WorldViewer;


/** 
 * Viewer contenat l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerHuman extends AbstractViewer{

		JTabbedPane contentPane;

		PanelInspecteur panelInspecteur;
		PanelMind panelMind;
		PanelGroupOfAnAgent panelGroupOfAnAgent;
		PanelMiniMap miniMap;
		PanelMindData panelMindData;
		
		Humain h;
	  
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
			miniMap = new PanelMiniMap(h);
			panelMindData = new PanelMindData(h);
			
		    contentPane = new JTabbedPane();
		    contentPane.addTab("Agent", panelInspecteur);
		    contentPane.addTab("Mind",new JSplitPane(JSplitPane.VERTICAL_SPLIT,panelMindData,new JScrollPane(panelMind)) );
		    contentPane.addTab("Groups", new JScrollPane(panelGroupOfAnAgent));
		    //contentPane.setPreferredSize(new Dimension(400,400));


		    frame.setContentPane(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,contentPane,miniMap));
			frame.setLocation(50, 0);
		}

		@Override
		public void observe(){
			if (h.isAlive()) {
				//contentPane.setPreferredSize(new Dimension(400,400));
				panelInspecteur.actualiser();
				miniMap.updatePosition();
				panelMind.updateData();
				panelMindData.updateData();
			}
			else {
				this.killAgent(this);
			}

		}
}
