package civilisation.inspecteur;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import civilisation.Civilisation;
import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Un sous panel pour représenter les informations sur le patch sélectionné
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class PanelPatch extends JPanel{

	JLabel nomTerrain = new JLabel("Aucun patch sélectionné");
	JTable tableau;
	Object[][] donnees = new Object[10][2];

	public PanelPatch()
	{
		this.setLayout(new BorderLayout());
		this.add(nomTerrain, BorderLayout.NORTH);
		
        String[] entetes = {"Attribut", "Valeur"};
        tableau = new JTable(donnees, entetes);
		this.add(tableau, BorderLayout.CENTER);

		
		this.setVisible(true);
	}

	public void setTerrain(String nouveauNomTerrain) {
		
		nomTerrain.setText(nouveauNomTerrain);
		
	}
	
	public void actualiser(Turtle t)
	{
		nomTerrain.setText(t.getPatchColor().toString());
		

		for (int i = 0; i < Civilisation.getNombreCiv();i++)
		{
			donnees[i][0] = "civ"+i;
			donnees[i][1] = t.smell("civ"+i);
		}
		
		
		this.updateUI();
		
	}



	
}
