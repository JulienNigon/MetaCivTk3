package civilisation.inspecteur.simulation.objets;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import civilisation.inventaire.Objet;

public class ListeObjetsRenderer  extends DefaultListCellRenderer{


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
	        
	       Objet o = (Objet) value;

	       this.setText(o.getNom());
	      // this.setBackground(((Terrain) value).getCouleur());
           
   			setIcon(o.getIcone());
	       
	        return this;
	    }
}
