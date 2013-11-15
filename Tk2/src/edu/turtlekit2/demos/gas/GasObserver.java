/*
 * TurtleKit - An Artificial Life Simulation Platform
 * Copyright (C) 2000-2010 Fabien Michel, Gregory Beurier
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package edu.turtlekit2.demos.gas;

import java.awt.Color;

import javax.swing.JComponent;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.agents.TurtleProbe;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.tools.chart.ChartWindow;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;


/**  
 * Adapted from the gas simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010
 * 
 * This agent watch the gas simulation (nb of turtles on right or left) 
  @author Fabien MICHEL
  @version 1.1 6/12/1999 */

@SuppressWarnings("serial")
public class GasObserver extends Viewer
{
	TurtleProbe allTurtles;
	int wall,holeSize;
	double[] t = new double[2];
	int step = 0;
	ChartWindow chart;
	@Override

	public void setup() {
		//paint the box
		for(int i=0;i<patchGrid[0].length;i++)
			patchGrid[getAttrib().getInt("wall")][i].setColor(Color.white);

		for (int i=0;i < getAttrib().getInt("holesize");i++)
			patchGrid[getAttrib().getInt("wall")][envHeight/2-getAttrib().getInt("holesize")/2+i].setColor(Color.black);

		allTurtles = new TurtleProbe(getSimulationGroup(),"turtle");
		addProbe(allTurtles);
		chart = new ChartWindow();
		chart.addChart("Population", "Step", "Population");
		chart.addSerie("Population", "Right");
		chart.addSerie("Population", "Left");
		this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
				new GUIMessage<JComponent>(chart, SimulationBoard.VIEWER_ZONE, "Gaz Chart"));
	}

	public void watch(){
		step++;
		int cpt = 0;
		//computing how many turtles are on the right side of the wall
		for (final Turtle t : allTurtles.getCurrentAgentsList()) {
			if (t.xcor()>= ((Gas)t).wall)
				cpt++;
		}
		t[0] = cpt;
		t[1] = allTurtles.getTurtles().length - cpt;
		chart.update("Population",step,t);
	}

}
