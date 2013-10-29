package civilisation.inspecteur.animations;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class JJComponent extends JComponent{


	
	protected double xx;
	protected double yy;
	protected double w;
	protected double h;
	double theta;
	double opacite = 1.0;
	boolean suppressionProgrammee = false;
	boolean dragable = false;
	ArrayList<JJAnimation> animations;
	protected JJPanel parent;
	
	
	public JJComponent(JJPanel parent , double xx , double yy , double w , double h)
	{
		super();
		this.xx = xx;
		this.yy = yy;
		this.setLocation((int)xx, (int)yy);
		this.setSize((int)w, (int)h);

		this.w = w;
		this.h = h;
		this.parent = parent;
		theta = 0;
		animations = new ArrayList<JJAnimation>();
		this.addMouseListener(new JJComponentMouseListener(this));
		this.addMouseMotionListener(new JJComponentMouseMotionListener(this));

	}
	
	public void addAnimation(JJAnimation anim){
		animations.add(anim);
		parent.animationAjoutee();
	}
	
	public boolean animate(){


		if (!animations.isEmpty()){
			for (int i = 0; i < animations.size(); i++){
				if (!animations.get(i).animer()){  //On applique l'animation et si elle est finie on la retire de la liste
					if(animations.get(i).isVieLimite()){
						suppressionProgrammee = true;
						animations.remove(i);
						i--;
						return true;

					}		
					animations.remove(i);
					i--;
				}

			}

			if (animations.isEmpty()){
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			
			return false;
		}
	}
	

	
	protected Graphics2D genererContexte(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.translate(xx+(w/2),yy+(h/2));
		g2d.rotate(theta);
		g2d.translate(-xx-(w/2),-yy-(h/2));
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacite));

		return g2d;
	}
    
	

	public double getXx() {
		return xx;
	}

	public void setXx(double xx) {
		this.xx = xx;
		this.setLocation((int)xx, (int)yy);

	}

	public double getYy() {
		return yy;
	}

	public void setYy(double yy) {
		this.yy = yy;
		this.setLocation((int)xx, (int)yy);

	}

	public double getW() {
		return w;
	}

	@Override
	public int getHeight(){
		return (int) h;
	}
	
	@Override
	public int getWidth(){
		return (int) w;
	}
	
	@Override
	public int getX(){
		return (int) xx;
	}
	
	@Override
	public int getY(){
		return (int) yy;
	}
	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public double getOpacite() {
		return opacite;
	}

	public void setOpacite(double d) {
		this.opacite = d;
		if (this.opacite < 1.){
	        setOpaque(false);
		}
		else{
	        setOpaque(true);
		}
		if(this.opacite < 0) this.opacite = 0.;
		if(this.opacite > 1) this.opacite = 1.;
	}

	public boolean isSuppressionProgrammee() {
		return suppressionProgrammee;
	}

	public void setSuppressionProgrammee(boolean suppressionProgrammee) {
		this.suppressionProgrammee = suppressionProgrammee;
	}

	public boolean isDragable() {
		return dragable;
	}

	public void setDragable(boolean dragable) {
		this.dragable = dragable;
	}
    
	public double getCentreX(){
		return getXx() + w/2.;
	}
	
	public double getCentreY(){
		return getYy() + h/2.;
	}
	

    
}