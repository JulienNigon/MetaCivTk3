package civilisation.inspecteur.simulation.dialogues;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.world.Terrain;

public class DialogueEditerTerrain extends JDialog implements ActionListener, PropertyChangeListener{
	
	Terrain terrain;
    JOptionPane optionPane;
    JButton add;
    JTextField nom;
    JLabel name;
    JLabel passability;
    JSpinner passabilite;
    JCheckBox infranchissable;
    ArrayList<Object> selectors = new ArrayList<Object>();
    ArrayList<JSpinner> startPh = new ArrayList<JSpinner>();
    ArrayList<JSpinner> growthPh = new ArrayList<JSpinner>();
    Frame f;
    boolean modal;
	public DialogueEditerTerrain(Frame f , boolean modal, Terrain terrain){
		super(f,modal);
		f = f;
		modal = modal;
		this.terrain = terrain;
		this.setTitle("Editer le terrain");
		
	/*	Container cp = getContentPane(); 
		JScrollPane jspane = new JScrollPane(cp, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 		this.setContentPane(jspane);
*/
		nom = new JTextField();
		nom.setText(terrain.getNom());

		SpinnerModel spinModel = new SpinnerNumberModel(terrain.getPassabilite(), //initial value
		                               1, //min
		                               1000, //max
		                               5);
		passability = new JLabel("Passability :");
		name = new JLabel("Name :");
		passabilite = new JSpinner(spinModel);
		infranchissable = new JCheckBox("Impassable?");
		infranchissable.setSelected(terrain.getInfranchissable());
		
		selectors.add(name);
		selectors.add(nom);
		selectors.add(passability);
		selectors.add(passabilite);
		selectors.add(infranchissable);

		add = new JButton("Add Attribute");
		add.addActionListener(this);
		selectors.add(add);
		
		for (int i = 0 ; i< Configuration.itemsPheromones.size() ; i++) {
			int var = terrain.getPheromoneIndexByName(Configuration.itemsPheromones.get(i).getNom());
			double start = 0, growth = 0;
			if (var != -1) {
				start = terrain.getPheroInitiales().get(var);
				growth = terrain.getPheroCroissance().get(var);
			}

			
			spinModel = new SpinnerNumberModel(start, -1000, 1000, 0.1);
			JSpinner jSpinner = new JSpinner(spinModel);
			jSpinner.setToolTipText("Starting value of " + Configuration.itemsPheromones.get(i).getNom());
			startPh.add(jSpinner);
			
			Box boxStart = Box.createHorizontalBox();
			boxStart.add(new JLabel("Starting value : "));
			boxStart.add(jSpinner);

			spinModel = new SpinnerNumberModel(growth, -1000, 1000, 0.1);
			JSpinner jSpinner2 = new JSpinner(spinModel);
			jSpinner2.setToolTipText("Growth value of " + Configuration.itemsPheromones.get(i).getNom());
			growthPh.add(jSpinner2);
			
			Box boxGrowth = Box.createHorizontalBox();
			boxGrowth.add(new JLabel("Growth value : "));
			boxGrowth.add(jSpinner2);
			
			Box box = Box.createVerticalBox();
			box.add(boxStart);
			box.add(boxGrowth);
			TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), Configuration.itemsPheromones.get(i).getNom());
			border.setTitleJustification(TitledBorder.LEFT);
			box.setBorder(border);
			selectors.add(box);

			
		}
		
		
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
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				terrain.setNom(nom.getText());
				terrain.setPassabilite((Integer)passabilite.getValue());
				terrain.setInfranchissable(infranchissable.isSelected());
				terrain.clearPheromones();		
				for (int i = 0 ; i < Configuration.itemsPheromones.size() ; i++) {
					if ((Double)startPh.get(i).getValue() != 0 || (Double)growthPh.get(i).getValue() != 0) {
						System.out.println("ter" + Configuration.itemsPheromones.get(i).getNom()+" "+ (Double)startPh.get(i).getValue()+" "+ (Double)growthPh.get(i).getValue());
						terrain.addPheromoneLiee(Configuration.itemsPheromones.get(i), (Double)startPh.get(i).getValue(), (Double)growthPh.get(i).getValue());
					}
				}
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DialogCreatePheromon dia = new DialogCreatePheromon(f,modal,terrain);
		dia.setVisible(true);
	}
	
	
}
