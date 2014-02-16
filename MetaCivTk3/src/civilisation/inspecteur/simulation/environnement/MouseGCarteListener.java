package civilisation.inspecteur.simulation.environnement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class MouseGCarteListener implements MouseListener , MouseMotionListener{

	GCarte gCarte;
	
	public MouseGCarteListener(GCarte gCarte){
		System.out.println("creation");
		this.gCarte = gCarte;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (gCarte.getPanelEnvironnement().getTypeDessin() == 1){
			gCarte.peindre(e);
		}
	    if(SwingUtilities.isRightMouseButton(e)){
			gCarte.afficherPopup(e);
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
	public void mouseDragged(MouseEvent e) {
		if (gCarte.getPanelEnvironnement().getTypeDessin() == 0){
			gCarte.changerPatch(e);
		}
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
