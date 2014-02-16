package civilisation.inspecteur.tableauDeBord;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.cognitons.CCogniton;
import civilisation.inspecteur.animations.*;
import civilisation.world.World;

public class PanelRatioCognitons extends JJPanel{

	ArrayList<JJStatDiagramme> diagrammes = new ArrayList<JJStatDiagramme>();
	ArrayList<IconeTendance> tendances = new ArrayList<IconeTendance>();
	int nCognitons[] = new int[Configuration.cognitons.size()];
	
	public PanelRatioCognitons(){
		
		for (int i = 0; i < Configuration.cognitons.size(); i++){
			JJStatDiagramme diag = new JJStatDiagramme(this, 25, 40+(i*20), 250, 15);
			diag.addItem(Configuration.cognitons.get(i).getType().getCouleur(), Configuration.cognitons.get(i).getNom(), 20.);
			diag.addItem(Color.BLACK, "Agents sans ce cogniton", 20.);
			diagrammes.add(diag);
			this.add(diag);
			
			IconeTendance tend = new IconeTendance(this, 275, 40+(i*20), 16, 16, 0);
			tendances.add(tend);
			this.add(tend);

		}
		this.add(new JButton("test"));
	}

	public void actualiser() {
		// (World.getInstance().getTurtleWithID(agentID) != null && World.getInstance().getTurtleWithID(agentID).getClass() == Humain.class)
		Object tortues[] = World.getInstance().getTurtlesWithRoles("Humain").toArray();
		int nAgents = tortues.length;
		HashMap<String,Integer> decompteCognitons = new HashMap<String,Integer>();
		
		for (int i = 0; i < tortues.length; i++){
			if (tortues[i].getClass() == Humain.class){
				Humain h = (Humain) tortues[i];
				ArrayList<CCogniton> listeCognitons= h.getEsprit().getCognitons();
				for (int j = 0; j < listeCognitons.size(); j++){
					Integer v;
					if (decompteCognitons.get(listeCognitons.get(j).getCogniton().getNom()) == null){
						v = 0;
					}
					else{
						v = decompteCognitons.get(listeCognitons.get(j).getCogniton().getNom());
					}
					decompteCognitons.put(listeCognitons.get(j).getCogniton().getNom(), v + 1);
					System.out.println(listeCognitons.get(j).getCogniton().getNom() +" "+decompteCognitons.get(listeCognitons.get(j).getCogniton().getNom()));

				}
			}
			else{
				nAgents--;
			}
		}
		
		for (int i = 0; i < diagrammes.size(); i++){
			System.out.println(i);
			double valeurs[] = new double[2];
			double d;
			if (decompteCognitons.get(Configuration.cognitons.get(i).getNom()) == null){
				d = 0;
				System.out.println("d est nul : " + d + " " + Configuration.cognitons.get(i).getNom());
			}else{
				d = decompteCognitons.get(Configuration.cognitons.get(i).getNom());
				System.out.println("d vaut : " + decompteCognitons.get(Configuration.cognitons.get(i).getNom()));

				
			}

			valeurs[0] = d;
			valeurs[1] = nAgents - d;
			System.out.println("d : " + d);
			diagrammes.get(i).setValeur(valeurs, true);  //Modification des valeurs des diagrammes
			
			/*Gestion des tendances*/
			if (nCognitons[i] > d){
				tendances.get(i).setTendance(-1);
			}
			else if (nCognitons[i] < d){
				tendances.get(i).setTendance(1);
			} else {
				tendances.get(i).setTendance(0);
			}
			nCognitons[i] = (int) d;
			
			
			
		}
		System.out.println("Nombre d'agents : " + nAgents);
		
		
	}
	
	
}
