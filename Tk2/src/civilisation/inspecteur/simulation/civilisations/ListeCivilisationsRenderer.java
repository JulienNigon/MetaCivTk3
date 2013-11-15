package civilisation.inspecteur.simulation.civilisations;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import civilisation.Civilisation;

public class ListeCivilisationsRenderer  extends DefaultListCellRenderer{


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
	        
	       Civilisation c = (Civilisation) value;

	       this.setText(c.getNom());
	      // this.setBackground(((Terrain) value).getCouleur());
	       
	        return this;
	    }
}
