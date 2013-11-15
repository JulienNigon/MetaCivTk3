package civilisation.inspecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * Gère l'interaction avec la fenêtre de réglage des valeurs des cognitons
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionParametresListener implements ActionListener{

	PanelParametres p;
	int index;
	
	public ActionParametresListener(PanelParametres p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			p.modifierParametre(index);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}



}
