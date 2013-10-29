package civilisation.inspecteur.simulation.environnement;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import civilisation.world.Terrain;

public class ListeTerrainsRenderer  extends DefaultListCellRenderer{


	    @Override
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
