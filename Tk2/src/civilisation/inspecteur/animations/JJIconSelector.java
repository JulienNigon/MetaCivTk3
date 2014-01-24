package civilisation.inspecteur.animations;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import civilisation.Configuration;
import civilisation.ItemPheromone;

public class JJIconSelector extends JComboBox{

	ArrayList pathTab;
	
	public JJIconSelector (String path) {
		//for (int i = 0 ; i <)
		//ImageIcon ico;
		super();
		
		File[] fichiersIcones = new File(System.getProperty("user.dir")+path).listFiles();
		ArrayList<ItemPheromone> phero = new ArrayList<ItemPheromone>();
		ArrayList<String> pathTab = new ArrayList<String>();
		for (File file : fichiersIcones) {
			if (!file.isHidden() && file.getName().endsWith(".png")){
		    	ImageIcon ico = new ImageIcon(file.getPath());
		    	this.addItem(ico);
			}
		}	
		
	}
	
	
}
