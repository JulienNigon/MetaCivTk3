package turtlekit.toys;

import java.awt.Color;

import turtlekit.kernel.Turtle;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.pheromone.Pheromone;
import turtlekit.viewer.PheromoneViewer;

public class Runaway extends Turtle {
	
	private Pheromone pheromone;

	public Runaway() {
		super("runaway");
	}
	
	@Override
	protected void activate() {
		super.activate();
		home();
		for (int i = 0; i < 2; i++) {
			launchAgent(new PheroEmmiter());
		}
		randomHeading();
		randomLocation();
		setColor(Color.BLUE);
		pheromone = getEnvironment().getPheromone("test",30,30);
	}
	
	
	@SuppressWarnings("unused")
	private String runaway() {
		setHeading(getPheroMinDirection(pheromone));
		wiggle(30);
		return "runaway";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeThisTurtle(30
				,Option.viewers.toString(),PheromoneViewer.class.getName()
				,Option.envHeight.toString(),"200"
				,Option.envWidth.toString(),"200"
//				,Option.noCuda.toString()
				);
	}

}
