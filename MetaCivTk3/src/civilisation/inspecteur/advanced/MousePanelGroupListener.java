package civilisation.inspecteur.advanced;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import civilisation.inspecteur.PanelGroupViewer;
import civilisation.world.WorldViewer;

public class MousePanelGroupListener implements MouseListener, ActionListener{

	PanelGroupViewer p;
	int index;
	
	public MousePanelGroupListener(PanelGroupViewer p , int index)
	{
		this.p = p;
		this.index = index;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (index == 0) { //Group table
	        int row = p.getGroupTable().rowAtPoint(e.getPoint());
	        int col = p.getGroupTable().columnAtPoint(e.getPoint());

		    if(SwingUtilities.isRightMouseButton(e)){
		    	p.getGroupTable().setRowSelectionInterval(row, row);
				p.afficherPopup(e);
		    }	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 1) { //Show group in world view
	        p.changeObservedGroup();
		}
		
	}
	


}
