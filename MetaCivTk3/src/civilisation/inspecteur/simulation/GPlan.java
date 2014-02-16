package civilisation.inspecteur.simulation;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;


public class GPlan extends GItemCognitif{

	NPlan plan;
	static float margeEcriture = 2;
	
	public GPlan(JJPanel parent , double xx, double yy, double w, double h, NPlan plan) {
		super(parent, xx, yy, w, h);
		this.plan = plan;
		this.setToolTipText(plan.toString());
		this.addMouseListener(new MouseGPlanListener(this));

	}

	
	@Override
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

    	FontMetrics fm = g2d.getFontMetrics();
    	this.setBounds(   (int)(this.getXx()+margeEcriture),(int)this.getYy()+2,(int) (fm.stringWidth(plan.getNom()) + (2*margeEcriture)),2*fm.getHeight());

    	g2d.fill(new Rectangle2D.Double(0,0,fm.stringWidth(plan.getNom()) + (2*margeEcriture),2*fm.getHeight()));
    	this.setW(fm.stringWidth(plan.getNom()) + (2*margeEcriture));
    	this.setH(2*fm.getHeight());
    	g2d.setColor(Color.GRAY);
    	g2d.fill(new Rectangle2D.Double(margeEcriture,2,fm.stringWidth(plan.getNom()),2*fm.getHeight()-4));
    	
    	if (plan.getIsSelfPlan()) {
        	g2d.setColor(Color.BLUE);
    	} else if (plan.getIsBirthPlan()) {
        	g2d.setColor(Color.RED);
    	} else {
        	g2d.setColor(Color.BLACK);
    	}
    	g2d.drawString(plan.getNom(), margeEcriture, (float) (fm.getHeight()*1.3));

    	//System.out.println("dessin du composant");
    	//validate();
    }

	public void selectionnerPlan(){
		((PanelStructureCognitive) this.getParent()).selectionnerPlan(plan);
	}


	public NPlan getPlan() {
		return plan;
	}

	public void afficherPopup(MouseEvent e){
		((PanelStructureCognitive) this.getParent()).afficherPopupPlan(e , this);
	}
	
}
