
package civilisation.inspecteur.viewer;

import javax.swing.JComponent;
import civilisation.inspecteur.tableauDeBord.PanelInfos;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenant les infos de performances
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerTableauDeBord extends Viewer{

	PanelInfos panel;


	  @Override
	public void setup(){


			
		    panel = new PanelInfos();
			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(panel, SimulationBoard.VIEWER_ZONE, "Tableau de bord"));

	  }
	  
	/*	public void watch(){
		panel.actualiser();	
		}*/
}
