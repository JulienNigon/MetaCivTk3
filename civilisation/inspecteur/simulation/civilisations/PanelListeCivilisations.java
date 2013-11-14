package civilisation.inspecteur.simulation.civilisations;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import civilisation.Civilisation;
import civilisation.Configuration;
import civilisation.inspecteur.animations.JJPanel;

public class PanelListeCivilisations extends JJPanel{

	JList listeCivilisations;
	DefaultListModel listModel;
	PanelCivilisations panelCivilisations;
	
	public PanelListeCivilisations(PanelCivilisations panelCivilisations) {
		
		this.panelCivilisations = panelCivilisations;
		
		this.setLayout(new BorderLayout());
		
		setupList();
		
		this.add(listeCivilisations , BorderLayout.CENTER);
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Liste des civilisations");
		bordure.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(bordure);
		
	}

	public JList getListeCivilisations() {
		return listeCivilisations;
	}

	public void setListeCivilisations(JList listeCivilisations) {
		this.listeCivilisations = listeCivilisations;
	}

	public void addCivilization(Civilisation c) {
		((javax.swing.DefaultListModel)listeCivilisations.getModel()).addElement(c);			
	}
	


	private void setupList() {
		
		listModel = new DefaultListModel();
		listeCivilisations = new JList(listModel);

		for (int i = 0; i < Configuration.civilisations.size(); i++){
			listModel.addElement(Configuration.civilisations.get(i));
		}
		
		listeCivilisations.addMouseListener(new MouseListeCivilisations(this));
		listeCivilisations.setCellRenderer(new ListeCivilisationsRenderer());
	}

	public PanelCivilisations getPanelCivilisations() {
		return panelCivilisations;
	}

	public void setPanelCivilisations(PanelCivilisations panelCivilisations) {
		this.panelCivilisations = panelCivilisations;
	}
	
	
	
}
