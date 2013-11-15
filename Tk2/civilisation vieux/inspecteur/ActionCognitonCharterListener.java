package civilisation.inspecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/** 
 * Gère l'interaction avec la fenêtre du charter de cognitons
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionCognitonCharterListener implements ActionListener{

	CognitonsCharter p;
	int index;
	
	public ActionCognitonCharterListener(CognitonsCharter p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) 
		{
			p.modifierGraphique();
		}

		
	}



}
