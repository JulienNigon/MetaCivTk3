package civilisation.inspecteur.simulation.dialogues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import civilisation.Configuration;
import civilisation.individu.cognitons.LienPlan;
import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.PanelStructureCognitive;

public class DialogueEditerLiensInfluence extends JDialog implements ActionListener, PropertyChangeListener{
	
	ArrayList<JComboBox> plansLies;
	ArrayList<JComboBox> poids;

	GCogniton gCogniton;
    JOptionPane optionPane;
    ArrayList<Object> array;
    
	public DialogueEditerLiensInfluence(Frame f , boolean modal, GCogniton gCogniton){
		super(f,modal);
		this.gCogniton = gCogniton;

		plansLies = new ArrayList<JComboBox>();
		poids = new ArrayList<JComboBox>();

		
		for (int i = 0; i < gCogniton.getCogniton().getLiensPlans().size(); i++){
			JComboBox box = new JComboBox();
			box.addItem("--AUCUN--");
			for (int j = 0; j < Configuration.plans.size(); j++){
				box.addItem(Configuration.plans.get(j));
				if (Configuration.plans.get(j).equals(gCogniton.getCogniton().getLiensPlans().get(i).getP())){
					box.setSelectedIndex(j+1);
				}
			}
			plansLies.add(box);
			box = new JComboBox();
			for (int j = -20; j <= 20; j++){
				box.addItem(j);
			}
			box.setSelectedIndex(gCogniton.getCogniton().getLiensPlans().get(i).getPoids()+20);
			poids.add(box);
		}
		ajouterBox();
		
		this.setTitle("Editer les liens d'influence");
		
		
		/*Proviens du tutorial Java Sun*/
		array = new ArrayList<Object>();
	    for (int i = 0; i < plansLies.size(); i++){
	    	array.add(plansLies.get(i));
	    	array.add(poids.get(i)); 
	    }
	    
	       
	    //Create an array specifying the number of dialog buttons
	    //and their text.
	    Object[] options = {"Valider" , "Annuler"};
	 
	    //Create the JOptionPane.
	    optionPane = new JOptionPane(array.toArray(),
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]); 
	    //Make this dialog display it.
	    setContentPane(optionPane);
	        
	    optionPane.addPropertyChangeListener(this);
	        
		
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println(optionPane.getValue());
		if (isVisible() && (optionPane.getValue().equals("Valider") || optionPane.getValue().equals("Annuler"))){
			if (optionPane.getValue().equals("Valider")){
				ArrayList<LienPlan> nouveauxLiens = new ArrayList<LienPlan>();
				for (int i = 0; i < plansLies.size();i++){
					if (!plansLies.get(i).getSelectedItem().equals("--AUCUN--")){
						System.out.println(plansLies.get(i).getSelectedIndex()-1 +" : "+ Configuration.plans.size());
						LienPlan lien = new LienPlan(  Configuration.plans.get(plansLies.get(i).getSelectedIndex()-1),(Integer) poids.get(i).getSelectedItem());
						nouveauxLiens.add(lien);
					}
				}
				gCogniton.getCogniton().setLiensPlans(nouveauxLiens);
				((PanelStructureCognitive) gCogniton.getParent()).supprimerLiensInfluence();
				((PanelStructureCognitive) gCogniton.getParent()).creerLiensInfluence();
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (!plansLies.isEmpty() && e.getSource().equals(plansLies.get(plansLies.size()-1)) && !((JComboBox) e.getSource()).getSelectedItem().equals("--AUCUN--")){
			System.out.println("add");
			ajouterBox();
			array.add(plansLies.get(plansLies.size()-1));
			array.add(poids.get(plansLies.size()-1));
			optionPane.setMessage(array.toArray());
			this.pack();
		}
		
	}
	
	public void ajouterBox(){
		
		JComboBox box = new JComboBox();
		box.addActionListener(this);
		box.addItem("--AUCUN--");
		for (int j = 0; j < Configuration.plans.size(); j++){
			box.addItem(Configuration.plans.get(j));
		}
		plansLies.add(box);
		box = new JComboBox();
		for (int j = -20; j <= 20; j++){
			box.addItem(j);
		}
		box.setSelectedIndex(20);
		poids.add(box);
	}
	
	
	
}
