package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.cognitons.CCogniton;

public class JTableRendererCognitons extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

			CCogniton cogni = (CCogniton) value;
			
			if (cogni != null)
			{
			    setToolTipText((cogni.getCogniton().getDescription()));
			    this.setValue((cogni.getCogniton().getNom()));
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