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

/** 
 * Fenêtre contenat l'inspecteur de communautés
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class FenetreInspecteurCommunaute extends JFrame{

	PanelInspecteurCommunaute contentPane;
	  


	  public FenetreInspecteurCommunaute(String nomFenetre){
		    super(nomFenetre);
		    this.setSize(350,550);


		    contentPane = new PanelInspecteurCommunaute();
		    this.setTitle(nomFenetre);
		    this.setVisible(true);
		    this.setContentPane(contentPane);
		    }
	  
	  
	  public void actualiser(MouseEvent e)
	  {
		 // contentPane.actualiser();
	  }
		    
}
