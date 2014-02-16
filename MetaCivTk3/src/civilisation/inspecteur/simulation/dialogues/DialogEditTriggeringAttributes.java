package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;

/**
 * Dialog to edit the triggering attributes of a cogniton 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import civilisation.Configuration;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.PanelStructureCognitive;

public class DialogEditTriggeringAttributes extends JDialog implements ActionListener, PropertyChangeListener{
	
	ArrayList<JComboBox> triggeringAttributes;
	ArrayList<JComboBox> comparator;
	ArrayList<JComboBox> triggeringValues;

	GCogniton gCogniton;
    JOptionPane optionPane;
    ArrayList<Object> array;
    
	public DialogEditTriggeringAttributes(Frame f , boolean modal, GCogniton gCogniton){
		super(f,modal);
		this.gCogniton = gCogniton;

		triggeringAttributes = new ArrayList<JComboBox>();
		triggeringValues = new ArrayList<JComboBox>();
		comparator = new ArrayList<JComboBox>();

		
		for (int i = 0; i < gCogniton.getCogniton().getLiensPlans().size(); i++){
			JComboBox box = new JComboBox();
			box.addItem("--NONE--");
			for (int j = 0; j < Configuration.attributesNames.size(); j++){
				box.addItem(Configuration.attributesNames.get(j));
			//	if (Configuration.plans.get(j).equals(gCogniton.getCogniton().getLiensPlans().get(i).getP())){
			//		box.setSelectedIndex(j+1);
			//	}
			}
			triggeringAttributes.add(box);
			
			box = new JComboBox();
			box.addItem("> ");  //+2
			box.addItem(">=");  //+1
			box.addItem("==");  // 0
			box.addItem("<= ");  //-1
			box.addItem("< ");  //-2
			comparator.add(box);
			
			box = new JComboBox();
			for (int j = -100; j <= 100; j++){
				box.addItem(j);
			}
			//box.setSelectedIndex(gCogniton.getCogniton().getLiensPlans().get(i).getPoids()+20);
			triggeringValues.add(box);
		}
		ajouterBox();
		
		this.setTitle("Edit triggering attributes");
		
		
		array = new ArrayList<Object>();
	    for (int i = 0; i < triggeringAttributes.size(); i++){
	    	array.add(triggeringAttributes.get(i));
	    	array.add(comparator.get(i)); 
	    	array.add(triggeringValues.get(i)); 
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
				gCogniton.getCogniton().getTriggeringAttributes().clear();
				for (int i = 0; i < triggeringAttributes.size();i++){
					if (!triggeringAttributes.get(i).getSelectedItem().equals("--NONE--")){
						//System.out.println(triggeringAttributes.get(i).getSelectedIndex()-1 +" : "+ Configuration.plans.size());
						Object[] tab = new Object[3];
						tab[0] = triggeringAttributes.get(i).getSelectedItem();
						tab[1] = (Integer) triggeringValues.get(i).getSelectedItem();
						tab[2] = ((Integer) comparator.get(i).getSelectedIndex() * (-1)) + 2;
						System.out.println("inde" + tab[2]);
						gCogniton.getCogniton().getTriggeringAttributes().add(tab);
						
					}
				}
				((PanelStructureCognitive) gCogniton.getParent()).showTrigger(gCogniton);
				((PanelStructureCognitive) gCogniton.getParent()).clearTriggerLink();
				((PanelStructureCognitive) gCogniton.getParent()).createTriggerLink();
			}		
	        setVisible(false);
	        
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (!triggeringAttributes.isEmpty() && e.getSource().equals(triggeringAttributes.get(triggeringAttributes.size()-1)) && !((JComboBox) e.getSource()).getSelectedItem().equals("--NONE--")){
			System.out.println("add");
			ajouterBox();
			array.add(triggeringAttributes.get(triggeringAttributes.size()-1));
			array.add(triggeringValues.get(triggeringAttributes.size()-1));
			array.add(comparator.get(triggeringAttributes.size()-1));

			optionPane.setMessage(array.toArray());
			this.pack();
		}
		
	}
	
	public void ajouterBox(){
		
		JComboBox box = new JComboBox();
		box.addActionListener(this);
		box.addItem("--NONE--");
		for (int j = 0; j < Configuration.attributesNames.size(); j++){
			box.addItem(Configuration.attributesNames.get(j));
		}
		triggeringAttributes.add(box);
		
		box = new JComboBox();
		box.addItem("> ");  //+2
		box.addItem(">=");  //+1
		box.addItem("==");  // 0
		box.addItem("<= ");  //-1
		box.addItem("< ");  //-2
		comparator.add(box);
		
		box = new JComboBox();
		for (int j = -100; j <= 100; j++){
			box.addItem(j);
		}
		box.setSelectedIndex(0);
		triggeringValues.add(box);
	}

	
	
}
	


