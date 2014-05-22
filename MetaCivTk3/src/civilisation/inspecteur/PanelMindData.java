package civilisation.inspecteur;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import civilisation.individu.Humain;

/**
 * Show advanced informations about the mind of an agent
 * @author Julien Nigon
 *
 */


public class PanelMindData extends JPanel{
	
	Humain h;
	DefaultPieDataset dataPlanWeight;
	JFreeChart chartPlanWeight;
	ChartPanel chartPanelPlanWeight;
	
	public PanelMindData(Humain h) {
		this.h = h;
		
		createDataset();
		chartPlanWeight = createChart(dataPlanWeight, "Plans weight");
        chartPanelPlanWeight = new ChartPanel(chartPlanWeight);
        chartPanelPlanWeight.setPreferredSize(new java.awt.Dimension(400, 200));
        chartPanelPlanWeight.setBackground(this.getBackground());
        this.add(chartPanelPlanWeight);
	}
	


    private  void createDataset() {
    	dataPlanWeight = new DefaultPieDataset();
    	for (int i = 0 ; i < h.getEsprit().getPlans().size() ; i++) {
        	dataPlanWeight.setValue(h.getEsprit().getPlans().get(i).getPlan().getNom(),  h.getEsprit().getPlans().get(i).getPoids());
    	}        
    }
    
    
    public void updateData() {
    	dataPlanWeight.clear();
    	for (int i = 0 ; i < h.getEsprit().getPlans().size() ; i++) {
        	dataPlanWeight.setValue(h.getEsprit().getPlans().get(i).getPlan().getNom(),  h.getEsprit().getPlans().get(i).getPoids());
    	}
    	chartPanelPlanWeight.repaint();
    	}
    
/**
     * Creates a chart
     */

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.75f);
        plot.setBackgroundPaint(this.getBackground());
        return chart;
        
    }
}
