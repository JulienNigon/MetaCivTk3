package civilisation;

import static turtlekit.kernel.TurtleKit.Option.cuda;
import static turtlekit.kernel.TurtleKit.Option.envDimension;
import static turtlekit.kernel.TurtleKit.Option.envHeight;
import turtlekit.kernel.TurtleKit.Option;
import static turtlekit.kernel.TurtleKit.Option.envDimension;
import static turtlekit.kernel.TurtleKit.Option.environment;
import static turtlekit.kernel.TurtleKit.Option.scheduler;
import static turtlekit.kernel.TurtleKit.Option.startSimu;
import static turtlekit.kernel.TurtleKit.Option.turtles;
import static turtlekit.kernel.TurtleKit.Option.viewers;

import java.io.File;

import javax.swing.JOptionPane;

import civilisation.inspecteur.viewer.ViewerAgent;
import civilisation.inspecteur.viewer.ViewerInspecteur;
import civilisation.inspecteur.viewer.ViewerModificationSimulation;
import civilisation.inspecteur.viewer.ViewerOptions;
import civilisation.inspecteur.viewer.ViewerParametres;
import civilisation.inspecteur.viewer.ViewerPerformances;
import civilisation.inspecteur.viewer.ViewerTabbed;
import civilisation.inspecteur.viewer.ViewerTableauDeBord;
import civilisation.world.World;
import civilisation.world.WorldViewer;
import turtlekit.kernel.TKLauncher;
import turtlekit.mle.MLEEnvironment;
import turtlekit.viewer.PheromoneViewer;
import turtlekit.viewer.TKDefaultViewer;

public class CivLauncher extends TKLauncher {
	
	@Override
	protected void activate() {
		setMadkitProperty(cuda, "false");
		setMadkitProperty("GPU_gradients", "true");
		super.activate();
	}

	
	@Override
	protected void createSimulationInstance() {
		
		

		Initialiseur.readParameters(); //Load minimum informations about the simulation
		int x = Integer.parseInt(Initialiseur.getChamp("Largeur", new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);
		int	y = Integer.parseInt(Initialiseur.getChamp("Hauteur", new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);

		System.out.println("X : " + x + "  Y : " + y);
		setMadkitProperty(envDimension, x+","+y);
		initProperties();
		setMadkitProperty(startSimu, "false");
		setMadkitProperty(viewers, WorldViewer.class.getName() /*+","+ TKDefaultViewer.class.getName()*/);
		setMadkitProperty(environment, World.class.getName());
		setMadkitProperty(turtles, TurtleGenerator.class.getName()+","+1);
		System.out.println(getMadkitProperty(envDimension));
		System.out.println(getHeight() + " " + getWidth());
		//setMadkitProperty(Option.noWrap, "true");

		super.createSimulationInstance();

		//this.launchAgent(new ViewerModificationSimulation());
		//this.launchAgent(new ViewerOptions());
		//this.launchAgent(new ViewerPerformances());
		//this.launchAgent(new ViewerTableauDeBord());
		this.launchAgent(new ViewerTabbed());
	}
	
	public static void main(String[] args) {
		executeThisLauncher("--popDensity","0");
	}

}
