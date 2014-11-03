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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import civilisation.Configuration;
import civilisation.individu.cognitons.TypeCulturon;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;


public class GCloudCogniton extends GCogniton{

	TypeCulturon cogniton;
	static float margeEcriture = 2;
	public final static int hueCircleSize = 12;
	static final int xSize = 125;
	static final int ySize = 50;
	BufferedImage bImg;
	
	public GCloudCogniton(JJPanel parent , double xx, double yy, double w, double h, TypeCulturon cogniton) {
		super(parent, xx, yy, w, h);
		this.cogniton = cogniton;
		this.setToolTipText(cogniton.toString());
		this.addMouseListener(new MouseGCognitonListener(this));
		


		File image;
        try {

			bImg = ImageIO.read(Configuration.class.getResourceAsStream("civilisation/graphisme"));


		} catch (IOException e) {

			e.printStackTrace();
		}


	}

	
	@Override
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);


    	FontMetrics fm = g2d.getFontMetrics();

    	this.setBounds(   (int)(this.getXx()),(int)this.getYy(),xSize,ySize);
    	
    	this.setW(xSize);
    	this.setH(ySize);

        g.drawImage(bImg, 0, 0, null);       
        
    	g2d.setColor(Color.BLACK);
    	g2d.drawString(cogniton.getNom(), margeEcriture, (float) (fm.getHeight()*1.3));
    	
    	/*paint the "hues" of the cogniton*/
    	int huesAlreadyPaint = 0;
    	for (int i = 0 ; i < TypeCogniton.nHues; i++) {
    		if (cogniton.getHues()[i] != 0){
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


	@Override
	public void selectionner() {
		ArrayList<JJComponent> liste = new ArrayList<JJComponent>();
		liste.add(this);
		((PanelStructureCognitive) parent).appliquerTransparence(liste);

		
	}

	/*Redefinition of getCentre to pass over hue circle*/
	/*public double getCentreY(){
		return (getYy() + (h - (hueCircleSize / 2)))/2.;
	}*/

	@Override
	public TypeCogniton getCogniton() {
		return cogniton;
	}

	@Override
	public void afficherPopup(MouseEvent e){
		((PanelStructureCognitive) this.getParent()).afficherPopupCogniton(e , this);
	}

	
}
