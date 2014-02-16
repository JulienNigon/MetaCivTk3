package turtlekit.toys;

import java.awt.Color;
import java.util.Arrays;

import turtlekit.kernel.TKEnvironment;
import turtlekit.kernel.Patch;
import turtlekit.kernel.TurtleKit.Option;

public class GameOfLife extends TKEnvironment{
	
	boolean[] gridBuffer;
	
	@Override
	protected void activate() {
		super.activate();
		final Patch[] patchGrid = getPatchGrid();
		gridBuffer = new boolean[patchGrid.length];
		Arrays.fill(gridBuffer, false);
		int index = 0;
		for (Patch p : patchGrid) {
			if(Math.random() < .4 ){
				p.setColor(Color.RED);
				gridBuffer[index] = true;
			}
			index++;
		}
	}

	@Override
	protected void update() {
		int index = 0;
		for (Patch p : getPatchGrid()) {
			int lifeCounter = 0;
			for(Patch tmp : p.getNeighbors(1,false)){
				if(tmp.getColor() == Color.RED){
					lifeCounter++;
				}
			}
			if(gridBuffer[index] && (lifeCounter < 2 || lifeCounter > 3)){
				gridBuffer[index] = false;
			}
			else if(lifeCounter == 3)
				gridBuffer[index] = true;
			index++;
		}
		index = 0;
		for (Patch p : getPatchGrid()) {
			p.setColor(gridBuffer[index++] ? Color.RED : Color.BLACK);
		}
	}
	
	public static void main(String[] args) {
		executeThisEnvironment(Option.envDimension.toString(),"150,150"
				,Option.startSimu.toString()
				);
	}
}

