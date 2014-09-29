package turtlekit.toys;

import java.awt.Color;

import turtlekit.kernel.Turtle;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.pheromone.Pheromone;
import turtlekit.pvequalsnrt.Gas;
import turtlekit.viewer.PopulationCharter;
import turtlekit.viewer.TKDefaultViewer;

public class Creation extends Turtle {
	
	private Pheromone pheromone;
	private boolean launched = false;
	private Gas photon = null;

	public Creation() {
		super("create");
		setColor(Color.ORANGE);
	}
	
	@Override
	protected void activate() {
		super.activate();
		home();
	}
	
	protected String create(){
		launchAgent(new Creation());
		if(generator.nextFloat() < 0.5){
			launchAgent(new Gas(){
				@Override
				public String go() {
					if(xcor() == 0 || ycor() == 0){
						return null; //die
					}
					return super.go();
				}
				@Override
				protected void activate() {
					super.activate();
					home();
					setColor(Color.WHITE);
				}
			});
		}
		return "fly";
	}
	
	private String fly() {
		wiggle(360);
		if(getCurrentBehaviorCount() > 20000){
			return null;
		}
		return"fly";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeThisTurtle(1
				,Option.viewers.toString(),MyPopulationCharter.class.getName()+";"+TKDefaultViewer.class.getName()
				,Option.envDimension.toString(),"800,800"
				,Option.startSimu.toString()
				);
	}

}

class MyPopulationCharter extends PopulationCharter{
	public MyPopulationCharter() {
		setTimeFrame(1000);
		setMonitorTurtleRole(true);
	}
}
