package turtlekit.mle;

import java.io.FileWriter;
import java.io.IOException;

import jcuda.utils.Timer;
import madkit.simulation.activator.GenericBehaviorActivator;
import turtlekit.kernel.TKEnvironment;
import turtlekit.kernel.TKScheduler;
import turtlekit.kernel.TurtleActivator;
import turtlekit.kernel.TurtleKit.Option;

public class MLEScheduler extends TKScheduler {
	
	@Override
	protected void activate() {
		super.activate();
		launchStepPerSecondMeter();
	}

	
	@Override
	public void doSimulationStep() {
//			Timer.startTimer(this);
			
			Timer.startTimer(getTurtleActivator());
			getTurtleActivator().execute();
			Timer.stopTimer(getTurtleActivator());

			Timer.startTimer(getEnvironmentUpdateActivator());
			getEnvironmentUpdateActivator().execute();
			Timer.stopTimer(getEnvironmentUpdateActivator());
			
//			Timer.stopTimer(this);
			
			getViewerActivator().execute();
			setGVT(getGVT() + 1);
	}

	@Override
	protected void end() {
		super.end();
		final String csvFile = getMadkitProperty("cvs.file");
		if (csvFile != null) {
			final String envSize = getMadkitProperty(Option.envHeight.name());
			int size = Integer.parseInt(envSize);
			size = size * size;
			String results = envSize;
			results += ";" + Timer.getAverageTimerValue(this);
			results += ";" + Timer.getAverageTimerValue(getTurtleActivator());
			results += ";"
					+ Timer.getAverageTimerValue(getEnvironmentUpdateActivator());
			results += ";" + (getTurtleActivator().size() * 100 / size) + "%";
			results += ";" + getTurtleActivator().size() + "\n";
			try (FileWriter fw = new FileWriter(csvFile, true)) {
				System.err.println(results);
				fw.write(results);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("average agents"+ (Timer.getTimerValue(getTurtleActivator()) / 1000000 / getGVT())	);
		//		getLogger().createLogFile();
		logger.info(getMadkitProperty(Option.envDimension.name()));
		logger.info("nb agents : "+getTurtleActivator().size());
		logger.info(Timer.createPrettyString());
	}

}
