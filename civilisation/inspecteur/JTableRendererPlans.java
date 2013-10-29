package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import civilisation.individu.plan.NPlanPondere;

import civilisation.inventaire.Objet;

public class JTableRendererPlans extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		NPlanPondere p = (NPlanPondere) value;
		Color fond;
		
		if (p != null)
		{
		    //setToolTipText("Poids:" + p.getPoids());
			if (column == 0)
			{
			    this.setValue((p.getPlan().getNom()));
				fond = new Color(240,255,255);


			    this.setBackground(fond);
			}
			else
			{

				    this.setValue(p.getPoids());

			}

		}
		else
		{
		    this.setBackground(Color.WHITE);
		    this.setValue(null);
		}

		    	
         return this;
	}
}