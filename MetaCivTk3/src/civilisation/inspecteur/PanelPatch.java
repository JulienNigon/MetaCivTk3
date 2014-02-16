package civilisation.inspecteur;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import turtlekit.kernel.Turtle;
import civilisation.Civilisation;


/** 
 * Un sous panel pour representer les informations sur le patch selectionne
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class PanelPatch extends JPanel{

	JLabel nomTerrain = new JLabel("Aucun patch selectionne");
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
		
		donnees[0][0] = "Patch X";
		donnees[0][1] = t.getPatch().x;
		donnees[1][0] = "Patch Y";
		donnees[1][1] = t.getPatch().y;
		
		/*for (int i = 2; i < Configuration.get + 2;i++)
		{
			donnees[i][0] = "civ"+i;
			donnees[i][1] = t.smell("civ"+i);
		}*/
		
		
		this.updateUI();
		
	}



	
}
