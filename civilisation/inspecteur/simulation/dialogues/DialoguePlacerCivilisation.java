package civilisation.inspecteur.simulation.dialogues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import civilisation.inspecteur.simulation.environnement.GCarte;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;
import civilisation.inspecteur.simulation.environnement.PseudoPatch;

public class DialoguePlacerCivilisation extends JDialog implements ActionListener, PropertyChangeListener{
	
	JComboBox listeCiv;
    JOptionPane optionPane;
    GCarte g;
    PseudoPatch pp;
    
	public DialoguePlacerCivilisation(Frame f , boolean modal , GCarte g, PseudoPatch pp){
		super(f,modal);

		this.pp = pp;
		this.g = g;
		listeCiv = new JComboBox();


		for (int i = 0; i < Configuration.civilisations.size(); i++){
			listeCiv.addItem(Configuration.civilisations.get(i).getNom());
		}
		
		this.setTitle("Choisissez une civilisation");
		
		
	    Object[] array = {listeCiv};
	       

	    Object[] options = {"Valider" , "Annuler"};
	 
	    optionPane = new JOptionPane(array,
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]); 

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
				g.addStartingPosition(Configuration.getCivilisationByName((String) listeCiv.getSelectedItem()), pp);
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
