package civilisation.inspecteur.groupPanel;

import civilisation.group.Group;
import civilisation.inspecteur.animations.JJPanel;

public class PanelGroup extends JJPanel{

	Group g;

	public PanelGroup(Group g) {
		super();
		this.g = g;
		
		this.add(new GMyselfInGroups(this, 100,100,30,800));
		
	}
	
	
	
}
