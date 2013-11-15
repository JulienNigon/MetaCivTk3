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
import java.util.ArrayList;
import java.util.Iterator;

import edu.turtlekit2.kernel.agents.Turtle;

/** 
 * Adapted from the soccer simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * blue player
  @author Fabien MICHEL
  @version 1.2 6/12/1999 */


@SuppressWarnings("serial")
public class BluePlayer extends Player 
{
	public BluePlayer()
	{
		super();
		teamNearest=new Player[3];
		xGoal=25;
		isBlue = true;
	}

	protected boolean takeBall()
	{
		if (distFromBall() < 1.2)
		{
			if (ball.holden && Math.random()<.25)	return false;
			ball.takenBy(this);
			ballHolder = true;
			setColor(Color.cyan);
			return true;
		}
		return false;
	}

	
	protected void computeTeamateAndNearest()
	{
		double d = 1000;
		ArrayList<Turtle> teamMatesList = getTurtlesListWithRole("BluePlayer");
		for(int cpt=0;cpt<3;cpt++){
			for (int i = cpt; i < teamMatesList.size(); i++) {
				Player player = (Player) teamMatesList.get(i);
				if(player != null && player != this && distance(player.xcor(),player.ycor()) < d){
					d = distance(player.xcor(),player.ycor()) ;
					teamNearest[cpt]=player;
					teamMatesList.add(cpt, player);
					teamMatesList.remove(i);
					break;
				}
			}
			d=1000;
		}
		d=1000;
		
		ArrayList<Turtle> othersList = getTurtlesListWithRole("RedPlayer");
		for (Iterator<Turtle> iterator = othersList.iterator(); iterator.hasNext();) {
			Player p = (Player) iterator.next();
			if (p !=null && distance(p.xcor(),p.ycor()) < d)
			{
				d = distance(p.xcor(),p.ycor()) ;
				otherNearest = p;
			}
		}			
	}

	public String play()
	{
		computeNearestFromBall();
		computeDistFromBall();
		computeTeamateAndNearest();
		if (whoNearest==this || (!whoNearest.isBlue && nearerBall && distFB < 15)  )
		{
			goForBall();
			move();
			if (takeBall()) return "dribble";
			return "play";
		}

		if (!whoNearest.isBlue || distance(xInit,yInit) > 50)
		{
			if (Math.random() < .7) repositioner();
			move();
			return "play";
		}

		if (Math.random() > .2)  goForGoal();
		else randomHeading();
		move();
		return "play";
	}

	@Override
	public void activate() {
		super.activate();
		xInit=xcor();
		yInit=ycor();
		ball = (Ball)getTurtlesListWithRole("Ball").get(0);
		moveTo(xInit,yInit);
		setColor(Color.blue);
		playRole("BluePlayer");
	}
}















