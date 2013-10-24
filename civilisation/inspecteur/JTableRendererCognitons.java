package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.cognitons.NCogniton;

public class JTableRendererCognitons extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

			NCogniton cogni = (NCogniton) value;
			
			if (cogni != null)
			{
			    setToolTipText((String) (cogni.getDescription()));
			    this.setValue((String) (cogni.getNom()));
			   // Color fond = cogni.getColor();
			//    this.setBackground(fond);
			}
			else
			{
			    this.setBackground(Color.WHITE);
			}

		    	
         return this;
	}
}