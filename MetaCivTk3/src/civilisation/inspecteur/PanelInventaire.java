package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import turtlekit.kernel.Turtle;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;


/** 
 * Un sous panel pour repr___senter les informations sur l'inventaire de l'agent selectionne
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class PanelInventaire extends JPanel{

	JLabel titre = new JLabel("Inventaire");
	JTable tableau;
	Object[][] donnees = new Object[100][2];
	HashMap<Objet,Integer> donnee = new HashMap<Objet,Integer>();
	JTableRendererInventaire renderer;

	public PanelInventaire()
	{
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		
        String[] entetes = {"Objet", "Nombre"};
        tableau = new JTable(donnees, entetes);
		this.add(tableau, BorderLayout.CENTER);
        renderer = new JTableRendererInventaire();
        tableau.setDefaultRenderer(Object.class, renderer);
		
		this.setVisible(true);
	}


	
	public void actualiser(Turtle t)
	{
		for (int i=0; i < donnees.length;i++)
		{
			donnees[i][0] = null;
			donnees[i][1] = null;
		}
		
		//TODO
		
		ArrayList<Objet> allObjets = ((Humain) t).getInventaire().getAll();
		for (int i=0; i < allObjets.size();i++)
		{
			int j = 0;
			while(donnees[j][0] != null && donnees[j][0] != allObjets.get(i))
			{
				++j;
			}
			if(donnees[j][0] != null)
			{
				if(donnees[j][1].getClass().equals(Integer.class))
				{
					Integer temp = (Integer) donnees[j][1];
					donnees[j][1] = temp + 1;
				}
			}
			else
			{
				donnees[j][0] = allObjets.get(i);
				donnees[j][1] = 1;
			}

		}
	

		this.updateUI();
		
	}



	
}
