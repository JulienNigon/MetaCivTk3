package civilisation.inspecteur.simulation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

/** 
 * Gère l'interaction avec les cognitons graphiques (GCognitons)
 * @author DTEAM
 * @version 1.1 - 2/2013
*/

public class MouseGCognitonListener implements MouseListener{

	GCogniton c;
	
	public MouseGCognitonListener(GCogniton c)
	{
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//c.selectionner();
	    if(SwingUtilities.isRightMouseButton(e)){
			c.afficherPopup(e);
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
