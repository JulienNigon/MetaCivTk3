package turtlekit.toys;

import java.awt.Color;
import java.util.logging.Level;

import turtlekit.kernel.Turtle;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.pheromone.Pheromone;
import turtlekit.viewer.PheromoneViewer;

public class PheroEmmiter extends Turtle {
	
	private Pheromone pheromone;

	@Override
	protected void activate() {
		super.activate();
		setNextAction("fly");
		home();
		randomHeading();
		setColor(new Color((int) (Math.random() * 256),(int) (Math.random() * 256), (int) (Math.random() * 256)));
		pheromone = getEnvironment().getPheromone("test",30,30);
	}
	
	
	private String fly() {
		wiggle();
		pheromone.incValue(xcor(), ycor(), 100000);
		return "fly";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeThisTurtle(10
				,Option.viewers.toString(),PheromoneViewer.class.getName()
//				,Option.envHeight.toString(),"100"
//				,Option.envWidth.toString(),"100"
				,Option.cuda.toString()
				);
	}

}
