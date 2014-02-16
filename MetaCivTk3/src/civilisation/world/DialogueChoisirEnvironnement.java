package civilisation.world;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import civilisation.Configuration;
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
	       

	    Object[] options = {"OK"};
	 
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
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
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
