package civilisation.individu.cognitons;

import java.util.HashMap;

import civilisation.individu.Esprit;


/**
 * PCogniton is the combination of a Cogniton and a hashmap to store data about the cogniton
 */

public class CCogniton {

	public NCogniton cogniton;
	public HashMap<String , Object> hashmap = new HashMap<String,Object>();
	
	public CCogniton (NCogniton cogniton) {
		this.cogniton = cogniton;
	}

	public NCogniton getCogniton() {
		return cogniton;
	}

	public void setCogniton(NCogniton cogniton) {
		this.cogniton = cogniton;
	}

	public HashMap<String, Object> getHashmap() {
		return hashmap;
	}

	public void setHashmap(HashMap<String, Object> hashmap) {
		this.hashmap = hashmap;
	}
	
	// Some functions from NCogniton for convenience
	public void mettreEnPlace(Esprit e){
		cogniton.mettreEnPlace(e);
	}
	
	public void appliquerPoids(Esprit e){
		cogniton.appliquerPoids(e);
	}
	
}
