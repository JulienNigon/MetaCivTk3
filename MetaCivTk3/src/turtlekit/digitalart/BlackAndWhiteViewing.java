package turtlekit.digitalart;

import java.awt.Color;
import java.awt.Graphics;

import turtlekit.kernel.Patch;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.viewer.PheromoneViewer;

public class BlackAndWhiteViewing extends PheromoneViewer {

	@Override
	protected void activate() {
		super.activate();
		getCurrentEnvironment().getPheromone("blackNwhite", 0, 33);
		setSelectedPheromone("blackNwhite");
		getSelectedPheromone().incValue(getWidth()/2, getHeight()/2, 100000000);
	}
	
	@Override
	public void paintPatch(Graphics g, Patch p, int x, int y, int index) {
		final int a = ((int) getSelectedPheromone().get(index))%256;
		g.setColor(new Color(a,a,a));
		g.fillRect(x,y,cellSize,cellSize);
	}

	public static void main(String[] args) {
		executeThisViewer(
				Option.envDimension.toString(),"100,100"
				,Option.startSimu.toString()
				);
	}
}
