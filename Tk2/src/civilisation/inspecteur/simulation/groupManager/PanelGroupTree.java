package civilisation.inspecteur.simulation.groupManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.JTree;

import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.ActionsMenuActions;
import civilisation.inspecteur.simulation.ArbreActionsRenderer;
import civilisation.inspecteur.simulation.ModeleArbreActions;
import civilisation.inspecteur.simulation.MouseArbreActionsListener;
import civilisation.inspecteur.simulation.NodeArbreActions;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction.Option_BeforeAfter;

public class PanelGroupTree  extends JJPanel{

	JTree groupTree;
	JPopupMenu popup;

	public PanelGroupTree(){
		
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(300,500));
	
		setupTree();
	}
	
	private void setupTree(){
		groupTree = new JTree(new GroupTreeModel());
		groupTree.setRootVisible(false);
		ArbreActionsRenderer renderer = new ArbreActionsRenderer();
	//	arbreActions.setCellRenderer(renderer);
	//	arbreActions.addMouseListener(new MouseArbreActionsListener(this));
		groupTree.setBackground(this.getBackground());
		this.add(groupTree , BorderLayout.CENTER);
	}
	
/*	public void changePlan(NPlan plan) {
		if (arbreActions != null) {
			this.remove(arbreActions);
		}
		System.out.println("Nouveau plan : " + plan.getNom());
		this.plan = plan;
	//	setupArbreActions();
	}*/
	
/*	public void afficherPopup(MouseEvent e, Action a){
		
		NodeArbreActions parentNode = ((NodeArbreActions)(((NodeArbreActions) this.getArbreActions().getPathForLocation(e.getX(), e.getY()).getLastPathComponent()).getParent()));
		
		popup = new JPopupMenu("Action");
		JMenuItem editerAction = new JMenuItem("Editer l'action");
	//	editerAction.addActionListener(new ActionsMenuActions(this,0,a));
		editerAction.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
		popup.add(editerAction);
		
		
		boolean peutRecevoirAction = false;
		if (parentNode.equals(arbreActions.getModel().getRoot())){
			peutRecevoirAction = true;
		}else if(parentNode.getAction().getNumberActionSlot() > parentNode.getAction().getListeActions().size() || parentNode.getAction().getNumberActionSlot() == -1){
			peutRecevoirAction = true;
		}
		
		if (peutRecevoirAction){
			JMenuItem ajouterActionApres = new JMenuItem("Ajouter une action aprs");
	//		ajouterActionApres.addActionListener(new ActionsMenuActions(this,1,a));
			ajouterActionApres.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
			popup.add(ajouterActionApres);
			JMenuItem ajouterActionAvant = new JMenuItem("Ajouter une action avant");
	//		ajouterActionAvant.addActionListener(new ActionsMenuActions(this,2,a));
			ajouterActionAvant.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
			popup.add(ajouterActionAvant);
		}
		
		if (a.getNumberActionSlot() == -1 || a.getNumberActionSlot() > a.getListeActions().size()){
			JMenuItem ajouterActionInterne = new JMenuItem("Ajouter une action interne");
	//		ajouterActionInterne.addActionListener(new ActionsMenuActions(this,3,a));
			ajouterActionInterne.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
			popup.add(ajouterActionInterne);
		}
		
		JMenuItem removeAction = new JMenuItem("Remove action");
	//	removeAction.addActionListener(new ActionsMenuActions(this,4,a));
		removeAction.setIcon(new ImageIcon(this.getClass().getResource("../icones/cross.png")));
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
		}
		else if (option == Option_BeforeAfter.BEFORE){
			plan.addActionBefore(a, actionActive);
		}
		else if (option == Option_BeforeAfter.INTERNAL){
			plan.addSubAction(a, actionActive);
		}
		else if (option == Option_BeforeAfter.FIRST){
			if (plan == null) System.out.println("NULL");
			System.out.println("plan : " + plan.getNom());
			plan.addFirstAction(a);
		}
		this.remove(arbreActions);
//		setupArbreActions();
		plan.seDecrire();
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
//		setupArbreActions();
	}
	
	*/
	
	
}
