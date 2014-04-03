package civilisation.inspecteur.simulation.objets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.effects.Effect;
import civilisation.inspecteur.animations.JJIconSelector;
import civilisation.inspecteur.simulation.PanelModificationSimulation;
import civilisation.inventaire.Objet;

public class PanelObjets extends JPanel implements ActionListener{

	PanelModificationSimulation panelParent;
	PanelListeObjets panelListeObjets;
	
	JTextField nameField;
	JTextField descriptionField;
	JComboBox comboIcon;
	JJIconSelector iconSelector;
	JLabel name;
	JLabel descriptionLabel;
	JLabel icon;
	JButton addEffect;
	ArrayList<PanelEffect> effects;
	JButton saveObject;
	
	public PanelObjets (PanelModificationSimulation panelParent , PanelListeObjets panelListeObjets){
		super();
		this.panelParent = panelParent;
		this.panelListeObjets = panelListeObjets;
		this.effects = new ArrayList<PanelEffect>();
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Edition d'objet");
		bordure.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(bordure);
		
		name = new JLabel("Item name : ");
		name.setVisible(false);
		this.add(name);
		nameField = new JTextField(40);
		nameField.addActionListener(new ActionPanelObjet(this));
		nameField.setVisible(false);
		this.add(nameField);
		
		//iconSelector = new JJIconSelector(Configuration.pathToIcon);
		//this.add(iconSelector);
		this.icon = new JLabel("Icon :");
		icon.setVisible(false);
		this.add(icon);
		comboIcon = new JComboBox();
		
		this.descriptionLabel = new JLabel("Description :");
		descriptionLabel.setVisible(false);
		this.add(descriptionLabel);
		//this.update();
		
		this.descriptionField = new JTextField(40);
		descriptionField.addActionListener(new ActionPanelObjet(this));
		descriptionField.setText("Enter item Description here");
		this.descriptionField.setVisible(false);
		this.add(descriptionField);

		this.addEffect = new JButton("Add an effect");
		this.addEffect.setVisible(false);
		this.addEffect.addActionListener(new ActionPanelObjet(this));
		this.add(addEffect);
		
		this.saveObject = new JButton("save object");
		saveObject.setVisible(false);
		saveObject.setActionCommand("Save");
		saveObject.addActionListener(this);
		addEffect.setActionCommand("addEffect");
		Box b2 = Box.createHorizontalBox();
		b2.add(name);
		b2.add(nameField);
		Box b3 = Box.createHorizontalBox();
		b3.add(descriptionLabel);
		b3.add(descriptionField);
		Box b1 = Box.createVerticalBox();
		b1.add(b2);
		b1.add(b3);
		b1.add(addEffect);
		b1.add(saveObject);
		this.add(b1);
	}
	
	/**
	 * Update data according to selected item in ItemList
	 */
	public void update(){	
		
		name.setVisible(true);
		nameField.setVisible(true);
		nameField.setText(((Objet) panelListeObjets.getListeObjets().getSelectedValue()).getNom()  );
		descriptionLabel.setVisible(true);
		descriptionField.setVisible(true);
		descriptionField.setText( ((Objet) panelListeObjets.getListeObjets().getSelectedValue()).getDescription()  );
		this.addEffect.setVisible(true);
		if(this.effects.size() <  ((Objet) panelListeObjets.getListeObjets().getSelectedValue()).getEffets().size())
		{
			for(int i = 0; i < ((Objet) panelListeObjets.getListeObjets().getSelectedValue()).getEffets().size(); ++i )
			{
				Effect ef = ((Objet) panelListeObjets.getListeObjets().getSelectedValue()).getEffets().get(i);
				this.effects.add( new PanelEffect(this,ef) );
				this.add(this.effects.get(this.effects.size() - 1));
			}
		}
		
		for(int i =0; i < this.effects.size(); ++i)
		{
			this.effects.get(i).setVisible(true);
		}
		saveObject.setVisible(true);
		
	}

	public PanelListeObjets getPanelListeObjets() {
		return panelListeObjets;
	}

	public void setPanelListeObjets(PanelListeObjets panelListeObjets) {
		this.panelListeObjets = panelListeObjets;
	}

	public void performChange() {
		
	}

	public void addEffects() {
		// TODO Auto-generated method stub
	//	System.out.println("New Effect");
		this.effects.add(new PanelEffect(this));
		this.add(this.effects.get(this.effects.size() - 1));
		this.effects.get(this.effects.size()-1).RendreVisible();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "Save")
		{
			if (panelListeObjets != null){
				if ( panelListeObjets.getListeObjets().getSelectedValue() != null) {
					Objet o = ((Objet) panelListeObjets.getListeObjets().getSelectedValue());
					o.setNom(nameField.getText());
					o.setDescription(descriptionField.getText());
					for(int i = 0; i < this.effects.size();++i)
					{
						o.addEffect(this.effects.get(i).returnEffect());
					}
				}
			}
		}
	}
	
	
	
	
}
