package civilisation.inspecteur.simulation.environnement;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MouseListeTerrains implements MouseListener{

	PanelTerrains p;
	
	public MouseListeTerrains(PanelTerrains p)
	{
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		p.getListeTerrains().locationToIndex(e.getPoint());
	    if(SwingUtilities.isRightMouseButton(e)){
			p.afficherPopup(e);
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
