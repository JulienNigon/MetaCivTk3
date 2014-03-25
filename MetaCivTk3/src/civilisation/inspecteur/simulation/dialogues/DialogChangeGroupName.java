package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;
import civilisation.inspecteur.simulation.groupManager.GroupToolBar;
import civilisation.inspecteur.simulation.groupManager.GroupTreeToolBar;

public class DialogChangeGroupName extends JDialog implements ActionListener, PropertyChangeListener{
	
	JTextField nom;
    JOptionPane optionPane;
    GroupToolBar source;
    
	public DialogChangeGroupName(Frame f , boolean modal , GroupToolBar source){
		super(f,modal);

		this.source = source;
		nom = new JTextField(20);
		nom.setText("group_name");

		
		this.setTitle("Create a role");
		
		
	    Object[] array = {nom};
	       

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
				source.changeGroupName(nom.getText());
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
