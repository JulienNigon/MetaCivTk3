package civilisation.inspecteur.simulation.environnement;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.inspecteur.simulation.dialogues.DialogueChoisirCouleurTerrain;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerTerrain;
import civilisation.world.Terrain;

public class ActionsMenuTerrain implements ActionListener{

	PanelTerrains p;
	int index;
	
	public ActionsMenuTerrain(PanelTerrains p, int i)
	{
		this.p = p;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			DialogueEditerTerrain d = new DialogueEditerTerrain((Frame) p.getTopLevelAncestor() , true , (Terrain)( p.getListeTerrains().getSelectedValue()));
			d.show();
		}
		else if (index == 1){
			DialogueChoisirCouleurTerrain d = new DialogueChoisirCouleurTerrain((Frame) p.getTopLevelAncestor() , true , (Terrain)( p.getListeTerrains().getSelectedValue()));
			d.show();
		}
		
	}

}
