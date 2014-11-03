package civilisation.inspecteur.simulation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.inspecteur.simulation.dialogues.DialogueEditerPlan;

public class ActionsMenuGPlan implements ActionListener{

	GPlan gp;
	int index;
	
	public ActionsMenuGPlan(GPlan gp, int i)
	{
		this.gp = gp;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			DialogueEditerPlan d = new DialogueEditerPlan((Frame) gp.getTopLevelAncestor() , true , gp);
			d.show();
		}
		
	}

}
