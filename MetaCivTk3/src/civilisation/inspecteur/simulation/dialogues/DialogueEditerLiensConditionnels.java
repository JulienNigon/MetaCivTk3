package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import civilisation.Configuration;
import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.PanelStructureCognitive;

public class DialogueEditerLiensConditionnels extends JDialog implements ActionListener, PropertyChangeListener{
	
	ArrayList<JComboBox> plansLies;

	GCogniton gCogniton;
    JOptionPane optionPane;
    ArrayList<Object> array;
    
	public DialogueEditerLiensConditionnels(Frame f , boolean modal, GCogniton gCogniton){
		super(f,modal);
		this.gCogniton = gCogniton;

		plansLies = new ArrayList<JComboBox>();

		
		for (int i = 0; i < gCogniton.getCogniton().getPlansAutorises().size(); i++){
			JComboBox box = new JComboBox();
			box.addItem("--AUCUN--");
			for (int j = 0; j < Configuration.plans.size(); j++){
				box.addItem(Configuration.plans.get(j));
				if (Configuration.plans.get(j).equals(gCogniton.getCogniton().getPlansAutorises().get(i))){
					box.setSelectedIndex(j+1);
				}
			}
			plansLies.add(box);
		}
		ajouterBox();
		
		this.setTitle("Editer les liens conditionnels");
		
		
		/*Proviens du tutorial Java Sun*/
		array = new ArrayList<Object>();
	    for (int i = 0; i < plansLies.size(); i++){
	    	array.add(plansLies.get(i));
	    }
	    
	       
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
	    setContentPane(optionPane);
	        
	    optionPane.addPropertyChangeListener(this);
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/bin/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println(optionPane.getValue());
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				ArrayList<NPlan> nouveauxLiens = new ArrayList<NPlan>();
				for (int i = 0; i < plansLies.size();i++){
					if (!plansLies.get(i).getSelectedItem().equals("--AUCUN--")){
						System.out.println(plansLies.get(i).getSelectedIndex()-1 +" : "+ Configuration.plans.size());
						nouveauxLiens.add(Configuration.plans.get(plansLies.get(i).getSelectedIndex()-1));
					}
				}
				gCogniton.getCogniton().setPlansAutorises(nouveauxLiens);
				((PanelStructureCognitive) gCogniton.getParent()).supprimerLiensConditionnels();
				((PanelStructureCognitive) gCogniton.getParent()).creerLiensConditionnels();
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
	}
	
	
	
}
