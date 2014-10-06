package turtlekit.termites;


import java.awt.Color;

import turtlekit.kernel.Patch;
import turtlekit.viewer.TKDefaultViewer;

public class TermiteViewer extends TKDefaultViewer {
	
	/**
	 * Just to do some initialization work
	 */
	@Override
	protected void activate() {
		super.activate();
//		setSynchronousPainting(false);// fastest display mode
		double densityRate = 0.5;
		for (Patch patch : getPatchGrid()) {
			if (Math.random() < densityRate){
				patch.setColor(Color.yellow);}
			else
				patch.setColor(Color.black);
		}
	}

}
