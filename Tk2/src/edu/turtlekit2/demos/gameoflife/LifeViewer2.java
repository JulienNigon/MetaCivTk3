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
package edu.turtlekit2.demos.gameoflife;

import java.awt.Color;
import java.awt.Graphics;

import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.kernel.environment.Patch;


/**
 * Adapted from the game of life simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * LifeViewer overrides the paintPatch method in order to adjust the onscreen color of a patch

   @author Fabien MICHEL
    @version 1.1 23/02/2001 */

@SuppressWarnings("serial")
public class LifeViewer2 extends Viewer 
{
	public void paintPatch(Graphics g, Patch p,int x,int y,int CellSize)
	{
		if( p.smell("lifeValue2") == 0)
			g.setColor(Color.black);
		else
			g.setColor(Color.yellow);
		g.fillRect(x,y,CellSize,CellSize);
	}
}
