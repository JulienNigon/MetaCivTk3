package civilisation;

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
