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
import civilisation.individu.plan.Plan;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;


public class GPlan extends GItemCognitif{

	Plan plan;
	static float margeEcriture = 2;
	
	public GPlan(JJPanel parent , double xx, double yy, double w, double h, Plan plan) {
		super(parent, xx, yy, w, h);
		this.plan = plan;
		this.setToolTipText(plan.toString());
		this.addMouseListener(new MouseGPlanListener(this));

	}

	
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

    	FontMetrics fm = g2d.getFontMetrics();
    	this.setBounds(   (int)(this.getXx()+margeEcriture),(int)this.getYy()+2,fm.stringWidth(plan.getNom()),2*fm.getHeight()-4);

    	g2d.fill(new Rectangle2D.Double(this.getXx() ,this.getYy(),fm.stringWidth(plan.getNom()) + (2*margeEcriture),2*fm.getHeight()));
    	g2d.setColor(Color.GRAY);
    	g2d.fill(new Rectangle2D.Double(this.getXx()+margeEcriture,this.getYy()+2,fm.stringWidth(plan.getNom()),2*fm.getHeight()-4));
    	
    	g2d.setColor(Color.BLACK);
    	g2d.drawString(plan.getNom(), (float) this.getXx()+margeEcriture, (float) (this.getYy()+(fm.getHeight()*1.3)));

    	//System.out.println("dessin du composant");
    	//validate();
    }



	
}
