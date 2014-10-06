package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.util.HashMap;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import turtlekit.kernel.Turtle;
import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.NPlanPondere;
import civilisation.world.World;

public class PanelCharts extends JPanel{

	ChartPanel chartPanel;
	JFreeChart chart;
	DefaultCategoryDataset dataset;
	
	ChartPanel chartPanelAttributes;
	JFreeChart chartAttributes;
	XYSeriesCollection datasetAttributes;
	
	public PanelCharts () {
        dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(590, 350));
        this.add(chartPanel);
        
        datasetAttributes = this.createDatasetAttributes();
        chartAttributes = createChartAttributes();
        chartPanelAttributes = new ChartPanel(chartAttributes);
        chartPanelAttributes.setPreferredSize(new java.awt.Dimension(500, 270));
        this.add(chartPanelAttributes);
	}
	
	private JFreeChart createChartAttributes() {

        // create subplot 1...
        final XYDataset data1 = datasetAttributes;
        final XYItemRenderer renderer1 = new StandardXYItemRenderer();
        final NumberAxis rangeAxis1 = new NumberAxis("Range 1");
        final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);


        // parent plot...
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
        plot.setGap(10.0);
        
        // add the subplots...
        plot.add(subplot1, 1);
        plot.setOrientation(PlotOrientation.VERTICAL);

        // return a new chart containing the overlaid plot...
        return new JFreeChart("Attributes plot",
                              JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}
	

    private XYSeriesCollection createDatasetAttributes() {

        XYSeriesCollection collection = new XYSeriesCollection();
        
    	for (String attr : Configuration.attributesNames) {
    		collection.addSeries(new XYSeries(attr));
    	}

  
        return collection;

    }


	private JFreeChart createChart(CategoryDataset dataset) {
		 final JFreeChart chart = ChartFactory.createStackedBarChart(
		            "Plan weight",  // chart title
		            "Category",                  // domain axis label
		            "Value",                     // range axis label
		            dataset,                     // data
		            PlotOrientation.VERTICAL,    // the plot orientation
		            true,                        // legend
		            true,                        // tooltips
		            false                        // urls
		        );
		        
		        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
		        KeyToGroupMap map = new KeyToGroupMap("G");
		        for(NPlan plan : Configuration.plans) {
		        	map.mapKeyToGroup(plan.getNom(), "G");
		        }

		        renderer.setSeriesToGroupMap(map); 
		        renderer.setBarPainter(new StandardBarPainter());
		        renderer.setItemMargin(0.0f);
		        
		        SubCategoryAxis domainAxis = new SubCategoryAxis("Tick");
		        
		        CategoryPlot plot = (CategoryPlot) chart.getPlot();
		        plot.setDomainAxis(domainAxis);
		        plot.setRenderer(renderer);
		        return chart;
	}


	private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        return result;
	}

	public void updateData() {
		if (World.getInstance().getTick()%20 == 0) {
			
			HashMap<NPlan,Float> planWeight = new HashMap<NPlan,Float>();
	        for(NPlan plan : Configuration.plans) {
	        	planWeight.put(plan, 0.0f);
	        }
			
	        for(Turtle turtle : World.getInstance().getTurtlesWithRoles("Humain")) {
				Humain h = (Humain) turtle ;
				for (NPlanPondere p : h.getEsprit().getPlans()) {
					planWeight.put(p.getPlan(), planWeight.get(p.getPlan()) + Math.max(p.getPoids(),0.0f));
				}
	        }
	        
	        float totalWeight = 0;
	        for(NPlan plan : Configuration.plans) {
	        	totalWeight += Math.max(planWeight.get(plan),0.0f);
	        }
	        totalWeight /= 100.0f;
	        
	        for(NPlan plan : Configuration.plans) {
	        	dataset.addValue(Math.max(planWeight.get(plan) / totalWeight,0.0f), plan.getNom(), String.valueOf(World.getInstance().getTick()));
	        }
		}
		if (World.getInstance().getTick() % 15 == 0) {

		
		HashMap<String,Float> attributeValues = new HashMap<String,Float>();
        for(String attr : Configuration.attributesNames) {
        	attributeValues.put(attr, 0.0f);
        }
		
        for(Turtle turtle : World.getInstance().getTurtlesWithRoles("Humain")) {
			Humain h = (Humain) turtle ;
			for(String attr : Configuration.attributesNames) {
				attributeValues.put(attr, (float) (attributeValues.get(attr) + (h.getAttr().get(attr)/World.getInstance().getTurtlesWithRoles("Humain").size())));
			}
        }
        
			for (String attr : Configuration.attributesNames) {
				datasetAttributes.getSeries(attr).add(World.getInstance().getTick(),attributeValues.get(attr));
			}
		}
	}
	
	

	
}
