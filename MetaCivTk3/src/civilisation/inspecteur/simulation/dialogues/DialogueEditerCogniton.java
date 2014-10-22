package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import civilisation.Configuration;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.inspecteur.animations.JJIconSelector;
import civilisation.inspecteur.simulation.GCogniton;

public class DialogueEditerCogniton extends JDialog implements ActionListener, PropertyChangeListener{
	
	JComboBox type;
	JTextField nom;
	GCogniton gCogniton;
    JOptionPane optionPane;
    JCheckBox recuAuDemarrage;
    JSlider[] hues;
    JSpinner startChance;
    JJIconSelector iconSelector;
    JLabel labname;
    JLabel labtype;
    JLabel labnumb;
    JLabel labhues;
    
	public DialogueEditerCogniton(Frame f , boolean modal, GCogniton gCogniton){
		super(f,modal);
		this.gCogniton = gCogniton;

		/*Type of cogniton*/
		labtype = new JLabel("Cogniton type :");
		type = new JComboBox();
		for (int i = 0; i < TypeDeCogniton.values().length; i++){
			type.addItem(TypeDeCogniton.values()[i]);
			if (TypeDeCogniton.values()[i].equals(gCogniton.getCogniton().getType())) {
				type.setSelectedIndex(i);
			}
		}
		
		/*Name of the cogniton*/
		labname = new JLabel("Cogniton name :");
		nom = new JTextField(20);
		nom.setText(gCogniton.getCogniton().getNom());
		
		/*Starting cogniton?*/
		recuAuDemarrage = new JCheckBox("Starting cogniton");
		recuAuDemarrage.addActionListener(this);
		recuAuDemarrage.setToolTipText("Give this cogniton to new agents?");
		if (gCogniton.getCogniton().isRecuAuDemarrage()){
			recuAuDemarrage.setSelected(true);
		}
		
		/*Start chances*/
		labnumb = new JLabel("Starting apparition chance :");
		SpinnerModel spinModel = new SpinnerNumberModel(gCogniton.getCogniton().getStartChance(), //initial value
                0, //min
                100, //max
                1);
		startChance = new JSpinner(spinModel);
		if (recuAuDemarrage.isSelected()) 
		{startChance.setEnabled(true);}
		else
		{startChance.setEnabled(false);}
		
		//this.add(iconSelector = new JJIconSelector());
		
		/* hues selection*/
		labhues = new JLabel("Cogniton hues : ");
		hues = new JSlider[TypeCogniton.nHues];
		for (int i = 0; i < TypeCogniton.nHues; i++){
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
	    Object[] array = {labname, nom, labtype, type , recuAuDemarrage, labnumb, startChance , labhues, hues};
	       
	    //Create an array specifying the number of dialog buttons
	    //and their text.
	    Object[] options = {"OK" , "Cancel"};
	 
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
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		System.out.println(optionPane.getValue());
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				gCogniton.getCogniton().setNom(nom.getText());
				gCogniton.getCogniton().setType((TypeDeCogniton) type.getSelectedItem());
				if (recuAuDemarrage.isSelected()) gCogniton.getCogniton().setStartChance(((Integer)startChance.getValue()));

				if (recuAuDemarrage.isSelected() != gCogniton.getCogniton().isRecuAuDemarrage()){
					if (recuAuDemarrage.isSelected()){
						Configuration.addCognitonDeBase(gCogniton.getCogniton());
						gCogniton.getCogniton().setStartChance(((Integer)startChance.getValue()));
					}
					else{
						Configuration.removeCognitonDeBase(gCogniton.getCogniton());
						gCogniton.getCogniton().setStartChance(0);
					}
					gCogniton.getCogniton().setRecuAuDemarrage(recuAuDemarrage.isSelected());
				}
				for (int i = 0; i < TypeCogniton.nHues; i++){
					gCogniton.getCogniton().getHues()[i] = hues[i].getValue();
				}
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("actionPerformed");
		
		if (recuAuDemarrage.isSelected()) {
			startChance.setEnabled(true);
		}
		else
		{
			startChance.setEnabled(false);
		}
	}
	
	
}
