package civilisation.inspecteur.simulation.environnement;

import civilisation.world.Terrain;


/** 
 * Classe pour gerer les pseudos patchs pour la gnration de l'environnement
 * @version MetaCiv 1.0
*/

public class PseudoPatch {

	Terrain terrain;
	
	public PseudoPatch(Terrain terrain){
		this.terrain = terrain;
	}

	
	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	
	
}
