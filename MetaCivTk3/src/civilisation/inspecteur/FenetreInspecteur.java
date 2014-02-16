package civilisation.inspecteur;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/** 
 * Fen�tre contenat l'inspecteur
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
