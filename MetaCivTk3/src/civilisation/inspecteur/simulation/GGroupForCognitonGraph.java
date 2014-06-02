package civilisation.inspecteur.simulation;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import civilisation.group.Group;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inspecteur.animations.JJPanel;

public class GGroupForCognitonGraph extends GItemCognitif{

	public static final int marge = 2;
	Group group;
	String role;



	public GGroupForCognitonGraph(JJPanel parent, double xx, double yy, double w, double h, Group gr, String role) {
		super(parent, xx, yy, w, h);
		this.group = gr;
		this.role = role;
	}

	@Override
	public void paintComponent(Graphics g) 
	{   
		
		
		Graphics2D g2d = genererContexte(g);
		FontMetrics fm = g2d.getFontMetrics();
		int longestString = Math.max(fm.stringWidth(group.getName()),fm.stringWidth(role));

		this.setBounds(   (int)(this.getXx()+marge),(int)this.getYy()+2,(int) (longestString + (2*marge) ),6*fm.getHeight());
    	this.setW(longestString + (2*marge));
    	this.setH(6*fm.getHeight());
    	
		g2d.fill(new Rectangle2D.Double(0 ,0,longestString + (2*marge),6*fm.getHeight()));
		g2d.setColor(new Color(65,220,80));
		g2d.fill(new Rectangle2D.Double(marge,2,longestString,6*fm.getHeight()-4));
		
		g2d.setColor(Color.BLACK);
		g2d.drawString(group.getName(), marge, (float) (fm.getHeight()*1.3));
		g2d.drawString(role, marge, (float) (fm.getHeight()*3.3));

		
/*
		FontMetrics fm = g2d.getFontMetrics();

		this.setBounds(   (int)(this.getXx()+margeEcriture),(int)this.getYy()+2,(int) (fm.stringWidth(cogniton.getNom()) + (2*margeEcriture) ),2*fm.getHeight());
		
		g2d.fill(new Rectangle2D.Double(0 ,0,fm.stringWidth(cogniton.getNom()) + (2*margeEcriture),2*fm.getHeight()));
		this.setW(fm.stringWidth(cogniton.getNom()) + (2*margeEcriture));
		this.setH(2*fm.getHeight() + (hueCircleSize/2) + 2);
		g2d.setColor(cogniton.getType().getCouleur());
		g2d.fill(new Rectangle2D.Double(margeEcriture,2,fm.stringWidth(cogniton.getNom()),2*fm.getHeight()-4));
		
		g2d.setColor(Color.BLACK);
		g2d.drawString(cogniton.getNom(), margeEcriture, (float) (fm.getHeight()*1.3));
*/
	}
	
	
}