package civilisation.inspecteur.simulation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction.Option_BeforeAfter;

public class ActionsToolBarArbreActions implements ActionListener{

	PanelArbreActions p;
	int index;
	
	public ActionsToolBarArbreActions(PanelArbreActions p, int i)
	{
		this.p = p;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			DialogueAjouterAction d;
			d = new DialogueAjouterAction((Frame) p.getTopLevelAncestor() , true , p , Option_BeforeAfter.FIRST);
			d.setVisible(true);
		}
	}

}
