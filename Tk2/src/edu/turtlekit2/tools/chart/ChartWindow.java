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

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import edu.turtlekit2.demos.gas.GasObserver;
/**
 * The turtleKit charter panel. Provides a convenient way to create quickly JFreeChart charts.
 * Create a TkCharter in an Observer to display data. {@link ChartWindow#addChart(String, String, String)} to 
 * add a new chart in the window. Then {@link #addSerie(String, String)} to the chart to display data. 
 * Update series by calling {@link #update(String, double, double...)}.
 * @author G.Beurier
 * @version 0.1 - 4/2010
 * @see LineCharter
 * @see GasObserver
 */
public class ChartWindow extends JPanel {
	final static long serialVersionUID = 1l;

	HashMap<String, XYSeriesCollection> sets = new HashMap<String, XYSeriesCollection>();
	
	/**
	 * Constructor. Creates a new window where charts can be added.
	 */
	public ChartWindow() {
		setLayout(new FlowLayout());
	}

	/**
	 * Add a new chart to the chart window. 
	 * @param chartName - the name of the chart.
	 * @param xName - the name of the x-axis.
	 * @param yName - the name of the y-axis.
	 */
	public void addChart(String chartName, String xName, String yName) {
		final XYSeriesCollection dataset = new XYSeriesCollection();
		sets.put(chartName, dataset);
		final JFreeChart chart = createChart(dataset, chartName, xName, yName);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(550, 250));
		add(chartPanel);
	}


	/**
	 * Add a new Serie to a given chart.
	 * @param chartName - the name of the chart.
	 * @param serieName - the name of the new serie.
	 */
	public void addSerie(String chartName, String serieName) {
		final XYSeries series = new XYSeries(serieName);
		sets.get(chartName).addSeries(series);
	}

	/**
	 * Creates a chart. Local use.
	 * @param dataset - the data for the chart.
	 * @param title - the name of the chart
	 * @param xName - the name of the x-axis.
	 * @param yName - the name of the y-axis.
	 * @return a chart.
	 */
	private JFreeChart createChart(final XYDataset dataset, String title, String xName, String yName) {
		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(
				title,      // chart title
				xName,                      // x axis label
				yName,                      // y axis label
				dataset,                  // data
				PlotOrientation.VERTICAL,
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
		);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//		final XYAreaRenderer2 renderer = new XYAreaRenderer2();
		renderer.setSeriesShapesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		renderer.setSeriesShapesVisible(2, false);
		plot.setRenderer(renderer);

//		renderer.setSeriesStroke(
//	            0, new BasicStroke(
//	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//	                1.0f, new float[] {1.0f, 6.0f}, 0.0f
//	            )
//	        );
//	        renderer.setSeriesStroke(
//	            1, new BasicStroke(
//	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//	                1.0f, new float[] {1.0f, 6.0f}, 0.0f
//	            )
//	        );
//	        renderer.setSeriesStroke(
//	            2, new BasicStroke(
//	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//	                1.0f, new float[] {1.0f, 6.0f}, 0.0f
//	            )
//	        );
//		
		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return chart;
	}

	
	/**
	 * Update data of the charts by adding values in the series of the chart.
	 * yValues are given in the creation order for every series.
	 * @param chartName - the name of the chart.
	 * @param xValue - the x-value for the added data (usually the step of simulation).
	 * @param yValues - an array of value for every data of the series. (i.e. {num_of_red; num_of_blue; average_age}).
	 * @see GasObserver
	 */
	public void update(String chartName, double xValue, double ... yValues) {
		XYSeriesCollection set = sets.get(chartName);
		for (int i = 0; i < yValues.length; i++) {
			set.getSeries(i).add(xValue, yValues[i]);
		}
	}
}
