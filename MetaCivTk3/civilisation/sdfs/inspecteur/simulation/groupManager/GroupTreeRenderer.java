package civilisation.inspecteur.simulation.groupManager;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import civilisation.group.GroupModel;

public class GroupTreeRenderer  extends DefaultTreeCellRenderer{


	    @Override
		public Component getTreeCellRendererComponent(JTree tree,
	        Object value, boolean selected, boolean expanded,
	        boolean leaf, int row, boolean hasFocus){

	        super.getTreeCellRendererComponent(tree, value,
	        selected, expanded, leaf, row, hasFocus);
	        
	        GroupModel gm = ((NodeGroupTree) value).getGm();

		    this.setBackground(tree.getBackground());
		    this.setBackgroundNonSelectionColor(tree.getBackground());

		    this.setText(gm.getName());

		    
		    


            //System.out.println("BIP");
            
	        return this;
	    }
}
