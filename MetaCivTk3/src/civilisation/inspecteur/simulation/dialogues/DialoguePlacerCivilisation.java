package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import civilisation.Configuration;
import civilisation.inspecteur.simulation.environnement.GCarte;
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
	       

	    Object[] options = {"OK" , "Cancel"};
	 
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
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
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
