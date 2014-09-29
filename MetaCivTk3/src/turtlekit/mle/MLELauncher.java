package turtlekit.mle;

import static turtlekit.kernel.TurtleKit.Option.cuda;
import static turtlekit.kernel.TurtleKit.Option.envDimension;
import static turtlekit.kernel.TurtleKit.Option.environment;
import static turtlekit.kernel.TurtleKit.Option.scheduler;
import static turtlekit.kernel.TurtleKit.Option.startSimu;
import static turtlekit.kernel.TurtleKit.Option.turtles;
import static turtlekit.kernel.TurtleKit.Option.viewers;

import javax.swing.JOptionPane;

import turtlekit.kernel.TKLauncher;
import turtlekit.viewer.PheromoneViewer;

public class MLELauncher extends TKLauncher {
	
	@Override
	protected void activate() {
		setMadkitProperty(cuda, "false");
		setMadkitProperty("GPU_gradients", "true");
		super.activate();
	}

	
	@Override
	protected void createSimulationInstance() {
		Object[] tab = { "50","100", "256","512","1024","1536","2048" };
		Object size = JOptionPane.showInputDialog(null,
				"environment size", "MLE world size",
				JOptionPane.QUESTION_MESSAGE, null, tab, "256");
		setMadkitProperty(envDimension, size.toString());
		System.out.println(size.toString());
		Object[] tab2 = { "1", "5", "10", "20","30","40","50","60","70","80","90","100","120","140" };
		size = JOptionPane.showInputDialog(null, "MLE population density",
				"population density", JOptionPane.QUESTION_MESSAGE, null, tab2,
				"10");
		setMadkitProperty("popDensity", size.toString());
		initProperties();
		setMadkitProperty(startSimu, "false");
		setMadkitProperty(viewers, PheromoneViewer.class.getName());
		setMadkitProperty(scheduler, MLEScheduler.class.getName());
		setMadkitProperty(environment, MLEEnvironment.class.getName());
		setMadkitProperty(turtles, Particule.class.getName()+","+(int) Math.ceil(Double.parseDouble(getMadkitProperty("popDensity"))*getWidth()*getHeight()/100));
		super.createSimulationInstance();
	}
	
	public static void main(String[] args) {
		executeThisLauncher("--popDensity","100");
	}

}
