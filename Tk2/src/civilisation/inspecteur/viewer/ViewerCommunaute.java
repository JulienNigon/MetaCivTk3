
package civilisation.inspecteur.viewer;

import javax.swing.JComponent;
import civilisation.inspecteur.PanelInspecteurCommunaute;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Observer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenant l'inspecteur de communautes
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerCommunaute extends Observer{

	PanelInspecteurCommunaute agents;


	  @Override
	public void setup(){


			
		    agents = new PanelInspecteurCommunaute();
			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(agents, SimulationBoard.VIEWER_ZONE, "Communautes"));

	  }
	  
		@Override
		public void watch(){
		agents.actualiser();	
		}
		
}
