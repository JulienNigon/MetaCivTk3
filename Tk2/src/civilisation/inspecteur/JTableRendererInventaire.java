package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import civilisation.inventaire.Objet;

public class JTableRendererInventaire extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

			ImageIcon icone;
			Objet o = (Objet) value;
			
			if (o != null)
			{
				setToolTipText((o.toString()));
				this.setValue((o.toString()));

				if (o.getIcone() != null)
			    {
		    		setIcon(o.getIcone());
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