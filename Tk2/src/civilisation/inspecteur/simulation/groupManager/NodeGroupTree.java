package civilisation.inspecteur.simulation.groupManager;

import javax.swing.tree.DefaultMutableTreeNode;

import civilisation.GroupModel;
import civilisation.individu.plan.action.Action;

public class NodeGroupTree extends DefaultMutableTreeNode{


	boolean root;
	GroupModel gm;

	public NodeGroupTree(GroupModel gm)
	{
		super(gm);
		root = true;
		this.gm = gm;
	}

	public NodeGroupTree(String s)
	{
		super(s);
		root = false;
	}



	public GroupModel getGm() {
		return gm;
	}

	public void setGm(GroupModel gm) {
		this.gm = gm;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	@Override
	public boolean isRoot() {
		return root;
	}
	
	
	
	
}
