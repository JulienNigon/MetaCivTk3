package civilisation.inspecteur.simulation.objets;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inventaire.Objet;

public class PanelListeObjets extends JJPanel{

	JList listeObjets;
	JPopupMenu popup;  
	DefaultListModel listModel;
	PanelObjets panelObjets;
	
	
	public PanelListeObjets (PanelObjets panelObjets) {
		
		this.panelObjets = panelObjets;
		
		this.setLayout(new BorderLayout());
		
		setupList();
		
		this.add(listeObjets , BorderLayout.CENTER);
		
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Liste d'objets");
		bordure.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(bordure);
		
	}


	private void setupList() {
		
		listModel = new DefaultListModel();
		listeObjets = new JList(listModel);

		for (int i = 0; i < Configuration.objets.size(); i++){
			listModel.addElement(Configuration.objets.get(i));
		}
		
		listeObjets.addMouseListener(new MouseListeObjets(this));
		listeObjets.setCellRenderer(new ListeObjetsRenderer());
	}
	
	
	
	public void addObjet(Objet o){
		((javax.swing.DefaultListModel)listeObjets.getModel()).addElement(o);	
	}
	
	public JList getListeObjets() {
		return listeObjets;
	}


	public PanelObjets getPanelObjets() {
		return panelObjets;
	}
	
	
	
}
