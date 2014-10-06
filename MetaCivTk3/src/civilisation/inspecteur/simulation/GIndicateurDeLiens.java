package civilisation.inspecteur.simulation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import civilisation.Configuration;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

/**
 * Green one represent positive influence, red one represent negative influence.
 */

public class GIndicateurDeLiens extends JJComponent{

    Image img;

	
	public GIndicateurDeLiens(JJPanel parent, double xx, double yy, double w,
			double h , boolean positif) {
		super(parent, xx, yy, w, h);
		if (positif){
			img = Configuration.getImage("status.png");
		}
		else{
			img = Configuration.getImage("status-busy.png");
		}
		this.setBounds((int)xx,(int)yy,(int)w,(int)h);
	}

	@Override
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

		g2d.drawImage(img, 0 , 0, null);


    	//System.out.println("dessin du composant");
    	//validate();
    }
	
}
