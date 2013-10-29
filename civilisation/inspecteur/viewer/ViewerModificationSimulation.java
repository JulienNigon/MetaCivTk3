
package civilisation.inspecteur.viewer;

import javax.swing.JComponent;
import civilisation.inspecteur.simulation.PanelModificationSimulation;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenat l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerModificationSimulation extends Viewer{

	PanelModificationSimulation contentPane;


	  @Override
	public void setup(){


			
			
		  contentPane = new PanelModificationSimulation();
			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(contentPane, SimulationBoard.VIEWER_ZONE, "Simulation"));

	  }

}
