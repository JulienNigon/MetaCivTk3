package civilisation.inspecteur;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import turtlekit.kernel.Turtle;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.world.PanelMicroWorldViewer;
import civilisation.world.WorldViewer;

public class PanelMiniMap extends JPanel{

	PanelMicroWorldViewer view;
	Turtle t;
	
	public PanelMiniMap(Turtle t) {
		this.t = t;
		view = new PanelMicroWorldViewer(t);
		this.setPreferredSize(new Dimension(300,300));
		//this.add(new JButton("test"));
		this.add(view);

	}
	
	public void updatePosition() {
		repaint();
	}
	/*
    public void paint(Graphics g) {
    	super.paint(g);
    	view = new PanelMicroWorldViewer();
        Graphics2D g2d = (Graphics2D) g;

        g2d.clearRect(0, 0, 5000, 5000);


        BufferedImage bf = new BufferedImage(200, 200,BufferedImage.TYPE_INT_RGB); 
        view.paint(bf.createGraphics()); 
        
    	g2d.drawImage(bf.getSubimage(50, 50, 100, 100), 10, 10, null);
        g.drawRect(10, 10, 240, 240);

    }
*/
}
