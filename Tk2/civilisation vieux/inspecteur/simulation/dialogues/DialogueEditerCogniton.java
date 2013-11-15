package civilisation.inspecteur.simulation.dialogues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import civilisation.Configuration;
import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.inspecteur.simulation.GCogniton;

public class DialogueEditerCogniton extends JDialog implements ActionListener, PropertyChangeListener{
	
	JComboBox type;
	JTextField nom;
	GCogniton gCogniton;
    JOptionPane optionPane;
    JCheckBox recuAuDemarrage;

    
	public DialogueEditerCogniton(Frame f , boolean modal, GCogniton gCogniton){
		super(f,modal);
		this.gCogniton = gCogniton;

		
		type = new JComboBox();
		
		for (int i = 0; i < TypeDeCogniton.values().length; i++){
			type.addItem(TypeDeCogniton.values()[i]);
		}
		
		nom = new JTextField(20);
		nom.setText(gCogniton.getCogniton().getNom());
		recuAuDemarrage = new JCheckBox();
		recuAuDemarrage.setToolTipText("Donner ce cogniton aux nouveaux agents?");
		if (gCogniton.getCogniton().isRecuAuDemarrage()){
			recuAuDemarrage.setSelected(true);
		}
		
		this.setTitle("Editer un cogniton");
		
		
		/*Proviens du tutorial Java Sun*/
	    Object[] array = {nom, type , recuAuDemarrage};
	       
	    //Create an array specifying the number of dialog buttons
	    //and their text.
	    Object[] options = {"Valider" , "Annuler"};
	 
	    //Create the JOptionPane.
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
				gCogniton.getCogniton().setNom(nom.getText());
				gCogniton.getCogniton().setType((TypeDeCogniton) type.getSelectedItem());
				if (recuAuDemarrage.isSelected() != gCogniton.getCogniton().isRecuAuDemarrage()){
					if (recuAuDemarrage.isSelected()){
						Configuration.addCognitonDeBase(gCogniton.getCogniton());
					}
					else{
						Configuration.removeCognitonDeBase(gCogniton.getCogniton());
					}
					gCogniton.getCogniton().setRecuAuDemarrage(recuAuDemarrage.isSelected());
				}
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
