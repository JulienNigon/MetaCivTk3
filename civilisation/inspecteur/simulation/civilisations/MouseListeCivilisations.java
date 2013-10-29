package civilisation.inspecteur.simulation.civilisations;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MouseListeCivilisations implements MouseListener{

	PanelListeCivilisations p;
	
	public MouseListeCivilisations(PanelListeCivilisations p)
	{
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		

	    if(SwingUtilities.isLeftMouseButton(e)){
			p.getPanelCivilisations().update();
			//p.afficherPopup(e);
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
