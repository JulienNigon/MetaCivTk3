package civilisation.individu.cognitons;

import civilisation.Group;


/**
 * Work in progress...
 * @author juliennigon
 *
 */


public class CCulturon {

	
	Culturon culturon;
	Group group;
	
	
	
	public CCulturon(Culturon culturon, Group group) {
		super();
		this.culturon = culturon;
		this.group = group;
	}
	
	
	public Culturon getCulturon() {
		return culturon;
	}
	public void setCulturon(Culturon culturon) {
		this.culturon = culturon;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
