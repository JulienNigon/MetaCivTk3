
package civilisation.inspecteur.viewer;

import javax.swing.JComponent;
import civilisation.inspecteur.PanelOptions;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenant les options
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerOptions extends Viewer{

	PanelOptions agents;


	  @Override
	public void setup(){


			
		    agents = new PanelOptions();
			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(agents, SimulationBoard.VIEWER_ZONE, "Options"));

	  }
}
