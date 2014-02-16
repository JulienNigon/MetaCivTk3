package civilisation.inspecteur.simulation;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MouseArbreActionsListener implements MouseListener{

	PanelArbreActions p;
	
	public MouseArbreActionsListener(PanelArbreActions p)
	{
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		;
	    if(SwingUtilities.isRightMouseButton(e)){
			p.afficherPopup(e,((NodeArbreActions)p.getArbreActions().getPathForLocation(e.getX(), e.getY()).getLastPathComponent()).getAction());
			p.setActionActive(((NodeArbreActions)p.getArbreActions().getPathForLocation(e.getX(), e.getY()).getLastPathComponent()).getAction());
	    }	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	


}
