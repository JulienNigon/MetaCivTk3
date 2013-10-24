package civilisation.inspecteur.simulation.environnement;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import civilisation.inspecteur.simulation.dialogues.DialogueChoisirCouleurTerrain;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerCogniton;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerPlan;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerTerrain;
import civilisation.inspecteur.simulation.dialogues.DialoguePlacerCivilisation;
import civilisation.world.Terrain;

public class ActionsMenuGCarte implements ActionListener{

	GCarte carte;
	int index;
	MouseEvent e;
	
	public ActionsMenuGCarte(GCarte carte, int i, MouseEvent e)
	{
		this.carte = carte;
		index = i;
		this.e = e;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			DialoguePlacerCivilisation d = new DialoguePlacerCivilisation((Frame) carte.getTopLevelAncestor() , true, carte, carte.getTargetPseudoPatch(this.e));
			d.setVisible(true);
		}

		
	}

}
