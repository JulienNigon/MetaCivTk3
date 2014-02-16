package civilisation;

import turtlekit.kernel.Turtle;


/** 
 * Special turtle used to create other turtles
 * @author DTEAM
 * @version 1.0 - 09/2013
*/

public class TurtleGenerator extends Turtle{

	static TurtleGenerator turtleGenerator;
	
	public TurtleGenerator(){
		turtleGenerator = this;
	}
	
	public void activate() {
		this.moveTo(0, 0);
	}
	
	public static TurtleGenerator getInstance(){
		return turtleGenerator;
	}
	
}
