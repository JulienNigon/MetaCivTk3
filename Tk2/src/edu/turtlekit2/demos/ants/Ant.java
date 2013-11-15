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
package edu.turtlekit2.demos.ants; 
import java.awt.Color;

import edu.turtlekit2.kernel.agents.Turtle;


@SuppressWarnings("serial")
public class Ant extends Turtle
{
	double quantity = 50;
	
	public Ant()
	{
		super("searchForFood");
		randomHeading();
	}

	
	public String searchForFood()
	{
		double foodQuantity = smell("food");
		if (foodQuantity > 0){
			emit("food", -10);
			turnLeft(180);
			quantity = 50;
			return("returnToNest");
		}
		else{
			if(smell("foodScent") <= 0.1) 
				wiggle();
			else{
				setHeading(getDirectionOfMaxInMyDirection("foodScent"));
				fd(1);
			}
			if(quantity>0) emit("nestScent", quantity--);
			return ("searchForFood");
		}
	}
	

	public String returnToNest()
	{
		if (getPatchColor() == Color.red){
			setHeading(Math.random()*360);
			turnLeft(180);
			quantity=50;
			emit("foodreturned", 10);
			return("searchForFood");
		}
		else
		{
			if(smell("nestScent") <= 0.1 || Math.random()<0.1) 
				wiggle();
			else{
				setHeading(getDirectionOfMaxInMyDirection("nestScent"));
				fd(1);
			}
			if(quantity>0) emit("foodScent", quantity--);
			return("returnToNest");
		}
	}
	
}
