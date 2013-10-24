package civilisation.inspecteur;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import civilisation.Civilisation;
import civilisation.world.WorldCtrl;

import edu.turtlekit2.Tk2Launcher;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.tools.chart.ChartWindow;
import edu.turtlekit2.tools.chart.LineCharter;
import edu.turtlekit2.ui.SimulationBoard;
import edu.turtlekit2.ui.utils.GUIMessage;

public class CognitonsCharter extends Viewer{

	int step = 0;
	ChartWindowImproved chart;
	Box boite;
	JComboBox cognitonPlot;
	int index;
	
	public void setup()
	{
        boite = new Box(BoxLayout.PAGE_AXIS);
        cognitonPlot = new JComboBox();
        cognitonPlot.addActionListener(new ActionCognitonCharterListener(this,0));
		chart = new ChartWindowImproved();
		boite.add(cognitonPlot);
		boite.add(chart);
		
		this.sendMessage(Tk2Launcher.COMMUNITY, getSimulationGroup(), "UIManager",
				new GUIMessage<JComponent>(boite, SimulationBoard.VIEWER_ZONE, "CogniCharter"));
		

		chart.addChart("test", "Step", "Value");
		chart.addSerie("test", "cogni");

	}
	
	public void watch()
	{
		if (step == 0)
		{
			System.out.println("Nombre sondes : " +WorldCtrl.sondes.size());
			for(int i = 0; i < WorldCtrl.sondes.size(); i++)
			{
				String s[] = WorldCtrl.sondes.get(i).getCognitonEvalue().getName().split("_");
				cognitonPlot.addItem(s[s.length-1]);
			}
		}
		
		if (cognitonPlot.getSelectedItem() != null)
		{
			int nCiv = Civilisation.getNombreCiv();
			double[] val = new double[nCiv];
			for (int i = 0; i < nCiv; i++)
			{
				val[i] = (double) WorldCtrl.sondes.get(index).getN().get(step)[i];
			}
			chart.update("Nombre d'agents portant le cogniton "+(String) cognitonPlot.getSelectedItem(), step, val);

		}


		step++;

	}

	public void modifierGraphique() {
		
		if (cognitonPlot.getSelectedItem() != null)
		{
			System.out.println("Modification graphique");
			
			index = cognitonPlot.getSelectedIndex();
			
	        boite.removeAll();
			chart = new ChartWindowImproved();
			boite.add(cognitonPlot);
			boite.add(chart);
			
			chart.addChart("Nombre d'agents portant le cogniton "+(String) cognitonPlot.getSelectedItem(), "Step", "Value");
			int nCiv = Civilisation.getNombreCiv();
			for (int i = 0; i < nCiv; i++)
			{
				chart.addSerie("Nombre d'agents portant le cogniton "+(String) cognitonPlot.getSelectedItem(), "Civ "+i);
			}
			
			
			double[] val = new double[nCiv];
			for (int i = 0; i < step; i++)
			{
				for (int j = 0; j < nCiv; j++)
				{
					val[j] = (double) WorldCtrl.sondes.get(index).getN().get(i)[j];
				}
				chart.update("Nombre d'agents portant le cogniton "+(String) cognitonPlot.getSelectedItem(), i, val);			}
			}
		
	}
	
}
