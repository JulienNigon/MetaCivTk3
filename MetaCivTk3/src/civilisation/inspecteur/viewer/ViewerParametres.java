
package civilisation.inspecteur.viewer;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import turtlekit.viewer.AbstractViewer;
import civilisation.inspecteur.PanelInspecteur;
import civilisation.inspecteur.PanelParametres;


/** 
 * Viewer contenant les parametres de la simulation
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerParametres extends AbstractViewer{

	PanelParametres param;

		@Override
		protected void render(Graphics arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void setupFrame(JFrame frame) {
			super.setupFrame(frame);
			try {
				param = new PanelParametres();
			} catch (IllegalArgumentException | IllegalAccessException
					| ClassNotFoundException | SecurityException
					| NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.setContentPane(param);
			frame.setLocation(50, 0);
		}

		@Override
		public void observe(){
		}
}
