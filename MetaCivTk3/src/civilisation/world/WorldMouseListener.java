package civilisation.world;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import turtlekit.kernel.Patch;

public class WorldMouseListener implements MouseListener{

	WorldViewer view;
	
	public WorldMouseListener(WorldViewer view) {
		this.view = view;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked");
	    if(SwingUtilities.isRightMouseButton(e)){

			float x = e.getX();
			float y = e.getY();
			Patch p = view.getPatch((int)(x/view.getCellSize()), view.getHeight() - ((int)(y/view.getCellSize()) + 1));
			System.out.println("Patch : " + p );
			//p.setColor(Color.RED);
			view.afficherPopup(e, p);
	    }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
