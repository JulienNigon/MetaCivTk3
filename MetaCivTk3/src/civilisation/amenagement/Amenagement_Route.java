package civilisation.amenagement;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.Semaphore;

import civilisation.world.World;
import turtlekit.kernel.Patch;

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
		p.dropMark("Route", this);
		this.p = p;
		recomputeLinks(-1);
		List<Patch> neighbors = p.getNeighbors(1, true);
		for (int i = 0 ; i < neighbors.size() ; i++) {
			Amenagement_Route mark = (Amenagement_Route) neighbors.get(i).getMark("Route");
			if (mark != null) {
				
				neighbors.get(i).dropMark("Route", mark);
				
				mark.recomputeLinks((i+2)%4);
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

		synchronized (p) {
		//List<Patch> neighbors = p.getNeighbors(1, false); //TODO There is an order??
		
		Amenagement mark = null;
		//0 -> right 2 -> down
		
		for (int i = -1 ; i <= 1 ; i++) {
			for (int j = -1 ; j <= 1 ; j++) {
				mark = null;
				if (i == 0 || j == 0) {
					//System.out.println(i + " "+j+" "+((i*-1)+Math.abs(i)+(-1*j)+(Math.abs(j)*2)));

					mark = (Amenagement) World.getInstance().getPatchAt(this.p.x + i, this.p.y + j).getMark("Route");


					if (mark != null) {
						roadDirection[(i*-1)+Math.abs(i)+(-1*j)+(Math.abs(j)*2)] = true;
						World.getInstance().getPatchAt(mark.position.x, mark.position.y).dropMark("Route", mark);
					}
					else {
						roadDirection[(i*-1)+Math.abs(i)+(-1*j)+(Math.abs(j)*2)] = false;
					}
					
					}
				}
			

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
