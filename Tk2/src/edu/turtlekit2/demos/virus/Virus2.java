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
package edu.turtlekit2.demos.virus;

import java.awt.Color;

import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Adapted from the epidemic simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * An infected turtle transmits virus, but not by sending a message,
	just by changing the color of the turtles who cross its way

  @author Fabien MICHEL
  @version 1.1 6/12/1999 */

@SuppressWarnings("serial")
public class Virus2 extends Turtle 
{
	boolean infected;

	public Virus2()
	{
	}

	public void setup()
	{

		String inf = getAttributes().getString("infection");
		if (inf.equals("red")) infected = true;
		else infected = false;


		if (infected)
		{
			playRole("infected");
			setColor(Color.red);
		}
		else
			setColor(Color.green);


		try {
			if(inf.equals("red")) setNextAction(getClass().getMethod("red"));
			else
				setNextAction(getClass().getMethod("green"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		};
	}

	public String green()
	{
		if (getColor() == Color.red)
		{
			playRole("infected");
			return ("red");
		}
		else
		{
			wiggle();
			return ("green");
		}
	}

	public String red()
	{
		wiggle();
		Turtle[] ts = turtlesHere();
		if (ts != null)
			for (int i=0; i < ts.length;i++)
				if (ts[i].getColor() == Color.green)
					ts[i].setColor(Color.red);
		return("red");
	}


}
