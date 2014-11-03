package civilisation.inspecteur.simulation;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;


public class GCogniton extends GItemCognitif{

	TypeCogniton typeCogniton;
	static float margeEcriture = 2;
	public final static int hueCircleSize = 12;
	
	/*For individual agent view*/
	private Cogniton cogniton;
	
	public GCogniton(JJPanel parent , double xx, double yy, double w, double h, TypeCogniton cogniton) {
		super(parent, xx, yy, w, h);
		this.typeCogniton = cogniton;
		this.setToolTipText(cogniton.toString());
		this.addMouseListener(new MouseGCognitonListener(this));
	}
	
	public GCogniton(JJPanel parent , double xx, double yy, double w, double h) {
		super(parent, xx, yy, w, h);
	}

	
	@Override
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

        String displayedString = typeCogniton.getNom();
        if (cogniton != null) displayedString += " [" + cogniton.getWeigth() +"]";

    	FontMetrics fm = g2d.getFontMetrics();

    	this.setBounds(   (int)(this.getXx()+margeEcriture),(int)this.getYy()+2,(int) (fm.stringWidth(displayedString) + (2*margeEcriture) ),2*fm.getHeight());
    	
    	g2d.fill(new Ellipse2D.Double(0 ,0,fm.stringWidth(displayedString) + (2*margeEcriture),2*fm.getHeight()));
    	this.setW(fm.stringWidth(displayedString) + (2*margeEcriture));
    	this.setH(2*fm.getHeight() + (hueCircleSize/2) + 2);
    	g2d.setColor(typeCogniton.getType().getCouleur());
    	g2d.fill(new Ellipse2D.Double(margeEcriture,2,fm.stringWidth(displayedString),2*fm.getHeight()-4));
    	
    	g2d.setColor(Color.BLACK);
    	g2d.drawString(displayedString, margeEcriture, (float) (fm.getHeight()*1.3));
    	
    	/*paint the "hues" of the cogniton*/
    	int huesAlreadyPaint = 0;
    	for (int i = 0 ; i < TypeCogniton.nHues; i++) {
    		if (typeCogniton.getHues()[i] != 0){
    			g2d.setColor(TypeCogniton.hueColors[i]);
    			Ellipse2D cercle = new Ellipse2D.Float(hueCircleSize*huesAlreadyPaint, 2*fm.getHeight() - (hueCircleSize/2), hueCircleSize, hueCircleSize);
    			g2d.fill(cercle);
    			g2d.setColor(Color.BLACK);
    			g2d.drawOval(hueCircleSize*huesAlreadyPaint, 2*fm.getHeight() - (hueCircleSize/2), hueCircleSize, hueCircleSize);
    			huesAlreadyPaint++;
    		}
    	}
    	
    	//this.setBounds(   (int)(this.getXx()+margeEcriture),(int)this.getYy()+2,(int) (fm.stringWidth(cogniton.getNom()) + (2*margeEcriture)),2*fm.getHeight());


    	//System.out.println("dessin du composant " + this.getSize() + " " + this.getLocation());
    	//validate();
    }


	public void selectionner() {
		ArrayList<JJComponent> liste = new ArrayList<JJComponent>();
		liste.add(this);
		((PanelStructureCognitive) parent).appliquerTransparence(liste);

		
	}

	/*Redefinition of getCentre to pass over hue circle*/
	/*public double getCentreY(){
		return (getYy() + (h - (hueCircleSize / 2)))/2.;
	}*/

	public TypeCogniton getCogniton() {
		return typeCogniton;
	}

	public void afficherPopup(MouseEvent e){
		((PanelStructureCognitive) this.getParent()).afficherPopupCogniton(e , this);
	}

	public void setCogniton(Cogniton cogniton) {
		this.cogniton = cogniton;
	}

	
}
