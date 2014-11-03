package civilisation.group;

import civilisation.Configuration;

/**
 * Just a convenient class to load, save and use a combination of a groupModel and a role
 */

public class GroupAndRole {
	
	GroupModel groupModel;
	String role;
	
	public GroupAndRole (String s) {
		String g = s.split(":")[0];
		role = s.split(":")[1];
		groupModel = Configuration.getGroupModelByName(g);
	}
	
	public GroupAndRole (Group g , String r) {
		groupModel = g.getGroupModel();
		role = r;
	}

	public GroupModel getGroupModel() {
		return groupModel;
	}

	public void setGroupModel(GroupModel groupModel) {
		this.groupModel = groupModel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
}
