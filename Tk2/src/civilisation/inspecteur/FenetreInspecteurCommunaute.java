package civilisation.inspecteur;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;

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
