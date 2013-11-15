package civilisation.inspecteur.simulation;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import civilisation.individu.plan.action.Action;

public class ArbreActionsRenderer  extends DefaultTreeCellRenderer{


	    public Component getTreeCellRendererComponent(JTree tree,
	        Object value, boolean selected, boolean expanded,
	        boolean leaf, int row, boolean hasFocus){

	        super.getTreeCellRendererComponent(tree, value,
	        selected, expanded, leaf, row, hasFocus);

	        if (((NodeArbreActions) value).isRoot()){
			    this.setText((((NodeArbreActions) value).getAction().toString()));
			    this.setIcon(((NodeArbreActions) value).getAction().getIcon());
	        }

            setToolTipText("Test test etst.");

            //System.out.println("BIP");
            
	        return this;
	    }
}
