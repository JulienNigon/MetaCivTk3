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

import java.lang.reflect.Field;

import javax.swing.JComponent;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.agents.TurtleProbe;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;



/**
 * The turtlekit Line Charter Agent. Can be extended or called to trace data on Turtles, Pheromones, or Agents Attributes, etc.
 * To trace multiple charts on a single window, multiple XML Attributes can be given or  
 * <p>XML Attributes :</p>
 * <p>Pheromone: trace quantities of the given pheromones (Min - Average - Max). i.e.: Pheromone="sugar"</p>
 * <p>Variable: trace the values (Min - Average - Max) of turtles variables given a role and a variable. i.e.: Variable="prey-age".</p>
 * <p>Role: trace data on the population for the given role. i.e.:Role="prey,predator"</p>
 * <p></p>
 * @note See demos file .xml to have an example of how to use LineCharter 
 * @author G. Beurier
 * @version 0.1 - 2/2010
 * @see PheromoneCharter
 * @see PopulationCharter
 * @see TkCharter
 */
public class LineCharter extends Viewer{
	private static final long serialVersionUID = 1L;

	int step = 0;
	ChartWindow chart;
	
	boolean rolesOn = false;
	double[] rolesValues;
	String[] roles;
	
	boolean pheroOn = false;
	double[] pheroValues = new double[3];
	String[] phero;
	
	boolean variablesOn = false;
	double[] variablesValues = new double[3];
	String[] variables;
	TurtleProbe allTurtles;
	
	@Override
	public void setup() {
		chart = new ChartWindow();
		this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
				new GUIMessage<JComponent>(chart, SimulationBoard.VIEWER_ZONE, "Charter"));
		
		createRolesCharts();
		createPheroCharts();
		createVariablesCharts();
	}

	public void watch(){
		step++;
		if(pheroOn) updatePheroCharts(step);
		if(rolesOn) updateRolesCharts(step);
		if(variablesOn) updateVariablesCharts(step);
	}

	private void createVariablesCharts(){
		String variable = "";
		if(getAttrib().containsKey("variable"))
			variable = getAttrib().getString("variable");
		if(variable.length() > 0) {
			variablesOn = true;
			variables = variable.split(",");
			for (int i = 0; i < variables.length; i++) {
				chart.addChart(variables[i], "Step", "Value");
				chart.addSerie(variables[i], "Min");
				chart.addSerie(variables[i], "Max");
				chart.addSerie(variables[i], "Average");
			}
	
			allTurtles = new TurtleProbe(getSimulationGroup(),"turtle");
			addProbe(allTurtles);
		}
	}
	
	private void updateVariablesCharts(int step) {
		Turtle[] turtles = allTurtles.getTurtles();
		for (int i = 0; i < variables.length; i++) {
			String roleV = variables[i]; 
			String[] RV = roleV.split("-");
			variablesValues[0] = Double.POSITIVE_INFINITY;
			variablesValues[1] = 0;
			variablesValues[2] = 0;
			for (int j = 0; j < turtles.length; j++) {
				Turtle t = turtles[j];
				if(t.isPlayingRole("turtle")){
					double value;
					try {
						value = getValueOf(t, RV[1]);
						variablesValues[2] += value;
						if(value < variablesValues[0]) variablesValues[0] = value;
						if(value > variablesValues[1]) variablesValues[2] = value;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			variablesValues[2] /= turtles.length;
			chart.update(variables[i], step, variablesValues);
		}		
	}
	
	
	@SuppressWarnings("unchecked")
	public static double getValueOf(Object clazz, String lookingForValue)
	throws Exception {
		Field field = clazz.getClass().getField(lookingForValue);
		@SuppressWarnings("unused")
		Class clazzType = field.getType();
		return field.getDouble(clazz);
	}
	
	
	private void createRolesCharts(){
		String role = "";
		if(getAttrib().containsKey("role"))
				role = getAttrib().getString("role"); 
		if(role.length() > 0) {
			rolesOn = true;
			roles = role.split(",");
			rolesValues = new double[roles.length];
			chart.addChart("Population", "Step", "Population");
			for (int i = 0; i < roles.length; i++) {
				chart.addSerie("Population", roles[i]);
			}
		}
	}
	
	private void updateRolesCharts(int step2) {
		for (int i = 0; i < roles.length; i++) {
			rolesValues[i] = this.getAgentsWithRole(
					Tk2Launcher.COMMUNITY, getSimulationGroup(), roles[i]).length;
			chart.update("Population", step, rolesValues);
		}		
	}

	private void createPheroCharts(){
		String pheromones = "";
		if(getAttrib().containsKey("pheromone"))
			pheromones = getAttrib().getString("pheromone"); 
		if(pheromones.length() > 0) {
			pheroOn = true;
			phero = pheromones.split(",");
			for (int i = 0; i < phero.length; i++) {
				System.err.println(phero[i]);
				chart.addChart(phero[i], "Step", "Quantity");
				chart.addSerie(phero[i], "Min");
				chart.addSerie(phero[i], "Max");
				chart.addSerie(phero[i], "Average");
			}
		}
	}
	
	public void updatePheroCharts(int step){
		for (int p = 0; p < phero.length; p++) {
			pheroValues[0] = Double.POSITIVE_INFINITY;
			pheroValues[1] = 0;
			pheroValues[2] = 0; 
			for (int l = 0; l < envHeight; l++) {
				for (int j = 0; j < envHeight; j++) {
					double pheroQ = patchGrid[l][j].smell(phero[p]);
					pheroValues[1] += pheroQ;
					if(pheroQ < pheroValues[0]) pheroValues[0] = pheroQ;
					if(pheroQ > pheroValues[1]) pheroValues[2] = pheroQ;
				}
			}
			pheroValues[2] /= envHeight * envWidth;
			chart.update(phero[p], step, pheroValues);
		}
	}

}
