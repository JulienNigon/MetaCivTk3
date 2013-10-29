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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import civilisation.world.Terrain;

public class DialogueEditerTerrain extends JDialog implements ActionListener, PropertyChangeListener{
	
	Terrain terrain;
    JOptionPane optionPane;
    JTextField nom;
    JSpinner passabilite;
    JCheckBox infranchissable;

	public DialogueEditerTerrain(Frame f , boolean modal, Terrain terrain){
		super(f,modal);
		this.terrain = terrain;

		

		nom = new JTextField();
		nom.setText(terrain.getNom());

		SpinnerModel spinModel = new SpinnerNumberModel(terrain.getPassabilite(), //initial value
		                               1, //min
		                               1000, //max
		                               5);
		
		passabilite = new JSpinner(spinModel);
		infranchissable = new JCheckBox();
		infranchissable.setSelected(terrain.getInfranchissable());
		
		this.setTitle("Editer le terrain");
		
		
	    Object[] array = {nom , passabilite , infranchissable};
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
		if (isVisible()){
			if (optionPane.getValue().equals("Valider")){
				terrain.setNom(nom.getText());
				terrain.setPassabilite((Integer)passabilite.getValue());
				System.out.println(passabilite.getValue());
				terrain.setInfranchissable(infranchissable.isSelected());
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
