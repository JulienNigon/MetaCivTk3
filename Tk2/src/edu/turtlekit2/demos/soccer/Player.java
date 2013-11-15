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
import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Adapted from the soccer simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * the abstract class that defines the basic behaviors of all turtles who are soccer players
  @author Fabien MICHEL
  @version 1.2 6/12/1999 */



public abstract class Player extends Turtle 
{
	private static final long serialVersionUID = 1L;
	boolean ballHolder = false;
	int xInit,yInit,shootPower,xGoal,yGoal;
	Ball ball;
	boolean nearerBall = false;
	double distFB=60;
	Player otherNearest,whoNearest;
	Player[] teamNearest;
	Boolean isBlue;


	public void init(){}
	@Override
	public void activate() {
		super.activate();
		playRole("player");
	}

	public Player()
	{
		super("play");
		shootPower=9;
		ballHolder=false;
		teamNearest=new Player[3];
		yGoal=75;
	}

	protected void move()
	{
		for (int i = 0;i<4;i++)
			if (countTurtlesAt(dx(),dy())>0 || getPatchColorAt(dx(),dy()) == Color.black)
			{
				if (Math.random() > .5) turnRight(Math.random()*90);
				else turnLeft(Math.random()*90);
			}
		if (countTurtlesAt(dx(),dy())==0 && getPatchColorAt(dx(),dy()) != Color.black)
			fd(1);
	}

	protected double distFromBall() {return distance(ball.xcor(),ball.ycor());}

	protected void computeDistFromBall(){
		double d = distFromBall();
		if (d < distFB) nearerBall = true;
		else nearerBall = false;
		distFB=d;
	}

	protected void goForGoal()
	{
		setHeading(towards(xGoal,ycor()));
		if (Math.random() <.5) turnLeft(Math.random()*30);
		else turnRight(Math.random()*30);
	}

	protected void goForBall()
	{
		double ballHeading = ball.getHeading();
		if (ball.speed < 1 || distFB < 5)
		{
			setHeading(towards(ball.xcor(),ball.ycor()));
		}
		else 
		{
			double anticip=distFB;
			if ( (ballHeading> 337.5 && ballHeading< 360) || (ballHeading> 0 && ballHeading< 22.5) )
				setHeading(towards(ball.xcor()+anticip,ball.ycor()));
			else if (ballHeading> 22.5 && ballHeading< 67.5) setHeading(towards(ball.xcor()+anticip, ball.ycor() + anticip));
			else if (ballHeading> 67.5 && ballHeading< 112.5) setHeading(towards(ball.xcor() ,ball.ycor()+ anticip ));
			else if (ballHeading> 112.5 && ballHeading< 157.5) setHeading(towards(ball.xcor() - anticip,ball.ycor() + anticip));
			else if (ballHeading> 157.5 && ballHeading< 202.5) setHeading(towards(ball.xcor() - anticip,ball.ycor()));
			else if (ballHeading> 202.5 && ballHeading< 247.5) setHeading(towards(ball.xcor() - anticip,ball.ycor() - anticip));
			else if (ballHeading> 247.5 && ballHeading< 292.5) setHeading(towards(ball.xcor(),ball.ycor() - anticip));
			else if (ballHeading> 292.5 && ballHeading< 337.5) setHeading(towards(ball.xcor() + anticip,ball.ycor() - anticip));
		}
	}

	protected void repositioner(){setHeading(towards(xInit+Math.random()*30-15,yInit+Math.random()*30-15));}


	protected void computeNearestFromBall()
	{
		double d = 1000;

		Turtle[] turtles = getTurtlesWithRole("player");
		for (int i = 0; i < turtles.length; i++) {
			Player p  = (Player) turtles[i]; 
			if (p.distFromBall() < d)
			{
				whoNearest=p;
				d=p.distFromBall();
			}
		}
	}

	abstract void computeTeamateAndNearest();

	void shoot(int x,int y,int power)
	{
		if (isBlue) setColor(Color.blue);
		else setColor(Color.red);
		ballHolder=false;
		ball.holden=false;
		ball.holder=null;
		ball.speed = (int) (Math.random()*power);
		if (ball.speed < 5) ball.speed=5;
		if (ball.speed > 8) ball.speed=8;
		if (Math.random() <.5) ball.setHeading(towards(x,y)+Math.random()*15);
		else ball.setHeading(towards(x,y)-Math.random()*15);
	}

	public String dribble()
	{
		if (ballHolder)
		{
			computeDistFromBall();
			computeTeamateAndNearest();
			if (distance(xGoal,yGoal) < 25)
			{
				shoot(xGoal,yGoal,shootPower);
				return "play";
			}
			double o1 = towards(otherNearest.xcor(),otherNearest.ycor());
			if (  getHeading() - o1 > 90 || getHeading() - o1 < -90  || distance(otherNearest.xcor(),otherNearest.ycor()) > 6 )
			{
				goForGoal();
				setHeading(getHeading()+Math.random()*20-10);
				move();
				return "dribble";
			}
			else
				if (Math.random() > 0.7)
				{
					goForGoal();
					move();
					return "dribble";
				}
			int cpt=0;
			double t1;
			for(int i=2;i>=0;i--)
			{
				t1 = towards(teamNearest[i].xcor(),teamNearest[i].ycor()) ;
				if (t1 > 90 && t1 < 270 &&  ( (t1- o1) > 20 || (t1- o1) < -20 ) )
				{
					cpt=i;
					break;
				}
			}
			double d = distance(teamNearest[cpt].xcor(),teamNearest[cpt].ycor());
			if (d<shootPower) shoot(teamNearest[cpt].xcor(),teamNearest[cpt].ycor(),(int)d);
			else shoot(teamNearest[cpt].xcor(),teamNearest[cpt].ycor(),shootPower);
		}
		return "play";
	}
}















