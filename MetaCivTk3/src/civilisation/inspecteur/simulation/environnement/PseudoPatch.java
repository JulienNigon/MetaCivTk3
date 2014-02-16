package civilisation.inspecteur.simulation.environnement;

import civilisation.world.Terrain;


/** 
 * Classe pour gerer les pseudos patchs pour la gnration de l'environnement
 * @version MetaCiv 1.0
*/

public class PseudoPatch {

	Terrain terrain;
	int x , y;
	
	public PseudoPatch(Terrain terrain, int x, int y){
		this.terrain = terrain;
		this.x = x;
		this.y = y;
	}

	
	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
