package civilisation.world;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import civilisation.Configuration;
import civilisation.individu.cognitons.LienCogniton;
import civilisation.individu.cognitons.LienPlan;
import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.GPlan;

public class DialogueChoisirEnvironnement extends JDialog implements ActionListener, PropertyChangeListener{
	
	GPlan gPlan;
    JOptionPane optionPane;
    JComboBox listeEnvironnements;
    
	public DialogueChoisirEnvironnement(Frame f , boolean modal, GPlan gPlan){
		super(f,modal);
		this.gPlan = gPlan;

		

		listeEnvironnements = new JComboBox();
		
		File[] files = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements").listFiles();
		for (File file : files) {
			if (!file.isHidden() && file.getName().endsWith(Configuration.getExtension())){
		    	listeEnvironnements.addItem(file);
		}	
		}
		
		

		this.setTitle("Choisir environnement");
		
		

	    Object[] array = {listeEnvironnements};
	       

	    Object[] options = {"Valider"};
	 
	    optionPane = new JOptionPane(array,
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]); 
	    setContentPane(optionPane);
	        
	    optionPane.addPropertyChangeListener(this);
	        
		
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println(optionPane.getValue());
		if (isVisible()){
			if (optionPane.getValue().equals("Valider")){
				//gPlan.getPlan().setNom(nom.getText());
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
