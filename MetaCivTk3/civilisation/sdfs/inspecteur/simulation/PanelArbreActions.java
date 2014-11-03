package civilisation.inspecteur.simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import civilisation.Configuration;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction.Option_BeforeAfter;

public class PanelArbreActions extends JJPanel{

	JTree arbreActions;
	NPlan plan;
	JToolBar toolBar = new JToolBar();
	JPopupMenu popup;
	Action actionActive; /*Pour m_moriser l'action en cours de modification, le cas _cheant*/
	NodeArbreActions nodeActionActive;
	ModeleArbreActions treeModel;

	public PanelArbreActions(NPlan plan){
		
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(300,500));

		if (plan != null){

			this.plan = plan;
			//System.out.println("Plan obersv_ : " + this.plan.getNom());
			setupArbreActions();

			
			this.add(toolBar , BorderLayout.NORTH);
		} else {
			//System.out.println("Plan de l'arbre est null");
		}
	}
	
	private void setupArbreActions(){
		treeModel = new ModeleArbreActions(plan);
		arbreActions = new JTree(treeModel);
		arbreActions.setRootVisible(false);
		ArbreActionsRenderer renderer = new ArbreActionsRenderer();
		arbreActions.setCellRenderer(renderer);
		arbreActions.addMouseListener(new MouseArbreActionsListener(this));
		arbreActions.setBackground(this.getBackground());
		this.add(arbreActions , BorderLayout.CENTER);
		javax.swing.ToolTipManager.sharedInstance().registerComponent(arbreActions);

	}
	
	public void changePlan(NPlan plan) {
		if (arbreActions != null) {
			this.remove(arbreActions);
		}
		System.out.println("Nouveau plan : " + plan.getNom());
		this.plan = plan;
		setupArbreActions();
	}
	
	public void afficherPopup(MouseEvent e, Action a){
		
		NodeArbreActions parentNode = ((NodeArbreActions)(((NodeArbreActions) this.getArbreActions().getPathForLocation(e.getX(), e.getY()).getLastPathComponent()).getParent()));
		
		popup = new JPopupMenu("Action");
		JMenuItem editerAction = new JMenuItem("Editer l'action");
		editerAction.addActionListener(new ActionsMenuActions(this,0,a));
		editerAction.setIcon(Configuration.getIcon("pencil.png"));
		popup.add(editerAction);
		
		
		boolean peutRecevoirAction = false;
		if (parentNode.equals(arbreActions.getModel().getRoot())){
			peutRecevoirAction = true;
		}else if(parentNode.getAction().getNumberActionSlot() > parentNode.getAction().getListeActions().size() || parentNode.getAction().getNumberActionSlot() == -1){
			peutRecevoirAction = true;
		}
		
		if (peutRecevoirAction){
			JMenuItem ajouterActionApres = new JMenuItem("Ajouter une action apr_s");
			ajouterActionApres.addActionListener(new ActionsMenuActions(this,1,a));
			ajouterActionApres.setIcon(Configuration.getIcon("pencil.png"));
			popup.add(ajouterActionApres);
			JMenuItem ajouterActionAvant = new JMenuItem("Ajouter une action avant");
			ajouterActionAvant.addActionListener(new ActionsMenuActions(this,2,a));
			ajouterActionAvant.setIcon(Configuration.getIcon("pencil.png"));
			popup.add(ajouterActionAvant);
		}
		
		if (a.getNumberActionSlot() == -1 || a.getNumberActionSlot() > a.getListeActions().size()){
			JMenuItem ajouterActionInterne = new JMenuItem("Ajouter une action interne");
			ajouterActionInterne.addActionListener(new ActionsMenuActions(this,3,a));
			ajouterActionInterne.setIcon(Configuration.getIcon("pencil.png"));
			popup.add(ajouterActionInterne);
		}
		
		JMenuItem removeAction = new JMenuItem("Remove action");
		removeAction.addActionListener(new ActionsMenuActions(this,4,a));
		removeAction.setIcon(Configuration.getIcon("cross.png"));
		popup.add(removeAction);
		
		popup.show(this, this.getX() + e.getX(), this.getY() + e.getY());
	}

	public JTree getArbreActions() {
		return arbreActions;
	}

	public NPlan getPlan() {
		return plan;
	}
	
	public void addNewAction(Action a, Option_BeforeAfter option){
		if (option == Option_BeforeAfter.AFTER){
			plan.addActionAfter(a, actionActive);
			System.out.println("plan : " + plan.getNom() + " " + nodeActionActive.toString());
			treeModel.insertNodeInto(new NodeArbreActions(a), (MutableTreeNode) nodeActionActive.getParent(), nodeActionActive.getParent().getIndex(nodeActionActive)+1);
		}
		else if (option == Option_BeforeAfter.BEFORE){
			plan.addActionBefore(a, actionActive);
			treeModel.insertNodeInto(new NodeArbreActions(a), (MutableTreeNode) nodeActionActive.getParent(), nodeActionActive.getParent().getIndex(nodeActionActive));
		}
		else if (option == Option_BeforeAfter.INTERNAL){
			plan.addSubAction(a, actionActive);
			treeModel.insertNodeInto(new NodeArbreActions(a),nodeActionActive,0);
		}
		else if (option == Option_BeforeAfter.FIRST){
			if (plan == null) System.out.println("NULL");
			System.out.println("plan : " + plan.getNom() + ((NodeArbreActions)treeModel.getRoot()).getChildCount());
			plan.addFirstAction(a);
			NodeArbreActions newNode = new NodeArbreActions(a);
			treeModel.insertNodeInto(newNode,(NodeArbreActions)treeModel.getRoot(),0);
			treeModel.reload((TreeNode) treeModel.getRoot());
			//TODO : don't close tree
		}
		//this.remove(arbreActions);
		//setupArbreActions();
	}

	public Action getActionActive() {
		return actionActive;
	}

	public void setActionActive(Action actionActive) {
		this.actionActive = actionActive;
	}

	public void remove(Action selectedAction) {
		plan.removeAction(selectedAction);
		this.remove(arbreActions);
		setupArbreActions();
	}

	public NodeArbreActions getNodeActionActive() {
		return nodeActionActive;
	}

	public void setNodeActionActive(NodeArbreActions nodeActionActive) {
		this.nodeActionActive = nodeActionActive;
	}


	
	
	
}
