package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import civilisation.Configuration;
import civilisation.individu.plan.action.Action;
import civilisation.inspecteur.simulation.PanelArbreActions;

public class DialogueAjouterAction extends JDialog implements ActionListener, PropertyChangeListener{
	
	JComboBox listeActions;
    JOptionPane optionPane;
    PanelArbreActions p;
    Option_BeforeAfter option;
    JTextPane infos; /*Affiche les infos concernant l'action s�l�ctionn�e*/
    
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
		changeInfos(); /*Mise � jour du texte informatif*/
		this.setTitle("Add new action");
		
		
	    Object[] array = {listeActions , infos};
	       

	    Object[] options = {"OK" , "Cancel"};
	 
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
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
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
		BEFORE , AFTER, INTERNAL  /*For sub-actions*/, FIRST 
	}

	
}
