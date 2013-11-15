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
package edu.turtlekit2.demos.soccer;

import java.awt.Color;
import java.awt.Graphics;

import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.agents.Viewer;

/**
 * Adapted from the soccer simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * SoccerViewer changes the turtle display to obtain a tiny player insteed of a rect,
  @author Fabien MICHEL
  @version 1.2 4/1/2000 */

@SuppressWarnings("serial")
public class SoccerViewer extends Viewer 
{
	public SoccerViewer(){
		redrawAll = true;
	}
	
	@Override
	public void paintInfo(Graphics g) {
		redrawAll = true;
		super.paintInfo(g);
	}
	
    public void paintTurtle(Graphics g,Turtle t,int x,int y,int cellSize)
    {
    	super.paintTurtle(g, t, x, y, cellSize);
		if(t.isPlayingRole("Ball")){
			g.setColor(Color.white);
			g.fillOval(x,y,cellSize,cellSize);
		}
		else{
			g.setColor(Color.lightGray);
			g.drawLine(x+1,y,x+2,y);
			g.drawLine(x,y+1,x,y+1);
			g.drawLine(x+3,y+1,x+3,y+1);
			g.setColor(t.getColor());
			g.drawLine(x+1,y+1,x+2,y+1);
			g.drawLine(x+1,y+2,x+2,y+2);
			g.setColor(Color.black);
			g.drawLine(x,y+3,x,y+3);
			g.drawLine(x+3,y+3,x+3,y+3);
		}
	}
}

