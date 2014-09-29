package civilisation.inspecteur.groupPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

public class GMyselfInGroups extends JJComponent{

	public GMyselfInGroups(JJPanel parent, double xx, double yy, double w,
			double h) {
		super(parent, xx, yy, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintComponent(Graphics g) 
	{   
		Graphics2D g2d = genererContexte(g);
		g2d.setColor(Color.PINK);
		g2d.fillRect(0, (int)w, (int)w, (int)(h-w));
		g2d.fillOval(0, 0, (int)w, (int)w);

	}
}
