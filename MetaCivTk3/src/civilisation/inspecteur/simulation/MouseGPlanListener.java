package civilisation.inspecteur.simulation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

/** 
 * Manage interaction with graphical plan
*/

public class MouseGPlanListener implements MouseListener{

	GPlan p;
	
	public MouseGPlanListener(GPlan p)
	{
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    if(SwingUtilities.isLeftMouseButton(e)){
			p.selectionnerPlan();
	    }	
	    if(SwingUtilities.isRightMouseButton(e)){
			p.afficherPopup(e);
	    }		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
