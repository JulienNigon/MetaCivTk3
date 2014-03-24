package civilisation.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import madkit.kernel.Agent;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Culturon;
import civilisation.individu.cognitons.CCogniton;
import civilisation.individu.cognitons.NCogniton;
import turtlekit.kernel.Turtle;
import turtlekit.kernel.Patch;

public class Group extends Turtle
{

	Group parent;
	GroupModel groupModel;
	Patch position;
	HashMap<String,ArrayList<CCogniton>> rolesAndCulturons = new HashMap<String,ArrayList<CCogniton>>();
	static HashMap<GroupModel,ArrayList<Group>> allGroups = new HashMap<GroupModel,ArrayList<Group>>();
	ArrayList<Humain> members = new ArrayList<Humain>();

	public Group() {
	}
	
	public void activate() {
		super.activate();
		playRole(groupModel.getName());
		playRole("Group");
	}
	
	public String live(){
		if (members.size() == 0) {
			this.killAgent(this);
		}
		return "live";
	}
	
	public Group(Group parent , GroupModel groupModel, Patch position){
		
		super("live"); //Behavior of a group as a turtle
		
		this.parent = parent;
		this.groupModel = groupModel;
		this.position = position;
		

	    Iterator<String> iterator = groupModel.getCulturons().keySet().iterator();
	    while(iterator.hasNext()) {
	    	String role = iterator.next();
	    	ArrayList<CCogniton> culturons = new ArrayList<CCogniton>();
	    	ArrayList<NCogniton> modelCulturons = groupModel.getCulturons().get(role);
	    	for(int i = 0 ; i < modelCulturons.size() ; i++) {
	    		culturons.add(new CCogniton(modelCulturons.get(i)));
	    	}
	    	this.setRole(role, culturons);
	    	
	    }
	    		
	}
	
	public void applyCulturons(String role , Esprit e){
		ArrayList<CCogniton> c = rolesAndCulturons.get(role);
		for (int i = 0 ; i < c.size() ; i++){
			c.get(i).appliquerPoids(e);
		}
		if (parent != null) parent.applyCulturons(role, e);
	}
	
	public void setupCulturons(String role , Esprit e){
		ArrayList<CCogniton> c = rolesAndCulturons.get(role);
		//System.out.println(c.size() + " " + c.get(0).getCogniton().getNom());
		for (int i = 0 ; i < c.size() ; i++){
			c.get(i).mettreEnPlace(e);
		}
		if (parent != null) parent.setupCulturons(role, e);
	}

	public void setRole(String role){
		rolesAndCulturons.put(role , null);
	}
	
	public void setRole(String role , ArrayList<CCogniton> newCulturons){
		rolesAndCulturons.put(role , newCulturons);
	}
	
	public void addCulturonToRole(String role , CCogniton culturon){
		if (!rolesAndCulturons.containsKey(role)) {
			ArrayList<CCogniton> lc = new ArrayList<CCogniton>();
			lc.add(culturon);
			rolesAndCulturons.put(role , lc);
		} else {
			rolesAndCulturons.get(role).add(culturon);
		}
	}
	
	public void joinGroup(Esprit e, String role) {
		e.getGroups().put(this,role);
		setupCulturons(role, e);
		members.add(e.getHumain());
	}
	
	public void leaveGroup(Esprit e) {
		e.getGroups().remove(this);
		//setupCulturons(role, e);
		if (members.remove(e.getHumain())) System.out.println("agent removed , reste : " + members.size());
	}
	
	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	public GroupModel getGroupModel() {
		return groupModel;
	}

	public void setGroupModel(GroupModel groupModel) {
		this.groupModel = groupModel;
	}

	public Patch getPosition() {
		return position;
	}

	public void setPosition(Patch position) {
		this.position = position;
	}
	
	
	
}
