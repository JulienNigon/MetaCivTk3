package civilisation.individu.cognitons;

import civilisation.Configuration;

public class Culturon extends NCogniton{

	/** 
	 * Cloud cogniton are special cogniton associated to groups instead of humans
	 * @author DTEAM
	 * @version 1.0 - 2/2013
	*/
	
	@Override
	public void creerCognitonLambda(){
		nom = "newCloudCogniton_" + Configuration.cloudCognitons.size(); /*TODO : faire un nom unique*/
		description = "A new cloud cogniton";
		typeDeCogniton = TypeDeCogniton.CLOUD;
	}
	

	
	
}
