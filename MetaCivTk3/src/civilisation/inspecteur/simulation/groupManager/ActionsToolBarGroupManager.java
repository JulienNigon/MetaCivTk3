package civilisation.inspecteur.simulation.groupManager;

import java.awt.Event;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import civilisation.inspecteur.simulation.dialogues.DialogEditPheromon;
import civilisation.inspecteur.simulation.dialogues.DialogueEnregistrerEnvironnement;
import civilisation.inspecteur.simulation.dialogues.DialogueSelectionnerEnvironnementActif;

public class ActionsToolBarGroupManager implements ActionListener{

	GroupToolBar gt;
	int index;
	
	public ActionsToolBarGroupManager(GroupToolBar gt, int i)
	{
		this.gt = gt;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			gt.groupSelectionChanged();	
		}
		if (index == 2) 
		{
			//Add a new Culturon to the group and role
			gt.panelGroupManager.creerCogniton();
		}
	}
	
}