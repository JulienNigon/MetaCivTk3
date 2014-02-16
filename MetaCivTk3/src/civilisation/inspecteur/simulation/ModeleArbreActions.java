package civilisation.inspecteur.simulation;

import javax.swing.tree.*;

import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;

public class ModeleArbreActions extends DefaultTreeModel
{
	NodeArbreActions root;

	public ModeleArbreActions(NPlan plan)
	{
		super(new NodeArbreActions("ROOT"));
		root = (NodeArbreActions) getRoot();
		

		for (int j = 0; j < plan.getActions().size(); j++)
		{
			addAction(root , plan.getActions().get(j));
		}
	}
	
	protected void addAction(NodeArbreActions parent , Action action){		
		NodeArbreActions node = new NodeArbreActions(action);
		if (!action.getListeActions().isEmpty()){
			for (int i = 0; i < action.getListeActions().size(); i++){
				addAction(node , action.getListeActions().get(i));
			}
		}
		parent.add(node);
	}
	
	public void ajouterNoeud(NodeArbreActions parent)
	{
		//NodeArbreActions node = new NodeArbreActions("enfant");
		//parent.add(node);
	}
	
	public void supprimerNoeud(NodeArbreActions cible)
	{
		this.removeNodeFromParent(cible);

	}

	
	
}
