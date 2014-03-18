package civilisation.inspecteur.simulation.groupManager;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MouseGroupTreeListener implements MouseListener{

	PanelGroupTree p;
	
	public MouseGroupTreeListener(PanelGroupTree p)
	{
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	    if(SwingUtilities.isLeftMouseButton(e)){
			p.changeSelection(((NodeGroupTree)p.getGroupTree().getPathForLocation(e.getX(), e.getY()).getLastPathComponent()).getGm());
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
