
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

import civilisation.inspecteur.PanelInspecteur;
import civilisation.inspecteur.PanelInspecteurCommunaute;
import civilisation.inspecteur.PanelOptions;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Observer;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/** 
 * Viewer contenat l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerInspecteur extends Viewer{

	JTabbedPane contentPane;


	  public void setup(){


			
		    contentPane = new JTabbedPane();
		    contentPane.addTab("Agents", new PanelInspecteur());
		    contentPane.addTab("Communautes", new PanelInspecteurCommunaute());
		    contentPane.addTab("Options", new PanelOptions());

			this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
					new GUIMessage<JComponent>(contentPane, SimulationBoard.VIEWER_ZONE, "Agents"));

	  }
}
