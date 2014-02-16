package civilisation.inspecteur.tableauDeBord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * Gère l'interaction avec le panel d'informations
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionPanelInfos implements ActionListener{

	PanelInfos p;
	int index;
	
	public ActionPanelInfos(PanelInfos p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) 
		{
			p.actualiserRatios();
		}
		else if (index == 1) 
		{
		}
		
	}




}
