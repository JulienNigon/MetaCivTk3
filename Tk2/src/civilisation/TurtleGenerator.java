package civilisation;

import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Tortue chargée de créer d'autres tortues.
 * @author DTEAM
 * @version 1.0 - 09/2013
*/

public class TurtleGenerator extends Turtle{

	static TurtleGenerator turtleGenerator;
	
	public TurtleGenerator(){
		turtleGenerator = this;
	}
	
	public static TurtleGenerator getInstance(){
		return turtleGenerator;
	}
}
