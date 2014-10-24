package civilisation.inspecteur.simulation.objets;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
	JButton addRecette;
	ArrayList<PanelEffect> effects;
	PanelRecette recette;
	JButton saveObject;
	JLabel ArdyLabel;
	JComboBox ArdyEffect;
	JButton addArdyEffect;
	Box b1 = Box.createVerticalBox();
	
	ArrayList<String> recettes;
	ArrayList<Integer> necessaires;
	
	
	public PanelObjets (PanelModificationSimulation panelParent , PanelListeObjets panelListeObjets){
		super();
		
	
		
		this.panelParent = panelParent;
		this.panelListeObjets = panelListeObjets;
		this.effects = new ArrayList<PanelEffect>();
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Item editor");
		bordure.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(bordure);
		
		recette = new PanelRecette(this);
		recette.setVisible(false);
		
		ArdyLabel = new JLabel("Add effect : ");
		ArdyEffect = new JComboBox();
		ArdyEffect.addActionListener(this);
		for(int i = 0; i < Configuration.effets.size();++i)
		{
			ArdyEffect.addItem(Configuration.effets.get(i).getName());
		}
		
		addArdyEffect = new JButton("Add this effect");
		addArdyEffect.setActionCommand("Ardy");
		addArdyEffect.addActionListener(this);

		this.ArdyLabel.setVisible(false);
		this.ArdyEffect.setVisible(false);
		this.addArdyEffect.setVisible(false);
		
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
	
		
		/*Start chances*/
		SpinnerModel spinModel = new SpinnerNumberModel(0, //initial value
                0, //min
                100, //max
                1); //pas

		
		

		this.addEffect = new JButton("New effect");
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
		
		Box b4 = Box.createHorizontalBox();
	
		b4.add(recette);

		
		Box b5 = Box.createHorizontalBox();
		JLabel rec = new JLabel("Recipe");
		b5.add(rec);		
		
		b1.add(b2);
		b1.add(b3);
		b1.add(b4);
		b1.add(addEffect);
		b1.add(saveObject);
		
		
		b1.add(ArdyLabel);
		b1.add(ArdyEffect);
		b1.add(addArdyEffect);

		
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
		this.ArdyLabel.setVisible(true);
		this.ArdyEffect.setVisible(true);
		this.addArdyEffect.setVisible(true);
		
		for(int i = 0; i < this.effects.size();++i)
		{
			this.effects.get(i).setVisible(false);
		}
		this.effects.clear();
	//	System.out.println("Taille des effets "+((Objet) panelListeObjets.getListeObjets().getSelectedValue()).getEffets().size());
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
		recette.setVisible(true);
		this.recette.update(this);
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
		this.ArdyLabel.setVisible(true);
		this.ArdyEffect.setVisible(true);
		addArdyEffect.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println(e.getActionCommand() );
		if(e.getActionCommand() == "Save")
		{
			if (panelListeObjets != null){
				if ( panelListeObjets.getListeObjets().getSelectedValue() != null) {
					Objet o = ((Objet) panelListeObjets.getListeObjets().getSelectedValue());
					o.setNom(nameField.getText());
					o.setDescription(descriptionField.getText());
			//		System.out.println("Nombre d'effets dans l'objet "+this.effects.size());
					for(int i = 0; i < o.getEffets().size();++i)
					{
						o.getEffets().remove(i);
					}
					for(int i = 0; i < this.effects.size();++i)
					{
					//	System.out.println("hey "+i);
						Effect temp = this.effects.get(i).returnEffect();
					//	System.out.println(temp);
						o.addEffect(temp);
						Configuration.addEffectUnique(temp);
					}
					if(this.recettes != null && this.recettes.size() > 0)
					{
						for(int i = 0; i < this.recettes.size();++i)
						{
							o.addItemRecipe(this.recettes.get(i), this.necessaires.get(i));
						}
					}
					

					Configuration.addObjetUnique(o);
				}
			}
		//	System.out.println("Nombre d'effets dans config : "+Configuration.effets.size());
		}

		if(e.getActionCommand() == "Ardy")
		{
			Effect ef = Configuration.getEffectByName(ArdyEffect.getSelectedItem().toString());
			this.effects.add( new PanelEffect(this,ef) );
			this.add(this.effects.get(this.effects.size() - 1));
		}
		
		if(e.getActionCommand() == "item")
		{

			int i = 0;
		}

	}
	
	
	
	
}
