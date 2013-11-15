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
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;

public class DialogueEnregistrerEnvironnement extends JDialog implements ActionListener, PropertyChangeListener{
	
	JTextField nom;
    JOptionPane optionPane;
    PanelEnvironnement p;
    
	public DialogueEnregistrerEnvironnement(Frame f , boolean modal , PanelEnvironnement p){
		super(f,modal);

		this.p = p;
		nom = new JTextField(20);
		nom.setText("nom");

		
		this.setTitle("Enregistrer un environnement");
		
		
	    Object[] array = {nom};
	       

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
				p.sauvegarderEnvironnement(nom.getText());
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
