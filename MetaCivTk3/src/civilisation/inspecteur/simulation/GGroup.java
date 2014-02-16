package civilisation.inspecteur.simulation;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import civilisation.individu.cognitons.NCogniton;
import civilisation.inspecteur.animations.JJPanel;

public class GGroup extends GItemCognitif{




	public GGroup(JJPanel parent, double xx, double yy, double w, double h) {
		super(parent, xx, yy, w, h);
	}

	@Override
	public void paintComponent(Graphics g) 
	{    
	/*  Graphics2D g2d = genererContexte(g);


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