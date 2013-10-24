package civilisation.inspecteur.simulation;

import javax.swing.tree.DefaultMutableTreeNode;

import civilisation.individu.plan.action.Action;

public class NodeArbreActions extends DefaultMutableTreeNode{

	float xPrelim;
	float modifier;
	boolean root;
	Action action;

	public NodeArbreActions(Action a)
	{
		super(a);
		root = true;
		this.action = a;
	}

	public NodeArbreActions(String s) /*Pour construire la racine*/
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

	public boolean isRoot() {
		return root;
	}
	
	
	
	
}
