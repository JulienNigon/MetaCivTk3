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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
		
		
		final Semaphore pathSelected = new Semaphore(1, true);
    	try {
			pathSelected.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
	//	System.out.println(Initialiseur.getChamp("Load_last_model", new File(System.getProperty("user.dir") + "/bin/config"))[0]);
	//	System.out.println(new File(Initialiseur.getChamp("Last_loaded_model_path", new File(System.getProperty("user.dir") + "/bin/config"))[0]));

		if (new File(System.getProperty("user.dir") + "/bin/config").exists() &&
				Initialiseur.getChamp("Load_last_model", new File(System.getProperty("user.dir") + "/bin/config"))[0].equals("true") &&
				new File(Initialiseur.getChamp("Last_loaded_model_path", new File(System.getProperty("user.dir") + "/bin/config"))[0]).exists()) {
			//Config file exist and the user specified to always use latest model
			Configuration.pathToRessources = Initialiseur.getChamp("Last_loaded_model_path", new File(System.getProperty("user.dir") + "/bin/config"))[0];
			pathSelected.release();
		}
		else {

	    	
			 SwingUtilities.invokeLater(new Runnable() {
	             public void run() {

	            //	JPanel pan = new JPanel();
	            //	JButton buttonLoadLastModel = new JButton("Load last model");
	            //	pan.add(buttonLoadLastModel);
	            	
	         	    JFileChooser chooser = new JFileChooser();
	        	    FileNameExtensionFilter filter = new FileNameExtensionFilter("parametres.metaciv","metaciv");
	        	    chooser.setFileFilter(filter);
	        	    chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
	        	 //   chooser.add(pan,BorderLayout.SOUTH);
	        	    int returnVal = chooser.showOpenDialog(null);
	        	    	    
	        	   // chooser.
	        	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	        			File file = chooser.getSelectedFile();
	        			Configuration.pathToRessources = file.getParent();
	        	    } else {
	        	    	Configuration.pathToRessources = System.getProperty("user.dir") + "/civilisation/ressources";
	        	    }
	        	    setField("Last_loaded_model_path",Configuration.pathToRessources,
	        	    		new File(System.getProperty("user.dir") + "/bin/config"),
	        	    		new File(System.getProperty("user.dir") + "/bin/tempConfig"));
	        	    pathSelected.release();
			 }});
		}

	    
		 
		 
		 try {
			pathSelected.acquire();
	//		 System.out.println("Selected path : " + Configuration.pathToRessources);
			 executeThisLauncher("--popDensity","0");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	static public void setField(String field, String newValue,  File f, File temp){
		
		 Scanner scanner;
		 PrintWriter out;
		try {
			scanner = new Scanner(new FileReader(f));
			out = new PrintWriter(new FileWriter(temp));

			 String str = null;
			 while (scanner.hasNextLine()) {
			     str = scanner.nextLine();
			     if(str.split(" : ")[0].equals(field)){
			    	 out.println((field + " : " + newValue));
			     }
			     else {
				     out.println(str);
			     }
			 }
			 out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
		//String name = f.getAbsolutePath();
		f.delete();
		temp.renameTo(f);
	}


}
