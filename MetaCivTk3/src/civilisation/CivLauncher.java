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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import civilisation.inspecteur.viewer.ViewerAgent;
import civilisation.inspecteur.viewer.ViewerInspecteur;
import civilisation.inspecteur.viewer.ViewerModificationSimulation;
import civilisation.inspecteur.viewer.ViewerOptions;
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
		
		printStartMessage();
		
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true"); //TODO : this correct a strange behaviour of Swing. Must be improved.

		//SwingUtilities.invokeLater(new Runnable() {
		//		public void run() { 
		
		//synchronized(Configuration.pathToRessources) {
			
		
		//TODO : Correct the 1/2 crash chance at start

		//		}
	
		//}
		//
		//		);

		//Configuration.pathToRessources = System.getProperty("user.dir") + "/civilisation/ressources";

		Initialiseur.readParameters(); //Load minimum informations about the simulation
		int x = Integer.parseInt(Initialiseur.getChamp("Largeur", new File(Configuration.pathToRessources + "/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);
		int	y = Integer.parseInt(Initialiseur.getChamp("Hauteur", new File(Configuration.pathToRessources + "/environnements/"+Configuration.environnementACharger+Configuration.getExtension()))[0]);

		setMadkitProperty(envDimension, x+","+y);
		initProperties();
		setMadkitProperty(startSimu, "false");
		setMadkitProperty(viewers, WorldViewer.class.getName() /*+","+ TKDefaultViewer.class.getName()*/);
		setMadkitProperty(environment, World.class.getName());
		setMadkitProperty(turtles, TurtleGenerator.class.getName()+","+1);
		//setMadkitProperty(Option.noWrap, "true");

		super.createSimulationInstance();

		//this.launchAgent(new ViewerModificationSimulation());
		//this.launchAgent(new ViewerOptions());
		//this.launchAgent(new ViewerPerformances());
		//this.launchAgent(new ViewerTableauDeBord());
		this.launchAgent(new ViewerTabbed());

		}
	
	public static void main(String[] args) {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("parametres.metaciv","metaciv");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			Configuration.pathToRessources = file.getParent();
			System.out.println("Selected path : " + Configuration.pathToRessources);
			executeThisLauncher("--popDensity","0");

	    } else {
	    	Configuration.pathToRessources = System.getProperty("user.dir") + "/civilisation/ressources";
			System.out.println("Selected path : " + Configuration.pathToRessources);
			executeThisLauncher("--popDensity","0");


	    }
	    
	}
	
	public void printStartMessage() {
		//Show version
		System.out.println("\n\t---------------------------------------" +
		"\n\t               MetaCiv" + "\n\t           version: "
				+ Configuration.versionNumber +
				"\n\t      MetaCiv Team (c) 2013-"
				+ Calendar.getInstance().get(Calendar.YEAR) + 
				"\n\t---------------------------------------\n");
	}

}
