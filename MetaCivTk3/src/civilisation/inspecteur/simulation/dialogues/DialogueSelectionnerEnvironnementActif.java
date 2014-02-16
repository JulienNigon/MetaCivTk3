package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import civilisation.Configuration;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;

public class DialogueSelectionnerEnvironnementActif extends JDialog implements ActionListener, PropertyChangeListener{
	
	JComboBox listeEnvironnements;
    JOptionPane optionPane;
    PanelEnvironnement p;
    
	public DialogueSelectionnerEnvironnementActif(Frame f , boolean modal , PanelEnvironnement p){
		super(f,modal);

		this.p = p;
		listeEnvironnements = new JComboBox();

		File[] files = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements").listFiles();

		for (int i = 0; i < files.length; i++){
			listeEnvironnements.addItem(files[i].getName());
		}
		
		this.setTitle("Enregistrer un environnement");
		
		
	    Object[] array = {listeEnvironnements};
	       

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
		if (isVisible()  && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				Configuration.environnementACharger = ((String) (listeEnvironnements.getSelectedItem())).split(Configuration.getExtension())[0];
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
