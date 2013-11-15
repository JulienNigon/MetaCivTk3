package civilisation.inspecteur.arbreCognitif;

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

import civilisation.individu.cognitons.Cogniton;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;


public class GLien extends JJComponent{

	JJComponent a ;
	JJComponent b ;
	
	public GLien(JJPanel parent , double xx, double yy, double w, double h, JJComponent a , JJComponent b) {
		super(parent, xx, yy, w, h);
		this.a = a;
		this.b = b;

	}

	
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

    	FontMetrics fm = g2d.getFontMetrics();
    	this.setBounds(0,0,0,0);  /*Utile pour un lien? A reflechir...*/


    	//System.out.println("dessin du composant");
    	//validate();
    }



	
}
