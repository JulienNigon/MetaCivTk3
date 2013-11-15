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


public class PanelOptions extends JPanel{

	JToolBar toolBar = new JToolBar();
	Box boite;
	JComboBox planAffiche;
	JComboBox frontiereAffichee;
	ArrayList<String> listePlan;
	
	public PanelOptions()
	{
		listePlan =new ArrayList<String>();
		listePlan.add("--Aucun--");
		
		String s;
		File[] files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/plan").listFiles();
		
		for (File file : files) {
		    if (file.isFile()) {
		        s = file.getName();
		        if (s.split("_")[0].equals("REFLEXE") || s.split("_")[0].equals("PLAN") || s.split("_")[0].equals("PLANGR"))
		        {
		        	listePlan.add((s.split("_")[1]).split("\\.")[0]);
		        }
		    }
		}

		
        boite = new Box(BoxLayout.PAGE_AXIS);

        //boite.add(Box.createVerticalStrut(600));
        
        planAffiche = new JComboBox(listePlan.toArray());
        planAffiche.addActionListener(new ActionOptionsListener(this, 0));
        JLabel labelAffichage = new JLabel("Affichage des plans : ");
        frontiereAffichee = new JComboBox();
        frontiereAffichee.addItem("Frontières visibles");
        frontiereAffichee.addItem("Frontières masquées");
        frontiereAffichee.addActionListener(new ActionOptionsListener(this, 1));

        labelAffichage.setAlignmentX(Component.LEFT_ALIGNMENT);
        planAffiche.setAlignmentX(Component.LEFT_ALIGNMENT);
        frontiereAffichee.setAlignmentX(Component.LEFT_ALIGNMENT);

        boite.add(labelAffichage);
		boite.add(planAffiche);
		boite.add(frontiereAffichee);
		this.add(boite);
		
		this.setVisible(true);
	}
	
	
	public void modifierAffichagePlans()
	{
		WorldViewer.getInstance().setPlanVisible((String) planAffiche.getSelectedItem());
	}
	
	public void modifierAffichageFrontieres()
	{
		if (frontiereAffichee.getSelectedItem().equals("Frontières visibles"))
		{
			WorldViewer.getInstance().setFrontieresVisibles(true);
		}
		else
		{
			WorldViewer.getInstance().setFrontieresVisibles(false);
		}

	}
	


	
}




