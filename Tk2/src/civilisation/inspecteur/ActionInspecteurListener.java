package civilisation.inspecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * Gère l'interaction avec la fenêtre de l'inspecteur
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionInspecteurListener implements ActionListener{

	PanelInspecteur p;
	int index;
	
	public ActionInspecteurListener(PanelInspecteur p, int i)
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
