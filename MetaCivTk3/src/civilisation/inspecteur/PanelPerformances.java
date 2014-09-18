package civilisation.inspecteur;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/** 
Give some informations about the running simulation.
*/


public class PanelPerformances extends JPanel{

	JToolBar toolBar = new JToolBar();
    private Runtime runtime = Runtime.getRuntime();

	Box boite;

	JLabel osName;
	JLabel osVersion;
	JLabel osArch;
	JLabel totalMem;
	JLabel usedMem;
	
	public PanelPerformances()
	{
		
        boite = new Box(BoxLayout.PAGE_AXIS);

        //boite.add(Box.createVerticalStrut(600));
        
        osName = new JLabel();
        osName.setAlignmentX(Component.LEFT_ALIGNMENT);
        boite.add(osName);
        
        osVersion = new JLabel();
        osVersion.setAlignmentX(Component.LEFT_ALIGNMENT);
        boite.add(osVersion);
        
        osArch = new JLabel();
        osArch.setAlignmentX(Component.LEFT_ALIGNMENT);
        boite.add(osArch);
        
        totalMem = new JLabel();
        totalMem.setAlignmentX(Component.LEFT_ALIGNMENT);
        boite.add(totalMem);
        
        usedMem = new JLabel();
        usedMem.setAlignmentX(Component.LEFT_ALIGNMENT);
        boite.add(usedMem);
        
		this.add(boite);
		
		this.setVisible(true);
	}
	
	
	public void actualiser()
	{
		osName.setText("OS : " + System.getProperty("os.name"));
		osVersion.setText("Version : " + System.getProperty("os.version"));
		osArch.setText("Architecture : " + System.getProperty("os.arch"));
		totalMem.setText("Total memory: " + Runtime.getRuntime().totalMemory());
		usedMem.setText("Used memory : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
	}
	


	
}




