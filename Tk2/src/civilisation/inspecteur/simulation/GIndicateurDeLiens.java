package civilisation.inspecteur.simulation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

public class GIndicateurDeLiens extends JJComponent{

    Image img;

	
	public GIndicateurDeLiens(JJPanel parent, double xx, double yy, double w,
			double h , boolean positif) {
		super(parent, xx, yy, w, h);
		try {
			if (positif){
				img = ImageIO.read(this.getClass().getResource("../icones/status.png"));
			}
			else{
				img = ImageIO.read(this.getClass().getResource("../icones/status-busy.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
