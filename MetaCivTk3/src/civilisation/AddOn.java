package civilisation;


import turtlekit.kernel.Turtle;
import madkit.kernel.Agent;

@SuppressWarnings("serial")
public class AddOn extends Turtle{
	
	
	public AddOn() {
		super("addOnEffect");
		System.out.println("New");
	}
	

	public String addOnEffect() {
		System.out.println("bip");
		return "addOnEffect";
	}

	

}
