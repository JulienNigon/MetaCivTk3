package civilisation.inspecteur.simulation.attributes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanelAttributes implements ActionListener{

	PanelAttributes p;
	
	public ActionPanelAttributes(PanelAttributes p) {
		this.p = p;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Perf? : " + p.isSetuping);
		System.out.println(e.getSource().toString());
		if (!p.isSetuping) {
			p.performChange();
		}
	}

	
	
}
