
package civilisation.inspecteur.viewer;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import turtlekit.viewer.AbstractViewer;
import civilisation.inspecteur.PanelInspecteur;



/** 
 * Viewer contenant l'inspecteur d'agents
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ViewerAgent extends AbstractViewer{

	PanelInspecteur agents;

	@Override
	protected void render(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setupFrame(JFrame frame) {
		super.setupFrame(frame);
		agents = new PanelInspecteur();
		frame.setContentPane(agents);
		frame.setLocation(50, 0);
	}

	@Override
	public void observe(){
		agents.actualiser();	
	}
}
