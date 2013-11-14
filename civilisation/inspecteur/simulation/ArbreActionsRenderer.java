package civilisation.inspecteur.simulation;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ArbreActionsRenderer  extends DefaultTreeCellRenderer{


	    @Override
		public Component getTreeCellRendererComponent(JTree tree,
	        Object value, boolean selected, boolean expanded,
	        boolean leaf, int row, boolean hasFocus){

	        super.getTreeCellRendererComponent(tree, value,
	        selected, expanded, leaf, row, hasFocus);

	        if (((NodeArbreActions) value).isRoot()){
			    this.setText((((NodeArbreActions) value).getAction().toString() + (((NodeArbreActions) value).getAction().getOptions().toString())));
			    this.setIcon(((NodeArbreActions) value).getAction().getIcon());



	        }
		    this.setBackground(tree.getBackground());
		    this.setBackgroundNonSelectionColor(tree.getBackground());

		    if (((NodeArbreActions) value).getAction().isLogical()) {
			    this.setBackground(Color.gray);
			    this.setBackgroundNonSelectionColor(Color.gray);
		    }
		    


            //System.out.println("BIP");
            
	        return this;
	    }
}
