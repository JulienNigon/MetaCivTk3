package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import civilisation.inspecteur.simulation.GPlan;

public class DialogueEditerPlan extends JDialog implements ActionListener, PropertyChangeListener{
	
	JTextField nom;
	GPlan gPlan;
    JOptionPane optionPane;
    JCheckBox isAuto, isBirth;

	public DialogueEditerPlan(Frame f , boolean modal, GPlan gPlan){
		super(f,modal);
		this.gPlan = gPlan;

		

		nom = new JTextField(20);
		nom.setText(gPlan.getPlan().getNom());

		isAuto = new JCheckBox("Auto-Plan");
		isAuto.setSelected(gPlan.getPlan().getIsSelfPlan());
		isAuto.setToolTipText("Every agents will run this plan every tick if this box is checked. You could use this features to create automatic cognitons transmissions," +
				" or change attributes (need for food...)");
		
		isBirth = new JCheckBox("Birth-Plan");
		isBirth.setSelected(gPlan.getPlan().getIsBirthPlan());
		isBirth.setToolTipText("Every agents will run this plan at birth.");
		
		this.setTitle("Edite plan");
		
		
		/*Proviens du tutorial Java Sun*/
	    Object[] array = {nom , isAuto , isBirth};
	       
	    //Create an array specifying the number of dialog buttons
	    //and their text.
	    Object[] options = {"OK" , "Cancel"};
	 
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
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/bin/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println(optionPane.getValue());
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				gPlan.getPlan().setNom(nom.getText());
				gPlan.getPlan().setIsSelfPlan(isAuto.isSelected());
				gPlan.getPlan().setIsBirthPlan(isBirth.isSelected());
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
