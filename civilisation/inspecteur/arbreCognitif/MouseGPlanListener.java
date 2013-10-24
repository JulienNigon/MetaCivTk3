package civilisation.inspecteur.arbreCognitif;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/** 
 * Gère l'interaction avec les plans graphiques (GPlan)
 * @author DTEAM
 * @version 1.1 - 2/2013
*/

public class MouseGPlanListener implements MouseListener{

	GPlan p;
	
	public MouseGPlanListener(GPlan p)
	{
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("click");
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
