
package civilisation.inspecteur.viewer;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import turtlekit.viewer.AbstractViewer;
import civilisation.inspecteur.PanelInspecteur;
import civilisation.inspecteur.PanelOptions;


/** 
 * Viewer contenat l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerInspecteur extends AbstractViewer{

	JTabbedPane contentPane;

	  
		@Override
		protected void render(Graphics arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void setupFrame(JFrame frame) {
			super.setupFrame(frame);
		    contentPane = new JTabbedPane();
		    contentPane.addTab("Agents", new PanelInspecteur());
		    contentPane.addTab("Options", new PanelOptions());
		    frame.setContentPane(contentPane);
			frame.setLocation(50, 0);
		}

		@Override
		public void observe(){
			//contentPane.actualiser();	
		}
}
