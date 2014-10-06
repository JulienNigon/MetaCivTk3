package civilisation.individu.cognitons;

import java.util.HashMap;

import civilisation.individu.Esprit;


/**
 * Cogniton is the combination of a Cogniton and a hashmap to store data about the cogniton
 * Only Cogniton are instantiated after initialization. No more TypeCogniton.
 */

public class Cogniton {

	public TypeCogniton cogniton;
	public HashMap<String , Object> hashmap = new HashMap<String,Object>();
	public double weigth = 1.0;
	
	public Cogniton (TypeCogniton cogniton) {
		this.cogniton = cogniton;
	}

	public TypeCogniton getCogniton() {
		return cogniton;
	}

	public void setCogniton(TypeCogniton cogniton) {
		this.cogniton = cogniton;
	}

	public HashMap<String, Object> getHashmap() {
		return hashmap;
	}

	public void setHashmap(HashMap<String, Object> hashmap) {
		this.hashmap = hashmap;
	}
	
	public double getWeigth() {
		return weigth;
	}

	public void setWeigth(double weigth) {
		this.weigth = weigth;
	}
	
	// Some functions from NCogniton for convenience
	public void mettreEnPlace(Esprit e){
		cogniton.mettreEnPlace(e , weigth);
		
	}
	
	public void appliquerPoids(Esprit e){
		cogniton.appliquerPoids(e , weigth);
	}
	
	public String toString () {
		return "C:"+ cogniton.toString();
	}


	
	
}
