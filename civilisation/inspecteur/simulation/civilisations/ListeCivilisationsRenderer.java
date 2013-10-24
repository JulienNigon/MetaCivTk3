package civilisation.inspecteur.simulation.civilisations;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import civilisation.Civilisation;
import civilisation.inventaire.Objet;

import civilisation.world.Terrain;

public class ListeCivilisationsRenderer  extends DefaultListCellRenderer{


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
	        
	       Civilisation c = (Civilisation) value;

	       this.setText(c.getNom());
	      // this.setBackground(((Terrain) value).getCouleur());
	       
	        return this;
	    }
}
