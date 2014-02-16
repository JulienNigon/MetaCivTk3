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
			    this.setText(((NodeArbreActions) value).getAction().toString());
			    this.setIcon(((NodeArbreActions) value).getAction().getIcon());
			   
			    String formatedString = ((NodeArbreActions) value).getAction().toFormatedString();
			    String toolTip =
			    		"<html>"
					    + "<b>"
					 	+ ((NodeArbreActions) value).getAction().toString()
					    + "</b>"
					    + "<br>"
			    		;
			    
			    for (int i = 1 ; i < formatedString.split(",").length ; i++) {
			    	toolTip += formatedString.split(",")[i];
			    	toolTip += "<br>";
			    }
			    
			    toolTip += "<br>";
			    
			    toolTip += "<FONT COLOR=\"#990099\">";
			    
			    toolTip += ((NodeArbreActions) value).getAction().getInfo().split(" : ")[1].split("</html>")[0];
			    toolTip += "</FONT>";
			    
			    toolTip += "</html>";
			    
			    this.setToolTipText(toolTip);



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
