package civilisation.inspecteur.simulation.dialogues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.GPlan;
import civilisation.world.Terrain;

public class DialogueChoisirCouleurTerrain extends JDialog implements ActionListener, PropertyChangeListener{
	
	Terrain terrain;
    JOptionPane optionPane;
    JColorChooser choixCouleur;

	public DialogueChoisirCouleurTerrain(Frame f , boolean modal, Terrain terrain){
		super(f,modal);
		this.terrain = terrain;

		

		choixCouleur = new JColorChooser();

		this.setTitle("Choisir la couleur");
		
		
	    Object[] array = {choixCouleur};
	    Object[] options = {"Valider" , "Annuler"};
	 
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
		if (isVisible()){
			if (optionPane.getValue().equals("Valider")){
				terrain.setCouleur(choixCouleur.getColor());
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
