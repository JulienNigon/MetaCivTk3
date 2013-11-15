package civilisation.inspecteur.simulation.environnement;

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

public class ActionsToolBarEnvironnement implements ActionListener{

	PanelEnvironnement p;
	int index;
	
	public ActionsToolBarEnvironnement(PanelEnvironnement p, int i)
	{
		this.p = p;
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (index == 0){
			System.out.println("--Enregistrement de l'environnement--");
			DialogueEnregistrerEnvironnement d = new DialogueEnregistrerEnvironnement((Frame) p.getTopLevelAncestor() , true , p);
			d.setVisible(true);

		
		}
		else if (index == 1){
			System.out.println("--Chargement d'un environnement (TODO)--");
		}
		else if (index == 2){
			System.out.println("--Reglage des dimensions (TODO)--");
		}
		else if (index == 3){
			System.out.println("--Creation d'un environnement à partir d'une image--");
		    JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(".png","png");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(p);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
				File image = chooser.getSelectedFile();
				try {
					System.out.println("Chargement de l'image");
					BufferedImage buffer = ImageIO.read(image);
					p.generationEnvironnementViaImage(buffer);
					
				} catch (IOException e1) {
					System.out.println("Nécessite une image suivant le lien : /bin/civilisation/ressources/image.png");
					e1.printStackTrace();
				}
		    }
			

		}
		else if (index == 4){
			System.out.println("--Crayon--");
			p.setTypeDessin(0);
		}
		else if (index == 5){
			System.out.println("--Pot de peinture--");
			p.setTypeDessin(1);
		}
		else if (index == 6){
			System.out.println("--Zoom--");
			p.zoom(1);
		}
		else if (index == 7){
			System.out.println("--Dezoom--");
			if (p.getTaillePseudoPatch() > 1){	
				p.zoom(-1);
			}
			else{
				System.out.println("Vous ne pouvez pas dézoomer plus.");
			}
		}
		else if (index == 8){
			System.out.println("--Définir l'environnement de la simulation--");
			DialogueSelectionnerEnvironnementActif d = new DialogueSelectionnerEnvironnementActif((Frame) p.getTopLevelAncestor() , true , p);
			d.setVisible(true);		
		}
		else if (index == 9){
			System.out.println("--Manage pheromons--");
			DialogEditPheromon d = new DialogEditPheromon((Frame) p.getTopLevelAncestor() , true);
			d.setVisible(true);		
		}
	}

	
	
	
}
