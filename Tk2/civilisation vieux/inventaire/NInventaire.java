package civilisation.inventaire;

import java.util.ArrayList;
import java.util.HashMap;

import civilisation.individu.cognitons.NCogniton;

public class NInventaire {

	HashMap<Objet, Integer> listeObjets;

	public NInventaire(){
		listeObjets = new HashMap<Objet, Integer>();
	}
	
	public void addObjets(Objet o , int i){
		if (!listeObjets.containsKey(o)){
			listeObjets.put(o, i);
		}
		else{
		//	listeObjets.get(o) = i;
		}
	}
	
	
	
}
