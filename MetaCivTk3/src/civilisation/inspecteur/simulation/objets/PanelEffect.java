package civilisation.inspecteur.simulation.objets;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import civilisation.Configuration;
import civilisation.effects.Effect;
import civilisation.inventaire.Objet;

public class PanelEffect extends JPanel implements ActionListener{
	
	JLabel nomEffet;
	JTextField nomEffetText;
	JLabel target;
	ButtonGroup choixTarget;
	JRadioButton choix1T;
	JRadioButton choix2T;
	JLabel nomCible;
	JComboBox listeCibles;
	JLabel modifValue;
	JTextField modifValueText;
	JLabel permanent;
	ButtonGroup choixPermanent;
	JRadioButton choix1P;
	JRadioButton choix2P;
	JLabel nomType;
	ButtonGroup choixType;
	JRadioButton choix1Ty;
	JRadioButton choix2Ty;
	JRadioButton choix3Ty;
	JLabel nomActive;
	ButtonGroup choixActive;
	JRadioButton choix1A;
	JRadioButton choix2A;
	JButton SaveEffect;
	Effect effet;
	public PanelEffect(PanelObjets panelParent)
	{
		super();
		nomEffet = new JLabel("Effect Name : ");
		nomEffetText = new JTextField(20);
		target = new JLabel("Target type : ");
		choixTarget = new ButtonGroup();
		choix1T = new JRadioButton("Attributes");
		choix2T = new JRadioButton("Cognitons");
		choix1T.addActionListener(this);
		choix1T.setSelected(true);
		choix2T.addActionListener(this);
		choixTarget.add(choix1T);
		choixTarget.add(choix2T);
		nomCible = new JLabel("Target Name : ");
		listeCibles = new JComboBox();
		if(choix1T.isSelected())
		{
			for(int i = 0; i < Configuration.attributesNames.size();++i)
			{
				listeCibles.addItem(Configuration.attributesNames.get(i));
			}
		}
		else
		{
			for(int i = 0; i < Configuration.cognitons.size();++i)
			{
				listeCibles.addItem(Configuration.cognitons.get(i).getNom());
			}
		}
		
		nomType = new JLabel("Effect Type : ");
		choixType = new ButtonGroup();
		choix1Ty = new JRadioButton("Add");
		choix2Ty = new JRadioButton("Modify");
		choix3Ty = new JRadioButton("Remove");
		choixType.add(choix1Ty);
		choixType.add(choix2Ty);
		choixType.add(choix3Ty);
		modifValue = new JLabel("Value : ");
		modifValueText = new JTextField(5);
		nomActive = new JLabel("Activation : ");
		choixActive = new ButtonGroup();
		choix1A = new JRadioButton("Possession");
		choix2A = new JRadioButton("Use");
		choixActive.add(choix1A);
		choixActive.add(choix2A);
		permanent = new JLabel("Permanent ? : ");
		choixPermanent = new ButtonGroup();
		choix1P = new JRadioButton("Yes");
		choix2P = new JRadioButton("No");
		choixPermanent.add(choix1P);
		choixPermanent.add(choix2P);
		SaveEffect = new JButton("Save Effect");
		SaveEffect.addActionListener(this);
		SaveEffect.setActionCommand("SaveEffect");
		Box b1 =  Box.createHorizontalBox();
		b1.setAlignmentX(LEFT_ALIGNMENT);
		b1.setAlignmentY(LEFT_ALIGNMENT);
		
		b1.add(nomEffet);
		b1.add(nomEffetText);
		
		Box b2 = Box.createHorizontalBox();
		b2.setAlignmentX(LEFT_ALIGNMENT);
		b2.setAlignmentY(LEFT_ALIGNMENT);
		
		b2.add(target);
		b2.add(choix1T);
		b2.add(choix2T);
		
		Box b3 = Box.createHorizontalBox();
		b3.setAlignmentX(LEFT_ALIGNMENT);
		b3.setAlignmentY(LEFT_ALIGNMENT);
		
		b3.add(nomCible);
		b3.add(listeCibles);
		
		Box b4 = Box.createHorizontalBox();
		b4.setAlignmentX(LEFT_ALIGNMENT);
		b4.setAlignmentY(LEFT_ALIGNMENT);
		
		b4.add(modifValue);
		b4.add(modifValueText);
		
		Box b5 = Box.createHorizontalBox();
		b5.setAlignmentX(LEFT_ALIGNMENT);
		b5.setAlignmentY(LEFT_ALIGNMENT);
		
		b5.add(permanent);
		b5.add(choix1P);
		b5.add(choix2P);
		
		Box b6 = Box.createHorizontalBox();
		b6.setAlignmentX(LEFT_ALIGNMENT);
		b6.setAlignmentY(LEFT_ALIGNMENT);
		
		b6.add(nomType);
		b6.add(choix1Ty);
		b6.add(choix2Ty);
		b6.add(choix3Ty);
		
		Box b7 = Box.createHorizontalBox();
		b7.setAlignmentX(LEFT_ALIGNMENT);
		b7.setAlignmentY(LEFT_ALIGNMENT);
		
		b7.add(nomActive);
		b7.add(choix1A);
		b7.add(choix2A);
		
		Box b8 = Box.createVerticalBox();
		b8.add(b1);
		b8.add(b2);
		b8.add(b3);
		b8.add(b4);
		b8.add(b5);
		b8.add(b6);
		b8.add(b7);
		b8.add(SaveEffect);
		this.add(b8);
	}
	
	public PanelEffect(PanelObjets p,Effect e)
	{
		super();
		nomEffet = new JLabel("Effect Name : ");
		nomEffetText = new JTextField(20);
		nomEffetText.setText(e.getName());
		target = new JLabel("Target type : ");
		choixTarget = new ButtonGroup();
		choix1T = new JRadioButton("Attributes");
		choix2T = new JRadioButton("Cognitons");
		choix1T.addActionListener(this);
		choix2T.addActionListener(this);
		choixTarget.add(choix1T);
		choixTarget.add(choix2T);
		if(e.getTarget().equals("attribut"))
		{
			choix1T.setSelected(true);
		}
		else
		{
			choix2T.setSelected(true);
		}
		nomCible = new JLabel("Target Name : ");
		listeCibles = new JComboBox();
		if(choix1T.isSelected())
		{
			for(int i = 0; i < Configuration.attributesNames.size();++i)
			{
				listeCibles.addItem(Configuration.attributesNames.get(i));
			}
		}
		else
		{
			for(int i = 0; i < Configuration.cognitons.size();++i)
			{
				listeCibles.addItem(Configuration.cognitons.get(i).getNom());
			}
		}
		int index = 0;
		for(int j = 0; j < listeCibles.getItemCount();++j)
		{
			if(listeCibles.getItemAt(j).equals(e.getVarget()))
			{
				index = j;
			}
		}
		listeCibles.setSelectedIndex(index);
		nomType = new JLabel("Effect Type : ");
		choixType = new ButtonGroup();
		choix1Ty = new JRadioButton("Set");
		choix2Ty = new JRadioButton("Add");
		choix3Ty = new JRadioButton("Remove");
		choixType.add(choix1Ty);
		choixType.add(choix2Ty);
		choixType.add(choix3Ty);
		if(e.getType() == 0)
		{
			choix1Ty.setSelected(true);
		}
		else
		{
			if(e.getType() == 1)
			{
				choix2Ty.setSelected(true);
			}
			else
			{
				choix3Ty.setSelected(true);
			}
		}
		modifValue = new JLabel("Value : ");
		modifValueText = new JTextField(5);
		Double val = e.getValue();
		modifValueText.setText(val.toString());
		nomActive = new JLabel("Activation : ");
		choixActive = new ButtonGroup();
		choix1A = new JRadioButton("Possession");
		choix2A = new JRadioButton("Use");
		choixActive.add(choix1A);
		choixActive.add(choix2A);
		if(e.getActivation() == 0)
		{
			choix1A.setSelected(true);
		}
		else
		{
			choix2A.setSelected(true);
		}
		permanent = new JLabel("Permanent ? : ");
		choixPermanent = new ButtonGroup();
		choix1P = new JRadioButton("Yes");
		choix2P = new JRadioButton("No");
		if(e.isPermanent())
		{
			choix1P.setSelected(true);
		}
		else
		{
			choix2P.setSelected(true);
		}
		choixPermanent.add(choix1P);
		choixPermanent.add(choix2P);
		SaveEffect = new JButton("Save Effect");
		SaveEffect.addActionListener(this);
		SaveEffect.setActionCommand("SaveEffect");
		Box b1 =  Box.createHorizontalBox();
		b1.setAlignmentX(LEFT_ALIGNMENT);
		b1.setAlignmentY(LEFT_ALIGNMENT);
		
		b1.add(nomEffet);
		b1.add(nomEffetText);
		
		Box b2 = Box.createHorizontalBox();
		b2.setAlignmentX(LEFT_ALIGNMENT);
		b2.setAlignmentY(LEFT_ALIGNMENT);
		
		b2.add(target);
		b2.add(choix1T);
		b2.add(choix2T);
		
		Box b3 = Box.createHorizontalBox();
		b3.setAlignmentX(LEFT_ALIGNMENT);
		b3.setAlignmentY(LEFT_ALIGNMENT);
		
		b3.add(nomCible);
		b3.add(listeCibles);
		
		Box b4 = Box.createHorizontalBox();
		b4.setAlignmentX(LEFT_ALIGNMENT);
		b4.setAlignmentY(LEFT_ALIGNMENT);
		
		b4.add(modifValue);
		b4.add(modifValueText);
		
		Box b5 = Box.createHorizontalBox();
		b5.setAlignmentX(LEFT_ALIGNMENT);
		b5.setAlignmentY(LEFT_ALIGNMENT);
		
		b5.add(permanent);
		b5.add(choix1P);
		b5.add(choix2P);
		
		Box b6 = Box.createHorizontalBox();
		b6.setAlignmentX(LEFT_ALIGNMENT);
		b6.setAlignmentY(LEFT_ALIGNMENT);
		
		b6.add(nomType);
		b6.add(choix1Ty);
		b6.add(choix2Ty);
		b6.add(choix3Ty);
		
		Box b7 = Box.createHorizontalBox();
		b7.setAlignmentX(LEFT_ALIGNMENT);
		b7.setAlignmentY(LEFT_ALIGNMENT);
		
		b7.add(nomActive);
		b7.add(choix1A);
		b7.add(choix2A);
		
		Box b8 = Box.createVerticalBox();
		b8.add(b1);
		b8.add(b2);
		b8.add(b3);
		b8.add(b4);
		b8.add(b5);
		b8.add(b6);
		b8.add(b7);
		b8.add(SaveEffect);
		this.add(b8);
		this.RendreVisible();
	}
	
	public void RendreVisible()
	{
		nomEffet.setVisible(true);
		nomEffetText.setVisible(true);
		target.setVisible(true);
		choix1T.setVisible(true);
		choix2T.setVisible(true);
		nomCible.setVisible(true);
		listeCibles.setVisible(true);
		modifValue.setVisible(true);
		modifValueText.setVisible(true);
		permanent.setVisible(true);
		choix1P.setVisible(true);
		choix2P.setVisible(true);
		nomType.setVisible(true);
		choix1Ty.setVisible(true);
		choix2Ty.setVisible(true);
		choix3Ty.setVisible(true);
		nomActive.setVisible(true);
		choix1A.setVisible(true);
		choix2A.setVisible(true);
	}
	
	public Effect performedChange(Effect e)
	{
		e.setName(nomEffetText.getText());
		
		return e;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == "SaveEffect")
		{
			Effect ef = new Effect();
			ef.setName(nomEffetText.getText());
			if(choix1T.isSelected())
			{
				ef.setTarget("attribut");
			}
			else
			{
				ef.setTarget("cogniton");
			}
			ef.setVarget(listeCibles.getSelectedItem().toString());
			ef.setValue(Double.parseDouble(modifValueText.getText()));
			if(choix1P.isSelected())
			{
				ef.setPermanent(true);
			}
			else
			{
				ef.setPermanent(false);
			}
			if(choix1Ty.isSelected())
			{
				ef.setType(0);
			}
			else
			{
				if(choix2Ty.isSelected())
				{
					ef.setType(1);
				}
				else
				{
					ef.setType(2);
				}
			}
			if(choix1A.isSelected())
			{
				ef.setActivation(0);
			}
			else
			{
				ef.setActivation(1);
			}
			
			int i = 0;
			while(i < Configuration.effets.size() && Configuration.effets.get(i).getName().equals(ef.getName()))
			{
				i++;
			}
			if(i < Configuration.effets.size())
			{
				Configuration.effets.remove(i);
			}
			this.effet = ef;
			Configuration.effets.add(ef);
		}
		else
		{
			if(choix1T.isSelected())
			{
				listeCibles.removeAllItems();
				for(int i = 0; i < Configuration.attributesNames.size();++i)
				{
					System.out.println("testing2");
					listeCibles.addItem(Configuration.attributesNames.get(i));
				}
			}
			else
			{
				listeCibles.removeAllItems();
				for(int i = 0; i < Configuration.cognitons.size();++i)
				{
					listeCibles.addItem(Configuration.cognitons.get(i).getNom());
				}
			}
		}
		
	}

	public Effect returnEffect()
	{
		return this.effet;
	}
}
