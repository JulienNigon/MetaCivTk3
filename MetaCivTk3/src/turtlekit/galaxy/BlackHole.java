/*
 * TurtleKit - An Artificial Life Simulation Platform
 * Copyright (C) 2000-2013 Fabien Michel
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

package turtlekit.galaxy;

import java.awt.Color;

import turtlekit.kernel.Turtle;

/**
 *       Galaxy center
 */

@SuppressWarnings("serial")
public class BlackHole extends Turtle {
	
	@Override
	protected void activate() {
		getLogger().createLogFile();
		super.activate();
		playRole("black hole");
		randomHeading();
		randomLocation();
		setColor(Color.cyan);
		setNextAction("move");
	}
	
	/** the only behavior of what we shamelessly call a BlackHole */
	public String move() {
		if (getCurrentBehaviorCount() == 2) {
			wiggle(5);
			setCurrentBehaviorCount(0);
		}
		return "move";
	}
}
