package civilisation.inspecteur.simulation.dialogues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import civilisation.Configuration;
import civilisation.individu.cognitons.TypeDeCogniton;
import civilisation.individu.plan.action.Action;
import civilisation.inspecteur.simulation.GCogniton;
import civilisation.inspecteur.simulation.PanelArbreActions;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;

public class DialogueAjouterAction extends JDialog implements ActionListener, PropertyChangeListener{
	
	JComboBox listeActions;
    JOptionPane optionPane;
    PanelArbreActions p;
    Option_BeforeAfter option;
    JTextPane infos; /*Affiche les infos concernant l'action séléctionnée*/
    
	public DialogueAjouterAction(Frame f , boolean modal , PanelArbreActions p, Option_BeforeAfter option){
		super(f,modal);

		this.p = p;
		this.option = option;
		infos = new JTextPane();
		infos.setContentType("text/html");
		
		listeActions = new JComboBox();

		for (int i = 0; i < Configuration.actions.size(); i++){
			listeActions.addItem(Configuration.actions.get(i).getName().split("\\.")[Configuration.actions.get(i).getName().split("\\.").length -1]);
		}
		
		listeActions.addActionListener(this);
		changeInfos(); /*Mise à jour du texte informatif*/
		this.setTitle("Ajouter une action");
		
		
	    Object[] array = {listeActions , infos};
	       

	    Object[] options = {"Valider" , "Annuler"};
	 
	    optionPane = new JOptionPane(array,
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]); 

	    setContentPane(optionPane);
	        
	    optionPane.addPropertyChangeListener(this);
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/bin/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (isVisible()){
			if (optionPane.getValue().equals("Valider")){
				String[] s = new String[1];
				s[0] = (String) listeActions.getSelectedItem();
				Action nouvelleAction = Action.actionFactory(s);
				p.addNewAction(nouvelleAction , option);
				
		        setVisible(false);
 
				DialogueEditerAction d;
				try {
					d = new DialogueEditerAction((Frame) p.getTopLevelAncestor() , true , p, nouvelleAction);
					d.setVisible(true);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e);

		if (e.getSource().equals(listeActions)){
			System.out.println("changement");
			changeInfos();
			this.pack();
		}
	}
	
	private void changeInfos(){
		infos.setText((Configuration.getActionByName((String) listeActions.getSelectedItem())).getInfo());
	}
	
	public enum Option_BeforeAfter {
		BEFORE , AFTER, INTERNAL,  /*Pour les sous-actions*/
	}

	
}
