package civilisation.inspecteur.simulation.attributes;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import civilisation.Civilisation;
import civilisation.Configuration;
import civilisation.inspecteur.simulation.PanelModificationSimulation;

public class PanelAttributes extends JPanel{

	PanelModificationSimulation panelParent;
	ArrayList<JTextField> attributesName = new ArrayList<JTextField>();
	ArrayList<JSpinner> attributesStartingValue = new ArrayList<JSpinner>();
	JComboBox comboIcon;
	public boolean isSetuping = false;
	
	public PanelAttributes (PanelModificationSimulation panelParent){
		super();
		this.panelParent = panelParent;
		this.setLayout(new GridLayout(1,2));
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Attributes");
		bordure.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(bordure);
		
		setupField();
		revalidate();

	}

	public void performChange() {
		
		Configuration.attributesNames = new ArrayList<String>();
		Configuration.attributesStartingValues = new ArrayList<Double>();
		
		for (int i = 0 ; i < attributesName.size() ; i++) {

			
			if (!attributesName.get(i).getText().equals("") ) {
				Configuration.attributesNames.add(attributesName.get(i).getText());
				Configuration.attributesStartingValues.add((Double) attributesStartingValue.get(i).getValue());
			}
			
			//System.out.println(Configuration.attributesNames.get(i));
		}
		if (attributesName.size() == Configuration.attributesNames.size()) {
			System.out.println(attributesName.size() + " " + Configuration.attributesNames.size());
			setupField();
			revalidate();
		}
		
	}

	public void setupField() {
		attributesName = new ArrayList<JTextField>();
		attributesStartingValue = new ArrayList<JSpinner>();
		
		this.removeAll();
		
		isSetuping = true;
		
		this.setLayout(new GridLayout(Configuration.attributesNames.size()+1,2));

		for (int i = 0 ; i < Configuration.attributesNames.size() ; i++) {
			attributesName.add(new JTextField(""));
			attributesName.get(i).setName("Field " + i);
			attributesName.get(i).setText(Configuration.attributesNames.get(i));
			attributesName.get(i).addActionListener(new ActionPanelAttributes(this));
			this.add(attributesName.get(i),i,0);
			/*Create JComboBox to select starting value*/
			SpinnerModel spinModel = new SpinnerNumberModel((Double)Configuration.attributesStartingValues.get(i), (Double)0.0,(Double)1000.0,(Double)0.1);
			JSpinner spin = new JSpinner(spinModel);
			attributesStartingValue.add(spin);
			this.add(spin,i,1);
		}

		attributesName.add(new JTextField(""));
		attributesName.get(Configuration.attributesNames.size()).setName("Field " + Configuration.attributesNames.size());
		attributesName.get(Configuration.attributesNames.size()).addActionListener(new ActionPanelAttributes(this));
		this.add(attributesName.get(Configuration.attributesNames.size()),Configuration.attributesNames.size(),0);

		SpinnerModel spinModel = new SpinnerNumberModel((Double)0.0, (Double)0.0,(Double)1000.0,(Double)0.1);
		JSpinner spin = new JSpinner(spinModel);
		attributesStartingValue.add(spin);
		this.add(spin, Configuration.attributesNames.size(),1);
		
		isSetuping = false;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//this.performChange();
	}
}
