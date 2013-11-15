package civilisation.inspecteur.simulation.civilisations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanelCivilisation implements ActionListener{

	PanelCivilisations p;
	
	public ActionPanelCivilisation(PanelCivilisations p) {
		this.p = p;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		p.performChange();
	}

	
	
}
