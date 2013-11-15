package civilisation.inspecteur.simulation;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;


public class GLien extends JJComponent{

	JJComponent a ;
	JJComponent b ;
	Color color;
	int poids;
	
	public GLien(JJPanel parent, JJComponent a , JJComponent b, int poids, Color color) {
		super(parent, Math.min(a.getCentreX(), b.getCentreX()), Math.min(a.getCentreY(), b.getCentreY()), Math.abs(a.getCentreX() - b.getCentreX()), Math.abs(a.getCentreY() - b.getCentreY()));
		this.a = a;
		this.b = b;
		this.poids = poids;
		this.color = color;
		this.setFocusable(false);

	}

	
	@Override
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

    	FontMetrics fm = g2d.getFontMetrics();
    	//this.setBounds(0,0,0,0);  /*Utile pour un lien? A reflechir...*/
    	
    	g2d.setColor(color);
    	g2d.drawLine((int)(a.getCentreX()), (int)(a.getCentreY()), (int)(b.getCentreX()), (int)b.getCentreY());
		//System.out.println((int)a.getXx()+" "+ (int)a.getYy()+" "+(int)b.getXx()+" "+(int)b.getYy());


    	//System.out.println("dessin du composant");
    	//validate();
    }


	public JJComponent getA() {
		return a;
	}


	public void setA(JJComponent a) {
		this.a = a;
	}


	public JJComponent getB() {
		return b;
	}


	public void setB(JJComponent b) {
		this.b = b;
	}


	public int getPoids() {
		return poids;
	}


	public void setPoids(int poids) {
		this.poids = poids;
	}

	


	
}
