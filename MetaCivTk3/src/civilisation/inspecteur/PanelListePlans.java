package civilisation.inspecteur;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import turtlekit.kernel.Turtle;
import civilisation.individu.Humain;


/** 
 * Un sous panel pour repr�senter les croyances d'un agent
 * @author DTEAM
 * @version 1.0 - 3/2013
*/

public class PanelListePlans extends JPanel{

	JLabel titre = new JLabel("Plans");
	JTable tableau;
	Object[][] donnees = new Object[1000][2];
	JTableRendererPlans renderer;

	public PanelListePlans()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Plans" , "poids"};
        tableau = new JTable(donnees, entetes);
        renderer = new JTableRendererPlans();
        tableau.setDefaultRenderer(Object.class, renderer);

        
		this.add(tableau, BorderLayout.CENTER);

		
		this.setVisible(true);
	}


	
	public void actualiser(Turtle t)
	{
		for (int i=0; i < donnees.length;i++)
		{
			donnees[i][0] = null;
			donnees[i][1] = null;
		}
		for (int i = 0; i < ((Humain) t).getEsprit().getPlans().size();i++)
		{
			donnees[i][0] = ((Humain) t).getEsprit().getPlans().get(i);
			donnees[i][1] = ((Humain) t).getEsprit().getPlans().get(i);
		}
		this.updateUI();
		
	}
}
	


