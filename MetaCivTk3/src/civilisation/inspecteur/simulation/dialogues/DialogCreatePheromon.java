package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.world.Terrain;

public class DialogCreatePheromon extends JDialog implements ActionListener, PropertyChangeListener{
	
	Terrain terrain;
	JTextField nom;
	JLabel labelName;
	JLabel labelGrowth;
	JLabel labelStart;
	JTextField growth;
	JTextField start;
	JOptionPane optionPane;
	ArrayList<Object> selectors = new ArrayList<Object>();

	public DialogCreatePheromon(Frame f , boolean modal, Terrain terrain)
	{
		super(f,modal);
		this.terrain = terrain;
		labelName = new JLabel("Attribute Name : ");
		nom = new JTextField();
		labelStart = new JLabel("Start Value : ");
		start = new JTextField();
		labelGrowth = new JLabel("Growing value (units/tick) : ");
		growth = new JTextField();
		
		selectors.add(labelName);
		selectors.add(nom);
		selectors.add(labelStart);
		selectors.add(start);
		selectors.add(labelGrowth);
		selectors.add(growth);
		
		Object[] array = selectors.toArray();
	    Object[] options = {"OK" , "Cancel"};
	 
	    optionPane = new JOptionPane(array,
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]); 
	    setContentPane(new JScrollPane(optionPane));
	        
	    optionPane.addPropertyChangeListener(this);
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
		this.setFocusable(true);
		this.setVisible(true);
		this.pack();
	}
	
	public void propertyChange(PropertyChangeEvent e) {
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				ItemPheromone pher = new ItemPheromone(nom.getText());
				int index = terrain.getPheromoneIndexByName(pher.getNom());
				if(index != -1)
				{
					terrain.getPheromones().remove(index);
				}
				terrain.addPheromoneLiee(pher, Double.parseDouble(start.getText()), Double.parseDouble(growth.getText()));
				int i = 0;
				while(i < Configuration.itemsPheromones.size() || !Configuration.itemsPheromones.get(i).getNom().equals(pher.getNom()))
				{
					++i;
				}
				if(i >= Configuration.itemsPheromones.size())
				{
					Configuration.itemsPheromones.add(pher);
				}
				
			}		
	        setVisible(false);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
