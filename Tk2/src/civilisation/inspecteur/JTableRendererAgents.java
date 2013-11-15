package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class JTableRendererAgents extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		 if (row == 5 && column == 1)
		 {
			 cell.setBackground(new Color(4,5,25));
		 }
         
         return cell;
	}
}