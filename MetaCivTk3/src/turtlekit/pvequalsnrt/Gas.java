package turtlekit.pvequalsnrt;

import java.awt.Color;

import turtlekit.kernel.Patch;
import turtlekit.kernel.Turtle;
import turtlekit.kernel.TurtleKit.Option;

/**
 *          The turtle "gas" just needs space !!!
 * @author Fabien Michel
 */

@SuppressWarnings("serial")
public class Gas extends Turtle {

	public Gas() {
		super("go");
	}

	/**
	 * 
	 */
	protected void activate() {
		super.activate();
		setColor(Color.cyan);
		randomHeading();
		final String wallValue = getMadkitProperty("wallX");
		if (wallValue != null) {
			int wallX = Integer.parseInt(wallValue);
			moveTo(generator.nextInt(wallX - 1) + 1,
					generator.nextInt(getWorldHeight() - 2) + 1);
		}
	}

	/**
	 * The gas looks for free space (without an other turtle or a wall) but
	 * can't go through the wall (white color patches) and rebounds against the
	 * sides of the world
	 */
	public String go() {
		final Patch nextPatch = getNextPatch();
		if(nextPatch != null){
			if(! nextPatch.isEmpty()){
				randomHeading(180);
			}
			else if(nextPatch.getColor() == Color.WHITE){
				setHeading(getHeading()+100);
				randomHeading(40);
			}
			else{
				step();
			}
		}
		else{
			step();
		}
		return ("go");
	}
	
	public static void main(String[] args) {
			executeThisTurtle(5000
					,Option.envDimension.toString(),"200,50"
					,Option.noWrap.toString()
					,"--wallX","10"
					,Option.viewers.toString(), GasViewer.class.getName()
					+";"+PhysicsChecker.class.getName()
					);
		
	}

}
