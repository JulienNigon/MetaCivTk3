package civilisation.inspecteur.simulation.objets;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import civilisation.inventaire.Objet;

import civilisation.world.Terrain;

public class ListeObjetsRenderer  extends DefaultListCellRenderer{


	    public Component getListCellRendererComponent(JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus)
	    {

	        super.getListCellRendererComponent( list,
	                 value,
	                 index,
	                 isSelected,
	                 cellHasFocus);
	        
	       Objet o = (Objet) value;

	       this.setText(o.getNom());
	      // this.setBackground(((Terrain) value).getCouleur());
           
   			setIcon(o.getIcone());
	       
	        return this;
	    }
}
