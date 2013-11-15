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
package edu.turtlekit2.demos.creation;

import java.awt.Color;
import java.util.ArrayList;

import edu.turtlekit2.demos.gravity.BlackHole;
import edu.turtlekit2.demos.gravity.Star;
import edu.turtlekit2.kernel.agents.Turtle;

/** 
 * Adapted from the creation simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * 
 * Turtles who create other kind of turtle during simulation and die after a countdown  

@author Fabien MICHEL
@version 1.1 6/12/1999 */


@SuppressWarnings("serial")
public class Creator extends Turtle 
{
	public int life=50;
	ArrayList<Turtle> createdTurtles = new ArrayList<Turtle>();
	
	
	public Creator()
	{
		super("ride");
	}

	public void setup()  { playRole("creator"); }

	/**these behaviors have no means, just a test*/

	public String ride()
	{
		fd(1);
		if (getPatchColor()==Color.white)
		{
			if (Math.random()<0.98) {
				Turtle w = new Walker();
				createTurtle(w);
				//createdTurtles.add(w);
			}
			else
				if (Math.random() < 0.92)
					for (int i = 0; i < 50; i++){
						Turtle o = new Ovni();
						createTurtle(o);
						//createdTurtles.add(o);
					}
				else
					launchGravity();
			life--;
			setPatchColor(Color.black);
			return("erase");
		}
		else
		{
			setPatchColor(Color.white);
			return("ride");
		}
	}

	public String erase()
	{
		if (life < 0) die();	//the turtle dies

		turnLeft(Math.random()*50);
		turnRight(Math.random()*50);
		fd(1);
		if (getPatchColor()==Color.white)
		{
			setColor(Color.lightGray);
			setPatchColor(Color.black);
			return("erase");
		}
		else
		{
			setColor(Color.cyan);
			return("ride");
		}
	}

	/**launch turtles of the gravity simulation*/

	public void launchGravity()
	{
		Turtle[] ts = new Turtle[1];
		Turtle t1 = new BlackHole();
		//createdTurtles.add(t1);
		ts[0] = t1;
		createTurtle(t1);
		for (int i = 0; i < 30; i++){
			Turtle s = new Star((int)Math.random()*15);
			createTurtle(s);
			//createdTurtles.add(s);
		}
			
	}

	
	
	
	
	@Override
	public void end() {
		super.end();
//		for (Iterator<Turtle> iterator = createdTurtles.iterator(); iterator.hasNext();) {
//			Turtle t = iterator.next();
//			killAgent(t);
//		}
	}
}

