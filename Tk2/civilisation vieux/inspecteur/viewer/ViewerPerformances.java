
package civilisation.inspecteur.viewer;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import civilisation.inspecteur.PanelOptions;
import civilisation.inspecteur.PanelPerformances;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Observer;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenant les infos de performances
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerPerformances extends Viewer{

	PanelPerformances panel;


	  public void setup(){


			
		    panel = new PanelPerformances();
			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(panel, SimulationBoard.VIEWER_ZONE, "Performances"));

	  }
	  
		public void watch(){
		panel.actualiser();	
		}
}
