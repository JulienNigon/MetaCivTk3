package civilisation.amenagement;

import java.awt.Color;
import java.awt.Graphics;

import edu.turtlekit2.kernel.environment.Patch;

public class Amenagement_Route extends AmenagementPublic {
	static String[] cognitonsLies = {};
	boolean[] roadDirection = new boolean[4];
	Patch p;
		//0 -> rigth
		//1 -> down
		//2 -> left
		//3 -> up

	
	public Amenagement_Route(Patch p)
	{
		super(p);
		this.p = p;
		recomputeLinks(-1);
		Patch neighbors[] = p.getNeighbors();
		for (int i = 0 ; i < 4 ; i++) {
			Amenagement_Route mark = (Amenagement_Route) neighbors[i*2].getMark("Route");
			if (mark != null) {
				mark.recomputeLinks((i+2)%4);
				neighbors[i*2].dropMark("Route", mark);
			}
		}
	}
	
	@Override
	public void dessiner(Graphics g,int x,int y,int cellS)
	{
		g.setColor(Color.GRAY);
		
		if (roadDirection[2]) {
			g.drawLine(x, y+(cellS/2)    , x+(cellS-1)/2, y+(cellS/2)    );
			g.drawLine(x, y+(cellS/2) + 1, x+(cellS-1)/2, y+(cellS/2) + 1);
		} 
		if (roadDirection[3]) {
			g.drawLine(x+(cellS/2)    , y+((cellS-1)/2), x+(cellS/2)    , y+cellS-1);
			g.drawLine(x+(cellS/2) + 1, y+((cellS-1)/2), x+(cellS/2) + 1, y+cellS-1);
		} 
		if (roadDirection[0]) {
			g.drawLine(x+(cellS-1)/2, y+(cellS/2)    , x+cellS-1, y+(cellS/2)    );
			g.drawLine(x+(cellS-1)/2, y+(cellS/2) + 1, x+cellS-1, y+(cellS/2) + 1);
		} 
		if (roadDirection[1]) {
			g.drawLine(x+(cellS/2)    , y, x+(cellS/2)    , y+((cellS-1)/2));
			g.drawLine(x+(cellS/2) + 1, y, x+(cellS/2) + 1, y+((cellS-1)/2));
		}

	}
	
	public void recomputeLinks(int forcedNeighbor) {
		Patch neighbors[] = p.getNeighbors();
		Amenagement mark;
		//0 -> right 2 -> down
		
		for (int i = 0 ; i < 4 ; i++) {
			mark = (Amenagement) neighbors[i*2].getMark("Route");
			if (mark != null) {
				roadDirection[i] = true;
				neighbors[i*2].dropMark("Route", mark);
			} else if (forcedNeighbor == i) {
				roadDirection[i] = true;
			} else {
			
				roadDirection[i] = false;
			}
		
		}

	}
	
	
	@Override
	public String getNom()
	{
		return "Route";
	}
	
	@Override
	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
	
}
