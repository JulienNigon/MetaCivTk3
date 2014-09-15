package civilisation.zones;

import java.util.ArrayList;
import java.util.List;

import civilisation.world.World;
import turtlekit.kernel.Patch;

public class ZoneElementaire extends Zone {

	ArrayList<Patch> content;

	public ZoneElementaire(ArrayList<Patch> content)
	{
		this.content = content;
		int xsum = 0;
		int ysum = 0;
		int xmin = 0;
		int xmax = World.getInstance().getWidth();
		int ymin = 0;
		int ymax = World.getInstance().getHeight();
		for(int i = 0; i < content.size();++i)
		{
			xsum += content.get(i).x;
			ysum += content.get(i).y;
			if(content.get(i).x > xmin)
			{
				xmin = content.get(i).x;
			}
			if(content.get(i).x < xmax)
			{
				xmax = content.get(i).x;
			}
			if(content.get(i).y > ymin)
			{
				ymin = content.get(i).y;
			}
			if(content.get(i).y < ymax)
			{
				ymax = content.get(i).y;
			}
		}
		this.width = xmax - xmin;
		this.height = ymax - ymin;
		int x = xsum / content.size();
		int y = ysum / content.size();
		int j = 0;
		while(j < content.size() && content.get(j).x != x && content.get(j).x != y)
		{
			++j;
		}
		if(j < content.size())
		{
			this.centroïde = content.get(j);
		}
	}
	
	public ZoneElementaire(Patch centroïde, int taille)
	{
		super(centroïde);
		this.width = taille;
		this.height = taille;
		int x;
		int y;
		for(int i = -taille;i < taille; ++i)
		{
			x = centroïde.x + i;
			for(int j = -taille; j < taille; ++j)
			{
				y = centroïde.y + j;
				if(x >= 0 && y > 0 && x < World.getInstance().getWidth() && y < World.getInstance().getHeight())
				{
				}
			}
		}
	}
	
	public ZoneElementaire() {
		// TODO Auto-generated constructor stub
		this.content = new ArrayList<Patch>();
	}



	public void addPatch(Patch p)
	{
		this.content.add(p);
	}
}
