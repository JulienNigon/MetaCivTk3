package civilisation.inspecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * Gère l'interaction avec la fenêtre de réglage des paramètres de la simulation
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionParametreToolBarListener implements ActionListener{

	PanelParametres p;
	int index;
	
	public ActionParametreToolBarListener(PanelParametres p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (index == 0){
			p.permuterModifierConf();
		}
		else if (index == 1){
			p.permuterModifierCogni();	
		}
		
	}



}
