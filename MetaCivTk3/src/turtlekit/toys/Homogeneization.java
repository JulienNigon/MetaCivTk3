package turtlekit.toys;

import java.awt.Color;
import java.util.logging.Level;

import turtlekit.kernel.Turtle;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.pheromone.Pheromone;
import turtlekit.viewer.PheromoneViewer;

public class Homogeneization extends Turtle {
	
	private Pheromone pheromone;

	public Homogeneization() {
		super("fly");
	}
	
	@Override
	protected void activate() {
		super.activate();
		home();
		randomHeading();
		randomLocation();
		setColor(new Color((int) (Math.random() * 256),(int) (Math.random() * 256), (int) (Math.random() * 256)));
		pheromone = getEnvironment().getPheromone("test",1,7);
	}
	
	
	private String fly() {
		setHeading(pheromone.getMinDirection(xcor(), ycor()));
		wiggle();
		pheromone.incValue(xcor(), ycor(), 100000);
		return "fly";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeThisTurtle(2
				,Option.viewers.toString(),PheromoneViewer.class.getName()
				,Option.envHeight.toString(),"100"
				,Option.envWidth.toString(),"100"
//				,Option.cuda.toString()
				);
	}

}
