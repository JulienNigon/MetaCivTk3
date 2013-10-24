package civilisation.inspecteur.simulation.objets;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.Configuration;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerCogniton;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensConditionnels;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensInfluence;
import civilisation.inventaire.Objet;
import civilisation.world.Terrain;

public class ActionsToolBarListeObjets implements ActionListener{

	PanelListeObjets p;
	int index;
	
	public ActionsToolBarListeObjets(PanelListeObjets p, int i)
	{
		this.p = p;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			System.out.println("--New item--");
			Objet o = new Objet("Default_" + Configuration.objets.size());
			Configuration.objets.add(o);
			p.addObjet(o);
			
		}
	}

}
