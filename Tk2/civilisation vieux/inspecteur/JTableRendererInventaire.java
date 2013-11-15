package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.plan.Plan;
import civilisation.inventaire.ObjetInventaire;

public class JTableRendererInventaire extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

			ImageIcon icone;
			ObjetInventaire o = (ObjetInventaire) value;
			
			if (o != null)
			{
				setToolTipText((String) (o.toString()));
				this.setValue((String) (o.toString()));

				if (o.getPathToIcon() != null)
			    {
		    		icone = new ImageIcon(this.getClass().getResource(o.getPathToIcon()));
		    		setIcon(icone);
				}
				else
				{
					setIcon(null);
				}


			}
			else
			{
				setIcon(null);
				setValue(null);
				setToolTipText(null);
			}
		    	
         return this;
	}
}