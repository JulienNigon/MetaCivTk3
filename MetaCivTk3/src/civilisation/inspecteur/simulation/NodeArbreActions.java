package civilisation.inspecteur.simulation;

import javax.swing.tree.DefaultMutableTreeNode;

import civilisation.individu.plan.action.Action;

public class NodeArbreActions extends DefaultMutableTreeNode{


	boolean root;
	Action action;

	public NodeArbreActions(Action a)
	{
		super(a);
		root = true;
		this.action = a;
	}

	public NodeArbreActions(String s)
	{
		super(s);
		root = false;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public boolean isRoot() {
		return root;
	}
	
	
	
	
}
