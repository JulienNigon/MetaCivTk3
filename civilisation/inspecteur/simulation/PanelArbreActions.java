package civilisation.inspecteur.simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

import civilisation.Configuration;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerAction;
import civilisation.inspecteur.simulation.dialogues.DialogueAjouterAction.Option_BeforeAfter;

public class PanelArbreActions extends JJPanel{

	JTree arbreActions;
	NPlan plan;
	JToolBar toolBar = new JToolBar();
	JPopupMenu popup;
	Action actionActive; /*Pour mŽmoriser l'action en cours de modification, le cas Žcheant*/

	public PanelArbreActions(NPlan plan){
		if (plan != null){
			this.setLayout(new BorderLayout());

			this.plan = plan;
			this.setMinimumSize(new Dimension(300,500));
			
			setupArbreActions();

			
			this.add(toolBar , BorderLayout.NORTH);
		}
	}
	
	private void setupArbreActions(){
		arbreActions = new JTree(new ModeleArbreActions(plan));
		arbreActions.setRootVisible(false);
		ArbreActionsRenderer renderer = new ArbreActionsRenderer();
		arbreActions.setCellRenderer(renderer);
		arbreActions.addMouseListener(new MouseArbreActionsListener(this));
		arbreActions.setBackground(this.getBackground());
		this.add(arbreActions , BorderLayout.CENTER);
	}
	
	public void afficherPopup(MouseEvent e, Action a){
		
		NodeArbreActions parentNode = ((NodeArbreActions)(((NodeArbreActions) this.getArbreActions().getPathForLocation(e.getX(), e.getY()).getLastPathComponent()).getParent()));
		
		popup = new JPopupMenu("Action");
		JMenuItem editerAction = new JMenuItem("Editer l'action");
		editerAction.addActionListener(new ActionsMenuActions(this,0,a));
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
			ajouterActionApres.addActionListener(new ActionsMenuActions(this,1,a));
			ajouterActionApres.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
			popup.add(ajouterActionApres);
			JMenuItem ajouterActionAvant = new JMenuItem("Ajouter une action avant");
			ajouterActionAvant.addActionListener(new ActionsMenuActions(this,2,a));
			ajouterActionAvant.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
			popup.add(ajouterActionAvant);
		}
		
		if (a.getNumberActionSlot() == -1 || a.getNumberActionSlot() > a.getListeActions().size()){
			JMenuItem ajouterActionInterne = new JMenuItem("Ajouter une action interne");
			ajouterActionInterne.addActionListener(new ActionsMenuActions(this,3,a));
			ajouterActionInterne.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
			popup.add(ajouterActionInterne);
		}
		
		popup.show(this, (int)this.getX() + e.getX(), (int)this.getY() + e.getY());
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
		this.remove(arbreActions);
		setupArbreActions();
		plan.seDecrire();
	}

	public Action getActionActive() {
		return actionActive;
	}

	public void setActionActive(Action actionActive) {
		this.actionActive = actionActive;
	}
	
	
	
	
}
