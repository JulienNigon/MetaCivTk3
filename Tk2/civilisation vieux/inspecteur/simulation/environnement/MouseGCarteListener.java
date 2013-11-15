package civilisation.inspecteur.simulation.environnement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseGCarteListener implements MouseListener , MouseMotionListener{

	GCarte gCarte;
	
	public MouseGCarteListener(GCarte gCarte){
		System.out.println("creation");
		this.gCarte = gCarte;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("enter");
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
		gCarte.changerPatch(e);
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
