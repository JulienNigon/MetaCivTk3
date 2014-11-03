package civilisation.inspecteur.animations;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class JJPanel extends JPanel implements ActionListener{

	int elementsAnimes = 0;
	int delay = 15;
	Timer timer;
	int sizeX = 0;
	int sizeY = 0;
	JJComponent maxX, maxY;
	
	public JJPanel(){
		super();

		
	// this.setDoubleBuffered(true);
	    this.setVisible(true);
	    this.setLayout(null);
	    
        timer = new Timer (delay, this);
        timer.start();
		}


	public void animate(){
		if (elementsAnimes > 0 ){
			//System.out.println("animate>0");
	        int max = this.getComponentCount();
	        Component[] components = this.getComponents();
	        for (int i = 0; i < max ; i++){
	        	if (components[i] instanceof JJComponent){
		        	if (((JJComponent) components[i]).animate()) {
		        		elementsAnimes--;
		        		if (((JJComponent) components[i]).isSuppressionProgrammee()){
		        			this.remove(components[i]);
		        		}
		        	}
	        		}
	        	}
	        }
		//	this.repaint();
		}
		
	
	public Dimension getPreferredSize() {
		//System.out.println("getPreferredSize" + this.toString());
		return super.getPreferredSize();
	}
	
	/*
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.clearRect(0, 0, 5000, 5000);

       // redessiner(g);
    	super.paintComponents(g);
        int max = this.getComponentCount();

        Component[] components = this.getComponents();
        
        for (int i = 0; i < max ; i++){
      //  	components[i].paint(g2d);
        }

    }
    */
/*

   public void paintChildren(Graphics g){

    }
*/
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		animate();
		repaint();
		//this.setPreferredSize(getMaximumSize());
		revalidate();
		
	}
	
	public void animationAjoutee(){
		elementsAnimes++;
	}


	public int getDelay() {
		return delay;
	}


	public void setDelay(int delay) {
		this.delay = delay;
		timer.setDelay(delay);
	}
	
	public void updatePreferredSize(JJComponent compo) {
		if (compo.getXx() + compo.getW() > sizeX) {
			sizeX = (int) (compo.getXx() + compo.getW());
			this.setPreferredSize(new Dimension(sizeX,sizeY));
			maxX = compo;
		}
		if (compo.getYy() + compo.getH() > sizeY) {
			sizeY = (int) (compo.getYy() + compo.getH());
			this.setPreferredSize(new Dimension(sizeX,sizeY));
			maxY = compo;
		}
		if (compo == maxX && compo.getXx() + compo.getW() < sizeX) {
			sizeX = (int) (compo.getXx() + compo.getW());
	        Component[] components = this.getComponents();
	        int max = this.getComponentCount();
	        for (int i = 0; i < max ; i++){
	        	if (components[i] instanceof JJComponent){
		        	this.updatePreferredSize((JJComponent) components[i]);
	        		}
	        	}
	        }
		
	if (compo == maxY && compo.getYy() + compo.getH() < sizeY) {
		sizeY = (int) (compo.getYy() + compo.getH());
        Component[] components = this.getComponents();
        int max = this.getComponentCount();
        for (int i = 0; i < max ; i++){
        	if (components[i] instanceof JJComponent){
	        	this.updatePreferredSize((JJComponent) components[i]);
        		}
        	}
        }
	
	
	}



	
}
