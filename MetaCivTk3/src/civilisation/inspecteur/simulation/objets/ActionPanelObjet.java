package civilisation.inspecteur.simulation.objets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanelObjet implements ActionListener{

	PanelObjets p;
	
	public ActionPanelObjet(PanelObjets p) {
		this.p = p;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		if(e.getActionCommand() == "addEffect")
		{
			p.addEffects();
		}
		else
		{
			
		}
		p.performChange();
	}

	
	
}
