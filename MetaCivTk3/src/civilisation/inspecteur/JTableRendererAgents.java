package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import civilisation.individu.cognitons.CCogniton;

public class JTableRendererAgents extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
	
	if (value != null) {
		if (column == 0) {
			String string = (String) value;
			

			if (string.startsWith("(Attribute)")) {
			   	this.setBackground(new Color(149, 136, 181));
		    }
			else {
				this.setBackground(Color.WHITE);
			}

		}
		else {
			
			
			
			if (column == 1 && ((String)table.getValueAt(row, column-1)).startsWith("(Attribute)")) {
				this.setToolTipText(row + " " + column + " " + table.getValueAt(row, column-1));
				this.setBackground(new Color(211, 192, 255));
			}
			else {
				this.setBackground(Color.WHITE);
			}
			
		}

	} else {
		this.setBackground(Color.WHITE);
	}
		return this;
		
	}
}