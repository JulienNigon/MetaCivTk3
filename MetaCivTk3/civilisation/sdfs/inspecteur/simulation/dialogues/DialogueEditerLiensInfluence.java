package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import civilisation.Configuration;
import civilisation.individu.cognitons.LienPlan;
import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.PanelStructureCognitive;

public class DialogueEditerLiensInfluence extends JDialog implements ActionListener, PropertyChangeListener{
	
	ArrayList<JComboBox> plansLies;
	ArrayList<JSpinner> poids;

	GCogniton gCogniton;
    JOptionPane optionPane;
    ArrayList<Object> array;
    
	public DialogueEditerLiensInfluence(Frame f , boolean modal, GCogniton gCogniton){
		super(f,modal);
		this.gCogniton = gCogniton;

		plansLies = new ArrayList<JComboBox>();
		poids = new ArrayList<JSpinner>();
		array = new ArrayList<Object>();

		
		for (int i = 0; i < gCogniton.getCogniton().getLiensPlans().size(); i++){

			ajouterBox(gCogniton.getCogniton().getLiensPlans().get(i).getP(),gCogniton.getCogniton().getLiensPlans().get(i).getPoids());
		}
		ajouterBox(null,0);
		
		this.setTitle("Editer les liens d'influence");
		
		

	    
	       
	    //Create an array specifying the number of dialog buttons
	    //and their text.
	    Object[] options = {"OK" , "Cancel"};
	 
	    //Create the JOptionPane.
	    optionPane = new JOptionPane(array.toArray(),
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]); 
	    //Make this dialog display it.
	    setContentPane(new JScrollPane(optionPane));
	        
	    optionPane.addPropertyChangeListener(this);
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println(optionPane.getValue());
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				ArrayList<LienPlan> nouveauxLiens = new ArrayList<LienPlan>();
				for (int i = 0; i < plansLies.size();i++){
					if (!plansLies.get(i).getSelectedItem().equals("--AUCUN--")){
						System.out.println(plansLies.get(i).getSelectedIndex()-1 +" : "+ Configuration.plans.size());
						LienPlan lien = new LienPlan(  Configuration.plans.get(plansLies.get(i).getSelectedIndex()-1),(Integer)poids.get(i).getValue());
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
			ajouterBox(null,0);
			optionPane.setMessage(array.toArray());
			this.pack();
		}
		
	}
	
	public void ajouterBox(NPlan plan, int startValue){
		
		Box b = Box.createHorizontalBox();
		JComboBox box = new JComboBox();
		box.addActionListener(this);
		box.addItem("--AUCUN--");
		for (int j = 0; j < Configuration.plans.size(); j++){
			box.addItem(Configuration.plans.get(j));
			if (Configuration.plans.get(j).equals(plan)){
				box.setSelectedIndex(j+1);
			}
		}
		b.add(box);
		this.plansLies.add(box);
		SpinnerNumberModel model = new SpinnerNumberModel(startValue,-100,10,1);
		JSpinner jspin = new JSpinner(model);
		b.add(jspin);
		this.poids.add(jspin);
		array.add(b);
	}
	
	
	
}
