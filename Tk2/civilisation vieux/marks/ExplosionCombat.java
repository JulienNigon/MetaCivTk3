package civilisation.marks;

import java.awt.Color;
import java.awt.Graphics;

public class ExplosionCombat {

	
	public void dessiner(Graphics g,int x,int y,int cellS)
	{
		g.setColor(Color.red);
		for (int i = 0; i < cellS; i++)
		{
			if (i%2 == 1)
			{
				g.drawLine(x-2, y+i, x+cellS-1+2, y+i);
			}
			if (i%2 == 1)
			{
				g.drawLine(x+i, y-2, x+i, y+cellS-1+2);
			}
		}
	}
	
	
	
	
	
}
