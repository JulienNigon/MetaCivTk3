package civilisation.inspecteur.simulation.civilisations;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.Civilisation;
import civilisation.Configuration;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerCogniton;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensConditionnels;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerLiensInfluence;
import civilisation.inventaire.Objet;
import civilisation.world.Terrain;

public class ActionsToolBarListeCivilisations implements ActionListener{

	PanelListeCivilisations p;
	int index;
	
	public ActionsToolBarListeCivilisations(PanelListeCivilisations p, int i)
	{
		this.p = p;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			System.out.println("--New Civilization--");
			Civilisation c = new Civilisation();
			c.setNom("Default_Civ_" + Configuration.civilisations.size());
			Configuration.civilisations.add(c);
			p.addCivilization(c);
			
		}
	}

}
