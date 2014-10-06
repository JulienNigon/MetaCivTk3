package civilisation.inspecteur.groupPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import civilisation.Configuration;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

public class GGroupMember extends JJComponent{


	public GGroupMember(JJPanel parent, double xx, double yy) {
		super(parent, xx, yy, 20, 20);
	}

	@Override
	public void paintComponent(Graphics g) 
	{   
		Graphics2D g2d = genererContexte(g);
        g2d.fill(new Rectangle2D.Double(0, 0, 20, 20));
        g2d.setPaint(new Color(187,210,225));
        g2d.fill(new Rectangle2D.Double(2, 2, 16, 16));

        Image ico = Configuration.getImage("user.png");
		g2d.drawImage(ico, 2, 2, null);

	}
	
}
