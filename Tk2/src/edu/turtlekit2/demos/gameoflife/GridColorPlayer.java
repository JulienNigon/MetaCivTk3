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

import edu.turtlekit2.kernel.agents.Observer;
import edu.turtlekit2.kernel.environment.Patch;


/** 
 * Adapted from the game of life simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * This agent just play the game of life

    @author Fabien MICHEL
    @version 1.1 23/02/2001 */

@SuppressWarnings("serial")
public class GridColorPlayer extends Observer
{
	Boolean[][] caBuffer;

	public void setup() {
		caBuffer = new Boolean[envWidth][envHeight];
	}
	
	public void watch()	{
		//Loop on every patches
		for(int i=0;i<envWidth;i++){
			for(int j=0;j<envHeight;j++){
				caBuffer[i][j]= patchGrid[i][j].getColor().equals(Color.RED);
				//Getting the number of living neighbors
				int aliveNeighbors=0;
				Patch[] neighbors = patchGrid[i][j].neighbors;
				for(int k=0;k<neighbors.length;k++){
					aliveNeighbors += neighbors[k].getColor().equals(Color.RED) ? 1 : 0;
				}
				//Execute the Game of Life on the buffer 
				if ( 	caBuffer[i][j] && 
						(aliveNeighbors < 2 || aliveNeighbors >3)){
					caBuffer[i][j] = false;
				}
				else if (aliveNeighbors == 3){
					caBuffer[i][j] = true;
				}
			}
		}
		
		//Copy the buffer to the environment grid
		for(int i=0;i<envWidth;i++){
			for(int j=0;j<envHeight;j++){
				if(caBuffer[i][j]) patchGrid[i][j].setColor(Color.RED);
				else patchGrid[i][j].setColor(Color.BLACK);
			}
		}
	}


}



