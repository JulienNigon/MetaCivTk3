package civilisation.inspecteur.simulation.environnement;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import civilisation.Configuration;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.world.Terrain;

public class PanelTerrains extends JJPanel{

	JList listeTerrains;
	JPopupMenu popup;  
	DefaultListModel listModel;
	  
	public PanelTerrains(){

			this.setLayout(new BorderLayout());
		
			definirListe();
			
			this.add(listeTerrains , BorderLayout.CENTER);
	}


	public JList getListeTerrains() {
		return listeTerrains;
	}


	public void afficherPopup(MouseEvent e) {
		
		popup = new JPopupMenu("Terrain");
		
		JMenuItem editerPhero = new JMenuItem("Editer les pheromones");
		editerPhero.addActionListener(new ActionsMenuTerrain(this,0));
		editerPhero.setIcon(new ImageIcon(this.getClass().getResource("../../icones/pencil.png")));
		popup.add(editerPhero);
		
		JMenuItem editerCouleur = new JMenuItem("Changer la couleur");
		editerCouleur.addActionListener(new ActionsMenuTerrain(this,1));
		editerCouleur.setIcon(new ImageIcon(this.getClass().getResource("../../icones/color--pencil.png")));
		popup.add(editerCouleur);
		
		JMenuItem supprimer = new JMenuItem("Supprimer");
		supprimer.addActionListener(new ActionsMenuTerrain(this,2));
		supprimer.setIcon(new ImageIcon(this.getClass().getResource("../../icones/cross.png")));
		popup.add(supprimer);
		
		popup.show(this, e.getX(), e.getY());		
	}
	
	public void definirListe(){
		
		listModel = new DefaultListModel();
		listeTerrains = new JList(listModel);

		for (int i = 0; i < Configuration.terrains.size(); i++){
			listModel.addElement(Configuration.terrains.get(i));
		}
		
		listeTerrains.addMouseListener(new MouseListeTerrains(this));
		listeTerrains.setCellRenderer(new ListeTerrainsRenderer());
	}
	
	public void addTerrain(Terrain t){
		((javax.swing.DefaultListModel)listeTerrains.getModel()).addElement(t);	
	}

	
	
}
	



	
	
	
