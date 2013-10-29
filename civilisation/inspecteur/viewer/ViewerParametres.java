
package civilisation.inspecteur.viewer;

import javax.swing.JComponent;
import civilisation.inspecteur.PanelParametres;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenant les parametres de la simulation
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerParametres extends Viewer{

	PanelParametres param;


	  @Override
	public void setup(){


			
		    try {
				try {
					try {
						param = new PanelParametres();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(param, SimulationBoard.VIEWER_ZONE, "Parametres"));

	  }
}
