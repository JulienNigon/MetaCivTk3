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

import civilisation.group.GroupModel;
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
	PanelGroupManager panelGroupManager;

	public PanelGroupTree(PanelGroupManager panelGroupManager){
		
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(300,500));
		this.panelGroupManager = panelGroupManager;
		setupTree();
	}
	
	private void setupTree(){
		groupTree = new JTree(new GroupTreeModel());
		groupTree.setRootVisible(false);
		GroupTreeRenderer renderer = new GroupTreeRenderer();
		groupTree.setCellRenderer(renderer);
		groupTree.addMouseListener(new MouseGroupTreeListener(this));
		groupTree.setBackground(this.getBackground());
		this.add(groupTree , BorderLayout.CENTER);
	}
	
	public void changeSelection(GroupModel gm) {
		panelGroupManager.changeSelection(gm);
	}

	public JTree getGroupTree() {
		return groupTree;
	}

	public void setGroupTree(JTree groupTree) {
		this.groupTree = groupTree;
	}

	public void createGroup(String text) {
		// TODO Auto-generated method stub
		
	}
	
	
}
