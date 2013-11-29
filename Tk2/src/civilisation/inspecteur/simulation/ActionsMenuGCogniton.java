package civilisation.inspecteur.simulation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.inspecteur.simulation.dialogues.DialogEditTriggeringAttributes;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerCogniton;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensConditionnels;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensInfluence;

public class ActionsMenuGCogniton implements ActionListener{

	GCogniton gc;
	int index;
	
	public ActionsMenuGCogniton(GCogniton gc, int i)
	{
		this.gc = gc;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			DialogueEditerCogniton d = new DialogueEditerCogniton((Frame) gc.getTopLevelAncestor() , true , gc);
			d.setVisible(true);
		}
		else if (index == 1){
			DialogueEditerLiensInfluence d = new DialogueEditerLiensInfluence((Frame) gc.getTopLevelAncestor() , true , gc);
			d.setVisible(true);
		}
		else if (index == 2){
			DialogueEditerLiensConditionnels d = new DialogueEditerLiensConditionnels((Frame) gc.getTopLevelAncestor() , true , gc);
			d.setVisible(true);
		}
		else if (index == 3){
			DialogEditTriggeringAttributes d = new DialogEditTriggeringAttributes((Frame) gc.getTopLevelAncestor() , true , gc);
			d.setVisible(true);
		}
	}

}
