package civilisation.inspecteur.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import civilisation.Configuration;

/** 
 * G_re l'interaction avec la fen_tre de des cognitons graphiques
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionPanelCognitonsGraphiques implements ActionListener{

	PanelModificationSimulation p;
	int index;
	
	public ActionPanelCognitonsGraphiques(PanelModificationSimulation p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) /*Remplacer par la version actuellement visible dans l'_diteur*/
		{

			
			
			System.out.println("---Enregistrement des param_tres de la simulation---");
			File cible = new File(Configuration.pathToRessources + "");
			
			/*
			 * On met de c_t_ les diff_rents environnments
			 * A ameliorer!
			 */
			File environnements = new File(Configuration.pathToRessources + "/environnements");
			if (environnements.isDirectory())
				System.out.println("--_ Sauvegarde des environnements de simulation");
				try {
					copierDossier(environnements , new File(System.getProperty("user.dir")+"/civilisation/environnements"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	
			System.out.println("--_ Suppression de l'ancienne version");
			supprimerDossier(cible);
			
			cible.mkdir();
			
			PrintWriter out;
			try {
				out = new PrintWriter(cible.getPath()+"/"+"parametres"+Configuration.getExtension());
				if (Configuration.environnementACharger == null){
					out.println("Carte : " + "AUCUNE");
				}
				else{
					out.println("Carte : " + Configuration.environnementACharger + Configuration.getExtension());
				}
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("--_ Enregistrement des attributs");
			File attributes = new File(Configuration.pathToRessources + "/attributes");
			attributes.mkdir();
			for (int i = 0; i < Configuration.attributesNames.size();i++){
				try {
					out = new PrintWriter(new FileWriter(attributes.getPath()+"/"+Configuration.attributesNames.get(i)+Configuration.getExtension()));
					out.println("Name : " + Configuration.attributesNames.get(i));
					out.println("Starting value : " + Configuration.attributesStartingValues.get(i));	
					out.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
			}
					
			System.out.println("--_ Enregistrement des cognitons");
			File cognitons = new File(Configuration.pathToRessources + "/cognitons");
			cognitons.mkdir();
			for (int i = 0; i < Configuration.cognitons.size();i++){
				System.out.println("Save : " + Configuration.cognitons.get(i).getNom());
				Configuration.cognitons.get(i).enregistrer(cognitons);
			}
			
			System.out.println("--_ Save cloud cognitons");
			File cloudCognitons = new File(Configuration.pathToRessources + "/cloudCognitons");
			cloudCognitons.mkdir();
			for (int i = 0; i < Configuration.cloudCognitons.size();i++){
				Configuration.cloudCognitons.get(i).enregistrer(cloudCognitons);
			}

			System.out.println("--_ Enregistrement des objets");
			File objets = new File(Configuration.pathToRessources + "/objets");
			objets.mkdir();
			for (int i = 0; i < Configuration.objets.size();i++){
				Configuration.objets.get(i).enregistrer(objets);
			}

			System.out.println("--_ Enregistrement des items pheromones");
			File phero = new File(Configuration.pathToRessources + "/itemPheromones");
			phero.mkdir();
			for (int i = 0; i < Configuration.itemsPheromones.size();i++){
				System.out.println("Configuration.itemsPheromones");
				Configuration.itemsPheromones.get(i).enregistrer(phero);
			}
			
			System.out.println("--_ Enregistrement des plans");
			File plans = new File(Configuration.pathToRessources + "/plans");
			plans.mkdir();
			for (int i = 0; i < Configuration.plans.size();i++){
				Configuration.plans.get(i).enregistrer(plans);
			}			
			
			System.out.println("--_ Enregistrement des terrains");
			File terrains = new File(Configuration.pathToRessources + "/terrains");
			terrains.mkdir();
			for (int i = 0; i < Configuration.terrains.size();i++){
				Configuration.terrains.get(i).enregistrer(terrains);
			}
			
			System.out.println("--_ Enregistrement des civilisations");
			File civilisations = new File(Configuration.pathToRessources + "/civilisations");
			civilisations.mkdir();
			for (int i = 0; i < Configuration.civilisations.size();i++){
				Configuration.civilisations.get(i).enregistrer(civilisations);
			}
			
			System.out.println("--_ Save groups");
			File groups = new File(Configuration.pathToRessources + "/groups");
			groups.mkdir();
			for (int i = 0; i < Configuration.groups.size();i++){
				Configuration.groups.get(i).enregistrer(groups);
			}

			/*On remet les environnements en place*/
			environnements = new File(System.getProperty("user.dir")+"/civilisation/environnements");
			System.out.println(environnements.getAbsolutePath() + " "+environnements.isDirectory());
			if (environnements.isDirectory()){
				System.out.println("env");
				try {
					copierDossier(environnements , new File(Configuration.pathToRessources + "/environnements"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				supprimerDossier(environnements);
			}


			
		}
		else if (index == 1) /*Archiver l'ancienne version des ressources*/
		{
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			
	    	File versionActuelle = new File(Configuration.pathToRessources + "");
	    	File archive = new File(Configuration.pathToRessources + ""+dateFormat.format(date));
	    	System.out.println(Configuration.pathToRessources + ""+" "+dateFormat.format(date));

	    	try {
	    		copierDossier(versionActuelle, archive);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("Echec lors de la copie des fichiers");
			}
	    	
		}
		else if (index == 2) 
		{
			p.afficherStructureCognitive();
		}
		else if (index == 3) 
		{
			p.afficherEnvironnement();
		}
		else if (index == 4) 
		{
			p.afficherObjets();
		}
		else if (index == 5) 
		{
			p.afficherCivilisations();
		}
		else if (index == 6) 
		{
			p.afficherAttributes();
		}
		else if (index == 7) 
		{
			p.showGroupManager();
		}
	}
	
	
    public static void copierDossier(File src, File dest) throws IOException{
     
    	if(src.isDirectory()){
    	     
    		if(!dest.exists()){
    		   dest.mkdir();
    		}
 
    		String files[] = src.list();
 
    		for (String file : files) {
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   copierDossier(srcFile,destFile);
    		}
 
    	}
    	else{
    		InputStream in = new FileInputStream(src);
    	    OutputStream out = new FileOutputStream(dest); 
 
	        byte[] buffer = new byte[1024];
	        
	        int length;
	        //copy the file content in bytes 
	        while ((length = in.read(buffer)) > 0){
	    	   out.write(buffer, 0, length);
	        }

	        in.close();
	        out.close();
    	}
    }


    public void supprimerDossier(File cible){
    	if (cible.isDirectory()){
            File [] fileList = cible.listFiles();
            for(int i = 0;i<fileList.length;i++){
            	  supprimerDossier(fileList[i]);
            }
    	}
    	cible.delete();
    }
}
