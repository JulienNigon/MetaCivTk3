package civilisation.inspecteur.arbreCognitif;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/** 
 * Gère l'interaction avec la fenêtre de des cognitons graphiques
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionPanelCognitonsGraphiques implements ActionListener{

	PanelCognitonsGraphiques p;
	int index;
	
	public ActionPanelCognitonsGraphiques(PanelCognitonsGraphiques p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) 
		{
			p.incrementerAgentID();
		}
		else if (index == 1) 
		{
			p.decrementerAgentID();
		}
		
	}



}
