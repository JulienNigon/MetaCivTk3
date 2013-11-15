package civilisation.inspecteur.simulation.dialogues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.GPlan;

public class DialogueEditerPlan extends JDialog implements ActionListener, PropertyChangeListener{
	
	JTextField nom;
	GPlan gPlan;
    JOptionPane optionPane;
    
	public DialogueEditerPlan(Frame f , boolean modal, GPlan gPlan){
		super(f,modal);
		this.gPlan = gPlan;

		

		nom = new JTextField(20);
		nom.setText(gPlan.getPlan().getNom());

		this.setTitle("Editer un cogniton");
		
		
		/*Proviens du tutorial Java Sun*/
	    Object[] array = {nom};
	       
	    //Create an array specifying the number of dialog buttons
	    //and their text.
	    Object[] options = {"Valider" , "Annuler"};
	 
	    //Create the JOptionPane
	    optionPane = new JOptionPane(array,
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
		if (isVisible()){
			if (optionPane.getValue().equals("Valider")){
				gPlan.getPlan().setNom(nom.getText());
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
