package civilisation.inspecteur.simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import civilisation.Configuration;
import civilisation.inspecteur.animations.JJPanel;

public class GTrigger extends GItemCognitif{

	static final int radius = 30;
	static final int border = 4;
	int attributesIndex;
	int value;
	int comparator;
	GCogniton gCogniton;
	
	
	public GTrigger(JJPanel parent, double xx, double yy) {
		super(parent, xx, yy, (double)radius, (double)radius);
	}

	
	@Override
	public void paintComponent(Graphics g) 
	{   
		//System.out.println("Paint GTrigger");
		
		Graphics2D g2d = genererContexte(g);
		//this.setBounds(   (int)this.getXx(),(int)this.getYy(),(int) (fm.stringWidth(cogniton.getNom()) + (2*margeEcriture) ),2*fm.getHeight());
		g2d.setColor(new Color(173,79,9));
		g2d.fillOval(0, 0, radius, radius);
		g2d.setColor(new Color(209,182,6));
		g2d.fillOval(border/2, border/2, radius-border, radius-border);
		
	/*    Graphics2D g2d = genererContexte(g);


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


	public int getAttributesIndex() {
		return attributesIndex;
	}


	public void setAttributesIndex(int attributesIndex) {
		this.attributesIndex = attributesIndex;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public int getComparator() {
		return comparator;
	}


	public void setComparator(int comparator) {
		this.comparator = comparator;
	}


	public GCogniton getgCogniton() {
		return gCogniton;
	}


	public void setgCogniton(GCogniton gCogniton) {
		this.gCogniton = gCogniton;
		String tmp = "";
		if 		(comparator ==  2) tmp = "> ";
		else if (comparator ==  1) tmp = ">=";
		else if (comparator ==  0) tmp = "==";
		else if (comparator == -1) tmp = "<=";
		else if (comparator == -2) tmp = "< ";
		
		
		this.setToolTipText("<html>"+Configuration.attributesNames.get(attributesIndex)+"<br> Trigger if " + tmp + " "+ value +"</html>");
	}
	
	
	
}
