package civilisation.inspecteur.groupPanel;

import java.util.ArrayList;

import civilisation.group.Group;
import civilisation.individu.Humain;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

public class GGroup extends JJComponent{

	Group group;
	ArrayList<GGroupMember> members = new ArrayList<GGroupMember>();
	
	public GGroup(JJPanel parent, double xx, double yy, double w, double h, Group group) {
		super(parent, xx, yy, w, h);
		this.group = group;
		setupTheGroup();
	}
	
	public void setupTheGroup() {
		this.add(new GGroupBorder(parent, 0, 0, w, h));
		for (Humain member : group.getMembers()) {
			setupAMember(member);
		}
		
	}

	public void setupAMember(Humain h) {
		GGroupMember newMember = new GGroupMember(parent, 30 + (w*members.size()), 5);
		members.add(newMember);
		this.add(newMember);
	}
}
