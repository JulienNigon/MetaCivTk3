package civilisation.inspecteur.animations;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class JJComponentMouseMotionListener implements MouseMotionListener{

	JJComponent c;
	
	public JJComponentMouseMotionListener(JJComponent c){
		this.c = c;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (c.isDragable()){
			if (e.getSource().equals(c)){
				c.setXx(c.getXx() + e.getX());
				c.setYy(c.getYy() + e.getY());
			}
			else
			{
				c.setXx(e.getX());
				c.setYy(e.getY());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
