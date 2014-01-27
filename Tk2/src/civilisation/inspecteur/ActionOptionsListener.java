package civilisation.inspecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * Gère l'interaction avec la fenêtre des options
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionOptionsListener implements ActionListener{

	PanelOptions p;
	int index;
	
	public ActionOptionsListener(PanelOptions p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) 
		{
			p.modifierAffichagePlans();
		}
		else if (index == 1) 
		{
			p.modifierAffichageFrontieres();
		}
		else if (index == 2) 
		{
			p.showPheroMap();
		}
		else if (index == 3) 
		{
			p.showGroupAndRole();
		}
	}



}
