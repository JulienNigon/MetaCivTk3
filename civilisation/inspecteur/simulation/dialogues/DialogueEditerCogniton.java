package civilisation.inspecteur.simulation.dialogues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import civilisation.Configuration;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.inspecteur.simulation.GCogniton;

public class DialogueEditerCogniton extends JDialog implements ActionListener, PropertyChangeListener{
	
	JComboBox type;
	JTextField nom;
	GCogniton gCogniton;
    JOptionPane optionPane;
    JCheckBox recuAuDemarrage;
    JSlider[] hues;


    
	public DialogueEditerCogniton(Frame f , boolean modal, GCogniton gCogniton){
		super(f,modal);
		this.gCogniton = gCogniton;

		/*Type of cogniton*/
		type = new JComboBox();
		for (int i = 0; i < TypeDeCogniton.values().length; i++){
			type.addItem(TypeDeCogniton.values()[i]);
		}
		
		/*Name of the cogniton*/
		nom = new JTextField(20);
		nom.setText(gCogniton.getCogniton().getNom());
		
		/*Starting cogniton?*/
		recuAuDemarrage = new JCheckBox();
		recuAuDemarrage.setToolTipText("Donner ce cogniton aux nouveaux agents?");
		if (gCogniton.getCogniton().isRecuAuDemarrage()){
			recuAuDemarrage.setSelected(true);
		}
		
		/* hues selection*/

		hues = new JSlider[NCogniton.nHues];
		for (int i = 0; i < NCogniton.nHues; i++){
			int temp = gCogniton.getCogniton().getHues()[i];
			hues[i] = new JSlider(0,50,temp);
			hues[i].setMajorTickSpacing(10);
			hues[i].setMinorTickSpacing(1);
			hues[i].setPaintTicks(true);
			hues[i].setPaintLabels(true);
			//hues[i].setBackground(NCogniton.hueColors[i]);  /*Colorer les sliders?*/
			
		}
		
		
		
		this.setTitle("Editer un cogniton");
		
		
		/*Proviens du tutorial Java Sun*/
	    Object[] array = {nom, type , recuAuDemarrage , hues};
	       
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
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/bin/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
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
				for (int i = 0; i < NCogniton.nHues; i++){
					gCogniton.getCogniton().getHues()[i] = (Integer) hues[i].getValue();
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
