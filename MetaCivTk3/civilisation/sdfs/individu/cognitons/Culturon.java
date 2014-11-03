package civilisation.individu.cognitons;

import civilisation.group.Group;


/**
 * Culturons are special cognitons used for group management.
 * Allow to recreate normative and cultural behavior.
 */


public class Culturon {

	
	TypeCulturon typeCulturon;
	Group group;
	
	
	
	public Culturon(TypeCulturon typeCulturon, Group group) {
		super();
		this.typeCulturon = typeCulturon;
		this.group = group;
	}
	
	
	public TypeCulturon getCulturon() {
		return typeCulturon;
	}
	public void setCulturon(TypeCulturon typeCulturon) {
		this.typeCulturon = typeCulturon;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
