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

package edu.turtlekit2.tools.chart;

import javax.swing.JComponent;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

/**
 * The turtlekit Population Charter Agent. Can be extended or called to trace data on Pheromones.
 * <p>XML Attributes :</p>
 *  <p>Role: trace data on the population for the given role. i.e.:Role="prey,predator"</p>
 * <p></p>
 * @author G. Beurier
 * @version 0.1 - 2/2010
 * @deprecated use {@link LineCharter} instead
 * @see LineCharter
 */
public class PopulationCharter extends Viewer{
	private static final long serialVersionUID = 1L;
	
	int step = 0;
	String[] roles;
	ChartWindow chart;
	double var[];
	
	@Override
	public void setup() {
		String role = getAttrib().getString("Role"); 
		roles = role.split(",");
		var = new double[roles.length];
		
		chart = new ChartWindow();
		this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
				new GUIMessage<JComponent>(chart, SimulationBoard.VIEWER_ZONE, role + " chart(s)"));
		
		chart.addChart("Population", "Step", "Population");
		for (int i = 0; i < roles.length; i++) {
			chart.addSerie("Population", roles[i]);
		}
		
		
	}
	
	public void watch(){
		step++;
		for (int i = 0; i < roles.length; i++) {
			var[i] = this.getAgentsWithRole(
					Tk2Launcher.COMMUNITY, getSimulationGroup(), roles[i]).length;
			chart.update("Population", step, var);
		}
	}
}
