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
package edu.turtlekit2.demos.diffusion;

import edu.turtlekit2.kernel.agents.Observer;

/**
 * Adapted from the diffusion simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * This agent just initializes the center patch flavor and flavor2 (PatchVariable Objects)
    @author Fabien MICHEL
    @version 1.2 31/01/2000 */

@SuppressWarnings("serial")
public class GridInitializer extends Observer
{
	
	public void setup()
	{
		System.err.println(probe1);
		patchGrid[(int) (envWidth/2)][(int) (envHeight/2)].setPatchVariable("flavor",getAttrib().getDouble("value"));
		patchGrid[(int) (envWidth/2)][(int) (envHeight/2)].setPatchVariable("flavor2",getAttrib().getDouble("value2"));
	}	  



}







