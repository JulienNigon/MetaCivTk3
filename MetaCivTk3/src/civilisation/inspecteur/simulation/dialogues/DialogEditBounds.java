package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import civilisation.Configuration;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;
import civilisation.world.Terrain;

public class DialogEditBounds extends JDialog implements ActionListener, PropertyChangeListener{
	
	PanelEnvironnement p;
    JOptionPane optionPane;


    JSpinner x = new JSpinner();
    JSpinner y = new JSpinner();

	public DialogEditBounds(Frame f , boolean modal, PanelEnvironnement p){
		super(f,modal);
		this.p = p;
		this.setTitle("Edit environment bounds");

		SpinnerNumberModel spinModel;

		spinModel = new SpinnerNumberModel(p.getLargeur(), 0, 1000, 1);
		x = new JSpinner(spinModel);
		x.setToolTipText("X");

			
		spinModel = new SpinnerNumberModel(p.getHauteur(), 0, 1000, 1);
		y = new JSpinner(spinModel);
		y.setToolTipText("Y");


	    Object[] array = new Object[2];
	    array[0] = x;
	    array[1] = y;
	    
	    Object[] options = {"OK" , "Cancel"};
	 
	    optionPane = new JOptionPane(array,
	                                    JOptionPane.QUESTION_MESSAGE,
	                                    JOptionPane.YES_NO_OPTION,
	                                    null,
	                                    options,
	                                    options[0]); 
	    setContentPane(optionPane);
	        
	    optionPane.addPropertyChangeListener(this);
	        
		ImageIcon icone = new ImageIcon(System.getProperty("user.dir")+"/civilisation/graphismes/LogoMedium.png");
		optionPane.setIcon(icone);
		this.pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (isVisible() && (optionPane.getValue().equals("OK") || optionPane.getValue().equals("Cancel"))){
			if (optionPane.getValue().equals("OK")){
				p.setLargeur((Integer) x.getValue());
				p.setHauteur((Integer) y.getValue());
				p.redimensionner();
				p.getgCarte().actualiser();
				p.revalidate();
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
