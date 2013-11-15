/*
 * TurtleKit - An Artificial Life Simulation Platform
 * Copyright (C) 2000-2010 Fabien Michel, Gregory Beurier
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
package edu.turtlekit2.demos.hunt;

import java.awt.Color;

import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Adapted from the hunt simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * 
 * A Predator
  @author Fabien MICHEL
  @version 1.1 17/10/2000 */

@SuppressWarnings("serial")
public class Predator extends Turtle 
{

	int visionRadius;

	public Predator()
	{
		super("live");
	}

	public void setup()
	{
		visionRadius = getAttributes().getInt("vision");
		playRole("predator");
		randomHeading();
		setColor(Color.red);
		if (countTurtlesHere()>0)
			fd(1);
	} 

	public String live()
	{
		setHeading(towardsAPrey(visionRadius));
		move();
		return "live";
	}

	void move()
	{
		if (countTurtlesAt(dx(),dy())>0)
			turnRight(50);
		if (countTurtlesAt(dx(),dy())>0)
			turnLeft(100);
		if (countTurtlesAt(dx(),dy())==0)
			fd(1);
		else turnRight(50);
	}


	double towardsAPrey(int radius)
	{
		for(int i=-radius;i<=radius;i++)
			for(int j=-radius;j<=radius;j++)
				if (! (i==0 && j==0) )
				{
					Turtle[] tur = turtlesAt(i,j);
					if (tur != null && tur.length>0 && tur[0].isPlayingRole("prey")) //instead of "instanceof". So prey can be another java class
						return towards(tur[0].xcor(),tur[0].ycor());
				}
		return Math.random()*180;
	}


}










