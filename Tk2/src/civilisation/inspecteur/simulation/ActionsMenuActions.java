package civilisation.inspecteur.simulation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import civilisation.individu.plan.action.Action;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction.Option_BeforeAfter;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerAction;

public class ActionsMenuActions implements ActionListener{

	PanelArbreActions pa;
	int index;
	Action selectedAction;
	
	public ActionsMenuActions(PanelArbreActions panelArbreActions, int i, Action selectedAction)
	{
		this.pa = panelArbreActions;
		index = i;
		this.selectedAction = selectedAction;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			DialogueEditerAction d;
			try {
				d = new DialogueEditerAction((Frame) pa.getTopLevelAncestor() , true , pa, selectedAction);
				d.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (index == 1){
			DialogueAjouterAction d;
			d = new DialogueAjouterAction((Frame) pa.getTopLevelAncestor() , true , pa , Option_BeforeAfter.AFTER);
			d.setVisible(true);
		}
		else if (index == 2){
			DialogueAjouterAction d;
			d = new DialogueAjouterAction((Frame) pa.getTopLevelAncestor() , true , pa , Option_BeforeAfter.BEFORE);
			d.setVisible(true);
		}
		else if (index == 3){
			DialogueAjouterAction d;
			d = new DialogueAjouterAction((Frame) pa.getTopLevelAncestor() , true , pa , Option_BeforeAfter.INTERNAL);
			d.setVisible(true);
		}
		else if (index == 4){
			pa.remove(selectedAction);
		}	
		}

}
