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

import java.awt.Graphics;

import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.kernel.environment.Patch;

/**
 * Adapted from the creation simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * A viewer that override the paintTurtle method to obtain a different visual effect,
   @author Fabien MICHEL
  @version 1.2 4/1/2000 
 */

@SuppressWarnings("serial")
public class SpecialViewer extends Viewer 
{
	public void paintTurtle(Graphics g,Turtle t,int x,int y,int cellSize)
	{
		g.setColor(t.getColor());
		g.fillOval(x,y,cellSize*3,cellSize*3);
	}

	public void paintPatch(Graphics g,Patch p,int x,int y,int cellSize)
	{
		g.setColor(p.getColor());
		g.fillRect(x,y,cellSize*3,cellSize*3);
	}
}









































