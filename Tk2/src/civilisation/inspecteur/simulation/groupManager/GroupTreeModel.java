package civilisation.inspecteur.simulation.groupManager;

import javax.swing.tree.*;

import civilisation.Configuration;
import civilisation.Group;
import civilisation.GroupModel;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;

public class GroupTreeModel extends DefaultTreeModel
{
	NodeGroupTree root;

	public GroupTreeModel()
	{
		super(new NodeGroupTree("ROOT"));
		root = (NodeGroupTree) getRoot();
		

		for (int j = 0; j < Configuration.groups.size(); j++)
		{
			addGroup(root , Configuration.groups.get(j));
		}
	}
	
	protected void addGroup(NodeGroupTree parent , GroupModel group){		
		NodeGroupTree node = new NodeGroupTree(group);
		parent.add(node);
	}
	
	public void ajouterNoeud(NodeGroupTree parent)
	{
		//NodeArbreActions node = new NodeArbreActions("enfant");
		//parent.add(node);
	}
	
	public void supprimerNoeud(NodeGroupTree cible)
	{
		this.removeNodeFromParent(cible);

	}

	
	
}
