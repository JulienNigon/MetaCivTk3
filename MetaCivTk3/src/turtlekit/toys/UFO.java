package turtlekit.toys;

import java.awt.Color;
import java.util.logging.Level;

import turtlekit.kernel.Turtle;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.pheromone.Pheromone;
import turtlekit.viewer.PheromoneViewer;

public class UFO extends Turtle {
	
	private Pheromone pheromone;
	private Pheromone pheromone2;

	public UFO() {
		super("fly");
	}
	
	@Override
	protected void activate() {
		super.activate();
		home();
		randomHeading();
		setColor(new Color((int) (Math.random() * 256),(int) (Math.random() * 256), (int) (Math.random() * 256)));
		pheromone = getEnvironment().getPheromone("test",30,30);
		pheromone2 = getEnvironment().getPheromone("other",30,60);
	}
	
	
	private String fly() {
		turnRight(2);
		fd(1);
		if (Math.random() < .5) {
			pheromone.incValue(xcor(), ycor(), 100000);
		}
		else{
			pheromone2.incValue(xcor(), ycor(), 100000);
		}
		return "fly";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeThisTurtle(1000
				,Option.viewers.toString(),PheromoneViewer.class.getName()
				,Option.envHeight.toString(),"100"
				,Option.envWidth.toString(),"100"
				,Option.cuda.toString()
				);
	}

}
