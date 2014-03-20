package civilisation.inspecteur.simulation.objets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.inspecteur.animations.JJIconSelector;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inventaire.Objet;

public class PanelObjets extends JPanel{

	PanelModificationSimulation panelParent;
	PanelListeObjets panelListeObjets;
	
	JTextField nameField;
	JComboBox comboIcon;
	JJIconSelector iconSelector;
	
	public PanelObjets (PanelModificationSimulation panelParent , PanelListeObjets panelListeObjets){
		super();
		this.panelParent = panelParent;
		this.panelListeObjets = panelListeObjets;
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Edition d'objet");
		bordure.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(bordure);
		
		this.add(new JLabel("Item name :"));
		nameField = new JTextField(40);
		nameField.addActionListener(new ActionPanelObjet(this));
		this.add(nameField);
		
		//iconSelector = new JJIconSelector(Configuration.pathToIcon);
		//this.add(iconSelector);
		
		this.add(new JLabel("Icon :"));
		comboIcon = new JComboBox();
		
		//this.update();
		

	}
	
	/**
	 * Update data according to selected item in ItemList
	 */
	public void update(){		
		nameField.setText(((Objet) panelListeObjets.getListeObjets().getSelectedValue()).getNom()  );
	}

	public PanelListeObjets getPanelListeObjets() {
		return panelListeObjets;
	}

	public void setPanelListeObjets(PanelListeObjets panelListeObjets) {
		this.panelListeObjets = panelListeObjets;
	}

	public void performChange() {
		if (panelListeObjets != null){
			if ( panelListeObjets.getListeObjets().getSelectedValue() != null) {
				Objet o = ((Objet) panelListeObjets.getListeObjets().getSelectedValue());
				o.setNom(nameField.getText());
			}
		}
	}
	
	
	
	
}
