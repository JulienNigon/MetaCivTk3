package civilisation.inspecteur;

import java.awt.Color;
import java.awt.GradientPaint;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.Rotation;

import civilisation.individu.Humain;

/**
 * Show advanced informations about the agent using jFreeChart
 * @author Julien Nigon
 *
 */


public class PanelAgentData extends JPanel{
	
	Humain h;
	
	DefaultPieDataset dataPlanWeight;
	JFreeChart chartPlanWeight;
	ChartPanel chartPanelPlanWeight;
	
	DefaultCategoryDataset dataPlanUse;
	JFreeChart chartPlanWeightUse;
	ChartPanel chartPanelPlanUse;
	
	public PanelAgentData(Humain h) {
		this.h = h;
		
		createDataset();
		chartPlanWeight = createChart(dataPlanWeight, "Attributes");
        chartPanelPlanWeight = new ChartPanel(chartPlanWeight);
        chartPanelPlanWeight.setPreferredSize(new java.awt.Dimension(300, 150));
        chartPanelPlanWeight.setBackground(this.getBackground());
        this.add(chartPanelPlanWeight);
        
        chartPlanWeightUse = createChartPlanUse(dataPlanUse);
        chartPanelPlanUse = new ChartPanel(chartPlanWeightUse);
        chartPanelPlanUse.setPreferredSize(new java.awt.Dimension(300, 150));
        this.add(chartPanelPlanUse);
	}
	


    private  void createDataset() {
    	dataPlanWeight = new DefaultPieDataset();
    	for (String attr : h.getAttr().keySet()) {
        	dataPlanWeight.setValue(attr,  h.getAttr().get(attr));
    	} 
    	dataPlanUse = new DefaultCategoryDataset();
    	for (int i = 0 ; i < h.getEsprit().getPlans().size() ; i++) {
    		dataPlanUse.setValue(h.getEsprit().getPlans().get(i).getPoids(), h.getEsprit().getPlans().get(i).getPlan().getNom(), "val" );
    	} 
    }
    
    
    public void updateData() {
    	dataPlanWeight.clear();
    	for (String attr : h.getAttr().keySet()) {
        	dataPlanWeight.setValue(attr,  h.getAttr().get(attr));
    	} 
    	chartPanelPlanWeight.repaint();
    	dataPlanUse.clear();

    	for (int i = 0 ; i < h.getEsprit().getPlans().size() ; i++) {
    		dataPlanUse.setValue(h.getEsprit().getPlans().get(i).getPoids(), h.getEsprit().getPlans().get(i).getPlan().getNom(), "val" );
    	} 
    	chartPanelPlanUse.repaint();
    	
    	}
    
/**
     * Creates a chart
     */

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
            dataset,                // data
            false,                   // include legend
            true,
            false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.75f);
        plot.setBackgroundPaint(this.getBackground());
        return chart;
        
    }
    
    private JFreeChart createChartPlanUse(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart Demo",         // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            false,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );


        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        
        return chart;
        
    }
}
