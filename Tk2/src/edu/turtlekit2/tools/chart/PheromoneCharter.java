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
 * The turtlekit Pheromone Charter Agent. Can be extended or called to trace data on Pheromones.
 * <p>XML Attributes :</p>
 * <p>Pheromone: trace quantities of the given pheromones (Min - Average - Max). i.e.: Pheromone="sugar"</p>
 * <p></p>
 * @author G. Beurier
 * @version 0.1 - 2/2010
 * @deprecated use {@link LineCharter} instead
 * @see LineCharter
 */
public class PheromoneCharter extends Viewer{
	private static final long serialVersionUID = 1L;
	
	//First line: Average
	//Second line: Min
	//Third line: Max
	
	int step = 0;
	String[] phero;
	ChartWindow chart;
	double var[] = new double[3];
	
	@Override
	public void setup() {
		String pheromones = getAttrib().getString("Pheromone"); 
		phero = pheromones.split(",");
		chart = new ChartWindow();
		this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
				new GUIMessage<JComponent>(chart, SimulationBoard.VIEWER_ZONE, pheromones + " chart(s)"));
		
		for (int i = 0; i < phero.length; i++) {
			chart.addChart(phero[i], "Step", "Quantity");
			chart.addSerie(phero[i], "Min");
			chart.addSerie(phero[i], "Average");
			chart.addSerie(phero[i], "Max");
		}
	}
	
	public void watch(){
		step++;
		for (int i = 0; i < phero.length; i++) {
			var[0] = Double.POSITIVE_INFINITY;
			var[1] = 0;
			var[2] = 0; 
			for (int l = 0; l < envHeight; l++) {
				for (int j = 0; j < envHeight; j++) {
					double pheroQ = patchGrid[l][j].smell(phero[i]);
					var[1] += pheroQ;
					if(pheroQ < var[0]) var[0] = pheroQ;
					if(pheroQ > var[2]) var[2] = pheroQ;
				}
			}
			var[1] /= envHeight * envWidth;
			chart.update(phero[i], step, var);
		}
	}
}
