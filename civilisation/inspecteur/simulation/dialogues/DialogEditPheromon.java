package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.inspecteur.simulation.GCogniton;

public class DialogEditPheromon extends JDialog implements ActionListener, PropertyChangeListener, DocumentListener{
	
	ArrayList<JTextField> pheromons;

    JOptionPane optionPane;
    ArrayList<Object> array;
    
	public DialogEditPheromon(Frame f , boolean modal){
		super(f,modal);

		pheromons = new ArrayList<JTextField>();

		
		for (int i = 0; i < Configuration.itemsPheromones.size(); i++){
			JTextField text = new JTextField(Configuration.itemsPheromones.get(i).getNom());
			text.getDocument().addDocumentListener(this);
			pheromons.add(text);
		}
		JTextField text = new JTextField("");
		text.getDocument().addDocumentListener(this);
		pheromons.add(text);
		
		this.setTitle("Edit pheromons");
		
		
		array = new ArrayList<Object>();
	    for (int i = 0; i < pheromons.size(); i++){
	    	array.add(pheromons.get(i));
	    }
	    

	    Object[] options = {"OK" , "Cancel"};
	    optionPane = new JOptionPane(array.toArray(),
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
				ArrayList<ItemPheromone> nouvellesPheromones = new ArrayList<ItemPheromone>();
				for (int i = 0; i < pheromons.size();i++){
					if (!pheromons.get(i).getText().equals("")){
						ItemPheromone phero = new ItemPheromone(pheromons.get(i).getText());
						nouvellesPheromones.add(phero);
					}
				}
				Configuration.itemsPheromones = nouvellesPheromones;
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (!pheromons.isEmpty() && e.getDocument().equals(pheromons.get(pheromons.size()-1).getDocument())){
			System.out.println("add");
			JTextField text = new JTextField("");
			text.getDocument().addDocumentListener(this);
			pheromons.add(text);
			array.add(text);
			optionPane.setMessage(array.toArray());
			this.pack();
		}		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (!pheromons.isEmpty() && e.getDocument().equals(pheromons.get(pheromons.size()-1).getDocument())){
			System.out.println("add");
			JTextField text = new JTextField("");
			text.getDocument().addDocumentListener(this);
			pheromons.add(text);
			array.add(text);
			optionPane.setMessage(array.toArray());
			this.pack();
		}		
	}
	
	

}
