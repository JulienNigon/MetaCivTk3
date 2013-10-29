package civilisation.inspecteur.simulation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.Civilisation;
import civilisation.Configuration;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerCogniton;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensConditionnels;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensInfluence;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction.Option_BeforeAfter;
import civilisation.inventaire.Objet;
import civilisation.world.Terrain;

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
