package civilisation.inspecteur.simulation.civilisations;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import civilisation.Civilisation;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inspecteur.simulation.objets.ActionPanelObjet;
import civilisation.inspecteur.simulation.objets.PanelListeObjets;
import civilisation.inventaire.Objet;

public class PanelCivilisations extends JPanel{

	PanelModificationSimulation panelParent;
	JTextField nameField;
	JComboBox comboIcon;
	PanelListeCivilisations panelListeCivilisations;
	
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
		
	}

	public void performChange() {
		if (panelListeCivilisations != null){
			if ( panelListeCivilisations.getListeCivilisations().getSelectedValue() != null) {
				Civilisation c = ((Civilisation) panelListeCivilisations.getListeCivilisations().getSelectedValue());
				c.setNom(nameField.getText());
			}
		}
		
	}
	
	/**
	 * Update data according to selected item in ItemList
	 */
	public void update(){		
		nameField.setText(((Civilisation) panelListeCivilisations.getListeCivilisations().getSelectedValue()).getNom()  );
	}

	public PanelListeCivilisations getPanelListeCivilisations() {
		return panelListeCivilisations;
	}

	public void setPanelListeCivilisations(
			PanelListeCivilisations panelListeCivilisations) {
		this.panelListeCivilisations = panelListeCivilisations;
	}
	
	
	
}
