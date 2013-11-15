package edu.turtlekit2.demos.gameoflife;

import java.awt.Color;
import java.util.Random;

import edu.turtlekit2.kernel.environment.Patch;
import edu.turtlekit2.kernel.environment.TurtleEnvironment;

public class GolEnvironment extends TurtleEnvironment {
	private static final long serialVersionUID = 1L;

	Boolean[][] caBuffer;
	Patch[] neighbors;
	
	public GolEnvironment() {
		super();
	}
	
	
	public void fillGrid() {
		caBuffer=new Boolean[x][y];
		Random rand = new Random(123);
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				if(rand.nextFloat()>0.5) grid[i][j].setColor(Color.RED);
				else grid[i][j].setColor(Color.BLACK);
			}
		}
	}
	
	public void executeGameOfLife(){
		//Loop on every patches
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				caBuffer[i][j]= grid[i][j].getColor().equals(Color.RED);
				//Getting the number of living neighbors
				int aliveNeighbors=0;
				Patch[] neighbors = grid[i][j].neighbors;
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
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				if(caBuffer[i][j]) grid[i][j].setColor(Color.RED);
				else grid[i][j].setColor(Color.BLACK);
			}
		}

	}

}
