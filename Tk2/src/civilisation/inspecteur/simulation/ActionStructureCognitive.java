package civilisation.inspecteur.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * Gère l'interaction avec la toolBar qui modifie la structure cognitive
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionStructureCognitive implements ActionListener{

	PanelModificationSimulation p;
	int index;
	
	public ActionStructureCognitive(PanelModificationSimulation p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) 
		{
			p.getPanelStructureCognitive().creerCogniton();
		}
		else if (index == 1) 
		{
			p.getPanelStructureCognitive().creerPlan();
		}
		else if (index == 2) 
		{
			p.getPanelStructureCognitive().createCloudCogniton();
		}
		else if (index == 3) 
		{
		//	p.getPanelStructureCognitive().createGroup();
		}
		
	}




}
