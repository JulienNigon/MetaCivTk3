package civilisation.amenagement;

import java.awt.Color;
import java.awt.Graphics;

import civilisation.individu.Humain;

import turtlekit.kernel.Patch;

public class Amenagement_Champ extends Amenagement{

	static String[] cognitonsLies = {"civilisation.individu.cognitons.beliefs.BELIEF_PossedeChamp"};

	
	public Amenagement_Champ(Patch p, Humain h)
	{
		super(p,h);
	}
	
	@Override
	public void dessiner(Graphics g,int x,int y,int cellS)
	{
		g.setColor(Color.yellow);
		for (int i = 0; i < cellS; i++)
		{
			if (i%2 == 1)
			{
				g.drawLine(x, y+i, x+cellS-1, y+i);
			}

		}
	}
	
	@Override
	public String getNom()
	{
		return "Champ";
	}
	
	@Override
	public String[] cognitonsLies()
	{
		return cognitonsLies;
	}
	
}
