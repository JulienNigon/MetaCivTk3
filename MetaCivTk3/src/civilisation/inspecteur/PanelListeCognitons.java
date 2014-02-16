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

public class PanelListeCognitons extends JPanel{

	JLabel titre = new JLabel("Cognitons");
	JTable tableau;
	Object[][] donnees = new Object[1000][1];
	JTableRendererCognitons renderer;

	JTable weights;
	Object[][] dataWeight = new Object[1000][1];

	public PanelListeCognitons()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Cognitons"};
        tableau = new JTable(donnees, entetes);
        renderer = new JTableRendererCognitons();
        tableau.setDefaultRenderer(Object.class, renderer);

        weights = new JTable(dataWeight, entetes);
        
		this.add(tableau, BorderLayout.CENTER);
		this.add(weights, BorderLayout.EAST);

		
		this.setVisible(true);
	}


	
	public void actualiser(Turtle t)
	{
		for (int i=0; i < donnees.length;i++)
		{
			donnees[i][0] = null;
			donnees[i][0] = null;
		}
		for (int i = 0; i < ((Humain) t).getEsprit().getCognitons().size();i++)
		{
			donnees[i][0] = ((Humain) t).getEsprit().getCognitons().get(i);
		}
		
		for (int i=0; i < dataWeight.length;i++)
		{
			dataWeight[i][0] = null;
			dataWeight[i][0] = null;
		}
		for (int i = 0; i < ((Humain) t).getEsprit().getCognitons().size();i++)
		{
			dataWeight[i][0] = ((Humain) t).getEsprit().getCognitons().get(i).getWeigth();
		}

		this.updateUI();
		
	}
}
	


