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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import civilisation.individu.cognitons.Cogniton;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;


public class GCogniton extends GItemCognitif{

	Cogniton cogniton;
	static float margeEcriture = 2;
	
	public GCogniton(JJPanel parent , double xx, double yy, double w, double h, Cogniton cogniton) {
		super(parent, xx, yy, w, h);
		this.cogniton = cogniton;
		this.setToolTipText(cogniton.toString());
		this.addMouseListener(new MouseGCognitonListener(this));

	}

	
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

    	FontMetrics fm = g2d.getFontMetrics();
    	this.setBounds(   (int)(this.getXx()+margeEcriture),(int)this.getYy()+2,fm.stringWidth(cogniton.getNom()),2*fm.getHeight()-4);

    	g2d.fill(new Rectangle2D.Double(this.getXx() ,this.getYy(),fm.stringWidth(cogniton.getNom()) + (2*margeEcriture),2*fm.getHeight()));
    	g2d.setColor(cogniton.getColor());
    	g2d.fill(new Rectangle2D.Double(this.getXx()+margeEcriture,this.getYy()+2,fm.stringWidth(cogniton.getNom()),2*fm.getHeight()-4));
    	
    	g2d.setColor(Color.BLACK);
    	g2d.drawString(cogniton.getNom(), (float) this.getXx()+margeEcriture, (float) (this.getYy()+(fm.getHeight()*1.3)));

    	//System.out.println("dessin du composant");
    	//validate();
    }


	public void selectionner() {
		ArrayList<JJComponent> liste = new ArrayList<JJComponent>();
		liste.add(this);
		((PanelArbreCognitif) parent).appliquerTransparence(liste);

		
	}



	
}
