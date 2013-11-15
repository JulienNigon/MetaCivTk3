package civilisation.inspecteur.tableauDeBord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

/** 
 * Gère l'interaction avec le panel d'informations
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionPanelInfos implements ActionListener{

	PanelInfos p;
	int index;
	
	public ActionPanelInfos(PanelInfos p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) 
		{
			p.actualiserRatios();
		}
		else if (index == 1) 
		{
		}
		
	}




}
