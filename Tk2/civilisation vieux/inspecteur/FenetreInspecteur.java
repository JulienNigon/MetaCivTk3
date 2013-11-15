package civilisation.inspecteur;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/** 
 * Fenêtre contenat l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class FenetreInspecteur extends JFrame{

	JTabbedPane contentPane;
	  


	  public FenetreInspecteur(String nomFenetre){
		    super(nomFenetre);
		    this.setSize(350,550);


		    contentPane = new JTabbedPane();
		    contentPane.addTab("Agents", new PanelInspecteur());
		    contentPane.addTab("Communautes", new PanelInspecteurCommunaute());
		    contentPane.addTab("Options", new PanelOptions());
		    this.setTitle(nomFenetre);
		    this.setVisible(true);
		    this.setContentPane(contentPane);
		    }
	  
	  
	  public void actualiser(MouseEvent e)
	  {
		 // contentPane.actualiser();
	  }
		    
}
