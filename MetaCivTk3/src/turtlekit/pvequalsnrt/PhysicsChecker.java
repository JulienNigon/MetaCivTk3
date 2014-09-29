package turtlekit.pvequalsnrt;


import javax.swing.JFrame;

import madkit.kernel.Probe;

import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import turtlekit.agr.TKOrganization;
import turtlekit.kernel.Turtle;
import turtlekit.viewer.AbstractChartViewer;

public class PhysicsChecker extends AbstractChartViewer {
	
	private int wallX;
	private Probe<Turtle> p;
	private XYSeries rightSide;
	private int index = 0;
	private XYSeries total;

	/**
	 * Just to do some initialization work
	 */
	@Override
	protected void activate() {
		super.activate();
		wallX = Integer.parseInt(getMadkitProperty("wallX"));
		p = new Probe<>(getCommunity(), TKOrganization.TURTLES_GROUP, TKOrganization.TURTLE_ROLE);
		addProbe(p);
	}

	@Override
	public void setupFrame(JFrame frame) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		final ChartPanel chartPanel = createChartPanel(dataset, "PV = nRT", null, null);
		chartPanel.setPreferredSize(new java.awt.Dimension(550, 250));
		rightSide = new XYSeries("Gas on the right side");
		dataset.addSeries(rightSide);
		total = new XYSeries("Total");
		dataset.addSeries(total);
		frame.setContentPane(chartPanel);
		frame.setLocation(50, 0);
		XYSeries s = dataset.getSeries("Total");
	}
	
	@Override
	protected void observe() {
		if(index % 10000 == 0){
			total.clear();
			rightSide.clear();
		}
		int nb = 0;
		for (Turtle t : p.getCurrentAgentsList()) {
			if(t.getX() > wallX){
				nb++;
			}
		}
		total.add(index, p.size());
		rightSide.add(index, nb);
		index++;
	}
	

}
