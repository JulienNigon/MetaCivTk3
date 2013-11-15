package civilisation.inspecteur.simulation.environnement;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import civilisation.individu.plan.action.Action;
import civilisation.world.Terrain;

public class ListeTerrainsRenderer  extends DefaultListCellRenderer{


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

	       this.setText(((Terrain) value).getNom() +" : "+  ((Terrain) value).getPassabilite());
	       this.setBackground(((Terrain) value).getCouleur());
            
	        return this;
	    }
}
