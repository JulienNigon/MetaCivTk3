package turtlekit.pvequalsnrt;


import java.awt.Color;

import turtlekit.viewer.TKDefaultViewer;

public class GasViewer extends TKDefaultViewer {
	
	/**
	 * Just to do some initialization work
	 */
	@Override
	protected void activate() {
//		setSynchronousPainting(false);
		super.activate();
		int wallX = Integer.parseInt(getMadkitProperty("wallX"));
		for (int i = 0; i < getHeight(); i++) {
			getPatch(wallX, i).setColor(Color.WHITE);
			getPatch(0, i).setColor(Color.WHITE);
			getPatch(getWidth()-1, i).setColor(Color.WHITE);
		}
		for (int i = 0; i < getWidth(); i++) {
			getPatch(i, 0).setColor(Color.WHITE);
			getPatch(i, getHeight()-1).setColor(Color.WHITE);
		}
		getPatch(wallX, getHeight()/2).setColor(Color.BLACK);
//		getPatch(wallX, 1+getHeight()/2).setColor(Color.BLACK);
	}
	

}
