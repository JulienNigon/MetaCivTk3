/*
 * Star.java -TurtleKit - A 'star logo' in MadKit
 * Copyright (C) 2000-2007 Fabien Michel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package edu.turtlekit2.demos.gravity;
import java.awt.Color;
import java.util.ArrayList;

import edu.turtlekit2.kernel.agents.Turtle;

/** 
 * Adapted from the gas simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * this turtle turns around the nearer BlackKole.
 If two black holes are near it just goes forward  
  @author Fabien MICHEL
  @version 1.1 4/1/2000 */

@SuppressWarnings("serial")
public class Star extends Turtle 
{
	int sunX,sunY,rayon;
	ArrayList<BlackHole> holeTab;

	public Star()
	{
		super("fall");
	}

	public Star(int rayonMax)
	{
		super("fall");
		this.rayon = ((int) (Math.random()*rayonMax))+1;;
	}
	
	public String fall()
	{
		double dist1=50,dist2=50;
		for(int i = 0;i<holeTab.size();i++)	 
			if (distance(holeTab.get(i).xcor(),holeTab.get(i).ycor()) < dist1)  
			{
				sunX=holeTab.get(i).xcor();sunY=holeTab.get(i).ycor();
				dist2 = dist1;
				dist1 = distance(holeTab.get(i).xcor(),holeTab.get(i).ycor()) ;
			}
		if (dist2-dist1 > 10  && dist1 < 15)  
		{
			if (distance(sunX,sunY) > rayon+2)
				setHeading(towards(sunX,sunY));
			else
				setHeading(towards(sunX,sunY)-90);
			if (distance(sunX,sunY) > rayon) turnLeft(15);
			fd(1);
		}
		else	//System.err.println("orientation = "+getHeading());
			fd(4);
		return "fall";
	}

	public void setup()
	{
		holeTab = new ArrayList<BlackHole>();
		if(getAttributes().containsKey("rayon"))
			rayon = ((int) (Math.random()* getAttributes().getInt("rayon")))+1;
		for (int i = 0; i < getAttributes().getInt("total"); i++) {
			if(this.getTurtleWithID(i) instanceof BlackHole)
				holeTab.add((BlackHole)this.getTurtleWithID(i));
		}
		int i = (int) (Math.random()* holeTab.size());	 
		moveTo(holeTab.get(i).xcor()+((int) (Math.random()*10)),holeTab.get(i).ycor()+((int) (Math.random()*10)));
		randomHeading();
		setColor(Color.white);
		playRole("star");
	}
}

