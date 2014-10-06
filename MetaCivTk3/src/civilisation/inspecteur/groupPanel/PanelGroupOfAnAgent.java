package civilisation.inspecteur.groupPanel;

import java.util.ArrayList;
import java.util.Set;

import civilisation.group.Group;
import civilisation.individu.Humain;
import civilisation.inspecteur.animations.JJPanel;

public class PanelGroupOfAnAgent extends JJPanel{

	final static double gapBetweenGroup = 10;
	final static double heightOfGroup = 30;
	
	Humain h;
	ArrayList<GGroup> gGroups = new ArrayList<GGroup>();
	
	public PanelGroupOfAnAgent (Humain h) {
		super();
		this.h = h;
		
		this.add(new GMyselfInGroups(this, 10,10,20,500));
		
		int iterations = 0;
		
		Set<Group> groups = h.getEsprit().getGroups().keySet();
		for (Group group : groups) {
			gGroups.add(new GGroup(this, 5,35 + (gapBetweenGroup + heightOfGroup) * iterations, 200, heightOfGroup, group));
			this.add(gGroups.get(iterations));

			iterations++;
		}

	}
	
}
