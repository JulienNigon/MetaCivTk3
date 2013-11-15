package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.List;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.*;



import civilisation.individu.Humain;
import civilisation.world.World;
import civilisation.world.WorldViewer;

/** 
 * Le JPanel contenant les options pour l'affichage
 * @author DTEAM
 * @version 1.0 - 2/2013
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
		totalMem.setText("Memoire totale : " + Runtime.getRuntime().totalMemory());
		usedMem.setText("Memoire utilisée : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
	}
	


	
}




