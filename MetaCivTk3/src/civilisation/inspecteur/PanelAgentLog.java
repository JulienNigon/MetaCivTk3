package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import civilisation.individu.Humain;
import civilisation.individu.plan.action.Action;

public class PanelAgentLog extends JPanel{

	final static int logSize = 100000;
	int index = 0;
	Humain h;
	Object[][] data = new Object[logSize][3];
	JTable jTable;
	
	public PanelAgentLog(Humain h) {
		this.h = h;
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
        String[] title = {"tick","Action","Plan"};
        jTable = new JTable(data, title);
        //renderer = new JTableRendererCognitons();
        //tableau.setDefaultRenderer(Object.class, renderer);

        //weights = new JTable(dataWeight, entetes);
        
		this.add(jTable, BorderLayout.CENTER);
	}
	
	/*
	 * 	Object[][] donnees = new Object[1000][1];
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

	 */
	
	public void updateData() {
		data[index][0] = index;
		data[index][1] = h.getEsprit().getActionEnCours();
		data[index][2] = h.getEsprit().getPlanEnCours().getPlan();
		index++;
		jTable.repaint();
	}
	
}


