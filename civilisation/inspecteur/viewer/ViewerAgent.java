
package civilisation.inspecteur.viewer;

import javax.swing.JComponent;
import civilisation.inspecteur.PanelInspecteur;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenant l'inspecteur d'agents
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerAgent extends Viewer{

	PanelInspecteur agents;


	  @Override
	public void setup(){


			
		    agents = new PanelInspecteur();
			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(agents, SimulationBoard.VIEWER_ZONE, "Agents"));

	  }
	  
		@Override
		public void watch(){
		agents.actualiser();	
		}
}
