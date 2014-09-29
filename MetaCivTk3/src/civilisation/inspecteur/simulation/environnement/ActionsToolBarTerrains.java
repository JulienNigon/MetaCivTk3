package civilisation.inspecteur.simulation.environnement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import civilisation.Configuration;
import civilisation.world.Terrain;

public class ActionsToolBarTerrains implements ActionListener{

	PanelTerrains p;
	int index;
	
	public ActionsToolBarTerrains(PanelTerrains p, int i)
	{
		this.p = p;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			Terrain t = new Terrain("Default_name_"+Configuration.terrains.size());
			Configuration.terrains.add(t);
			p.addTerrain(t);
			
		}
	}

}
