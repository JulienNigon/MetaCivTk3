package civilisation.inspecteur.simulation.civilisations;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import civilisation.Civilisation;
import civilisation.inspecteur.simulation.PanelModificationSimulation;

public class PanelCivilisations extends JPanel{

	PanelModificationSimulation panelParent;
	JTextField nameField;
	JComboBox comboIcon;
	PanelListeCivilisations panelListeCivilisations;
    JSlider startingAgents;

	
	public PanelCivilisations (PanelModificationSimulation panelParent , PanelListeCivilisations panelListeCivilisations){
		super();
		this.panelParent = panelParent;
		this.panelListeCivilisations = panelListeCivilisations;
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Edition de civilisations");
		bordure.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(bordure);
		
		this.add(new JLabel("Civilisation name :"));
		nameField = new JTextField(40);
		nameField.addActionListener(new ActionPanelCivilisation(this));
		this.add(nameField);
		
		/* number of starting agents selection*/
		startingAgents = new JSlider(0,500,0);
		startingAgents.setMajorTickSpacing(50);
		startingAgents.setMinorTickSpacing(10);
		startingAgents.setPaintTicks(true);
		startingAgents.setPaintLabels(true);
		this.add(startingAgents);
		
	}

	public void performChange() {
		if (panelListeCivilisations != null){
			if ( panelListeCivilisations.getListeCivilisations().getSelectedValue() != null) {
				Civilisation c = ((Civilisation) panelListeCivilisations.getListeCivilisations().getSelectedValue());
				c.setNom(nameField.getText());
				c.setAgentsInitiaux(startingAgents.getValue());
			}
		}
		
	}
	
	/**
	 * Update data according to selected item in ItemList
	 */
	public void update(){		
		nameField.setText(((Civilisation) panelListeCivilisations.getListeCivilisations().getSelectedValue()).getNom()  );
		startingAgents.setValue((((Civilisation) panelListeCivilisations.getListeCivilisations().getSelectedValue()).getAgentsInitiaux()))  ;

	}

	public PanelListeCivilisations getPanelListeCivilisations() {
		return panelListeCivilisations;
	}

	public void setPanelListeCivilisations(
			PanelListeCivilisations panelListeCivilisations) {
		this.panelListeCivilisations = panelListeCivilisations;
	}
	
	
	
}
