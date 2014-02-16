package civilisation.inspecteur.tableauDeBord;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

public class IconeTendance extends JJComponent{

    Image img;
    int tendance;
	
	public IconeTendance(JJPanel parent, double xx, double yy, double w,
			double h , int positif) {
		super(parent, xx, yy, w, h);
		tendance = positif;
		try {
			if (tendance == 1){
				img = ImageIO.read(this.getClass().getResource("../icones/arrow-045.png"));
			}
			else if (tendance == -1){
				img = ImageIO.read(this.getClass().getResource("../icones/arrow-315.png"));
			}
			else{
				img = ImageIO.read(this.getClass().getResource("../icones/arrow-000.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setBounds((int)xx,(int)yy,(int)w,(int)h);
	}

	@Override
	public void paintComponent(Graphics g) 
    {    
        Graphics2D g2d = genererContexte(g);

		g2d.drawImage(img, 0 , 0, null);
    }
	
	public void setTendance(int tendance){
		this.tendance = tendance;
		try {
			if (tendance == 1){
				img = ImageIO.read(this.getClass().getResource("../icones/arrow-045.png"));
				this.setToolTipText("tendance ˆ la hausse");
			}
			else if (tendance == -1){
				img = ImageIO.read(this.getClass().getResource("../icones/arrow-315.png"));
				this.setToolTipText("tendance ˆ la baisse");
			}
			else{
				img = ImageIO.read(this.getClass().getResource("../icones/arrow-000.png"));
				this.setToolTipText("stagnation");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
