package civilisation.inspecteur.groupPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

public class GGroupBorder extends JJComponent{

	public GGroupBorder(JJPanel parent, double xx, double yy, double w, double h) {
		super(parent, xx, yy, w, h);
	}

	@Override
	public void paintComponent(Graphics g) 
	{   
		Graphics2D g2d = genererContexte(g);
		g2d.setColor(new Color(173,79,9));
		g2d.drawRect(1, 1, (int)w-2, (int)h-2);

	}
}
