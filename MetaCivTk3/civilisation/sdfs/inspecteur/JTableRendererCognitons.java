package civilisation.inspecteur;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import civilisation.individu.cognitons.TypeCogniton;
import civilisation.individu.cognitons.Cogniton;

public class JTableRendererCognitons extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

			Cogniton cogni = (Cogniton) value;
			
			if (cogni != null)
			{
			    setToolTipText((cogni.getCogniton().getDescription()));
			    this.setValue((cogni.getCogniton().getNom()));
			    Color fond = cogni.getCogniton().getType().getCouleur();
			    this.setBackground(fond);
			}
			else
			{
			    this.setBackground(Color.WHITE);
			}

		    	
         return this;
	}
}