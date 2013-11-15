package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import civilisation.annotations.*;



import civilisation.Configuration;

/** 
 * Le JPanel contenant les options pour l'affichage
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public class PanelParametres extends JPanel{

	JToolBar toolBar = new JToolBar();
	JButton bParamConf;
	JButton bParamCogni;

	Box boiteConf;
	Box boiteCogni;


	ArrayList<Class> listeCognitons;
	
	Field[] fields;
	ArrayList<JComboBox> comboBox;
	ArrayList<JLabel> label;
	ArrayList<JComboBox> comboBox2;
	ArrayList<JLabel> label2;

	
	public PanelParametres() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchFieldException
	{
		this.setLayout(new BorderLayout());
        boiteConf = new Box(BoxLayout.PAGE_AXIS);
        boiteCogni = new Box(BoxLayout.PAGE_AXIS);

        
		ImageIcon imageParamConfig = new ImageIcon(this.getClass().getResource("icones/globe.png"));
		bParamConf = new JButton(imageParamConfig);
		bParamConf.setToolTipText("Modifier les parametres principaux");
		bParamConf.addActionListener(new ActionParametreToolBarListener(this, 0));
		toolBar.add(bParamConf);
		
		ImageIcon imageCognitonConfig = new ImageIcon(this.getClass().getResource("icones/brain.png"));
		bParamCogni = new JButton(imageCognitonConfig);
		bParamCogni.setToolTipText("Modifier les parametres des cognitons");
		bParamCogni.addActionListener(new ActionParametreToolBarListener(this, 1));
		toolBar.add(bParamCogni);
		
		
		
		
		
		this.add(toolBar , BorderLayout.NORTH);
		
		
        
        
        label = new ArrayList<JLabel>();
        comboBox = new ArrayList<JComboBox>();

		fields = Configuration.class.getDeclaredFields();
		
		for (int i = 0; i < fields.length;i++){

			label.add(new JLabel(fields[i].getName()));
		    boiteConf.add(label.get(i));
		    
	    	comboBox.add(new JComboBox());

	    	Annotation[] anno = fields[i].getAnnotations();

	    	
		    if (fields[i].getType().equals(Integer.class)){
		    	if(anno.length > 0){
			    	for (int j = ((ParametrisationInteger) anno[0]).min() ; j <= ((ParametrisationInteger) anno[0]).max(); j += ((ParametrisationInteger) anno[0]).pas()){
				    	comboBox.get(i).addItem(j);
				    	if (((Integer)fields[i].get(null)).equals(j)){
				    		comboBox.get(i).setSelectedIndex(comboBox.get(i).getItemCount()-1);
				    	}
			    	}
			    	
			    	comboBox.get(i).setToolTipText(((ParametrisationInteger) anno[0]).toolTip());
		    	}
		    }
		    else if (fields[i].getType().equals(Double.class)){
		    	if(anno.length > 0){
			    	for (double j = ((ParametrisationDouble) anno[0]).min() ; j <= ((ParametrisationDouble) anno[0]).max(); j += ((ParametrisationDouble) anno[0]).pas()){
				    	comboBox.get(i).addItem(Math.round(j*10000.0)/10000.0);  //Arrondi a quatres dŽcimales
				    	if (((Double)fields[i].get(null)).equals(Math.round(j*10000.0)/10000.0)){
				    		comboBox.get(i).setSelectedIndex(comboBox.get(i).getItemCount()-1);
				    	}
			    	}
			    	comboBox.get(i).setToolTipText(((ParametrisationDouble) anno[0]).toolTip());
		    	}
		    }  
		    else{
		    	comboBox.get(i).addItem("Non modifiable");
		    }
	    	comboBox.get(i).addActionListener(new ActionParametresListener(this, i));
		    boiteConf.add(comboBox.get(i));



		}

		
		
		
		/* La boite pour les parametres de cognitons*/

		listeCognitons = new ArrayList<Class>();
		label2 = new ArrayList<JLabel>();
		comboBox2 = new ArrayList<JComboBox>();
		
		String s;		
		File[] files;
		Field field, fPoids;

		
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/beliefs").listFiles();	
		for (File file : files) {
		    if (file.isFile()) {
		        s = file.getName().split("\\.")[0];
		        listeCognitons.add(Class.forName("civilisation.individu.cognitons.beliefs." + s));	
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/memes").listFiles();	
		for (File file : files) {
		    if (file.isFile()) {
		        s = file.getName().split("\\.")[0];
		        listeCognitons.add(Class.forName("civilisation.individu.cognitons.memes." + s));		        
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/percepts").listFiles();	
		for (File file : files) {
		    if (file.isFile()) {
		        s = file.getName().split("\\.")[0];
		        listeCognitons.add(Class.forName("civilisation.individu.cognitons.percepts." + s));		        
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/skills").listFiles();	
		for (File file : files) {
		    if (file.isFile()) {
		        s = file.getName().split("\\.")[0];
		        listeCognitons.add(Class.forName("civilisation.individu.cognitons.skills." + s));		        
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/traits").listFiles();	
		for (File file : files) {
		    if (file.isFile()) {
		        s = file.getName().split("\\.")[0];
		        listeCognitons.add(Class.forName("civilisation.individu.cognitons.traits." + s));		  
		        
		    }
		}

		for (int i = 0; i < listeCognitons.size(); i++){
			field = listeCognitons.get(i).getDeclaredField("plans");
			fPoids = listeCognitons.get(i).getDeclaredField("poids");
		    if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) && java.lang.reflect.Modifier.isStatic(fPoids.getModifiers())) {  /*Si le champ est statique*/
		    	
		    	label2.add(new JLabel(listeCognitons.get(i).toString()));
		    	label2.get(label2.size()-1).setForeground(Color.blue);
		    	
				String[] arrayString = (String[]) field.get(null);
				int[] arrayInt = (int[]) fPoids.get(null);

				int taille = Array.getLength(arrayString);
		    	if (taille > 0) boiteCogni.add(label2.get(label2.size()-1));

				for (int j = 0; j < taille ; j++ ){
			    	label2.add(new JLabel("\t" + arrayString[j]));
			    	boiteCogni.add(label2.get(label2.size()-1));
			    	comboBox2.add(new JComboBox());
			    	for (int k = -100; k <= 100; k++){
				    	comboBox2.get(comboBox2.size() - 1).addItem(k);
			    	}
			    	comboBox2.get(comboBox2.size() - 1).setSelectedIndex(arrayInt[j] + 100);
			    	comboBox2.get(comboBox2.size() - 1).addActionListener(new ActionParametresCogniListener(this, comboBox2.size() - 1 - j, i)); //On fourni l'indice de la premiere comboBox associee a la classe
			    	boiteCogni.add(comboBox2.get(comboBox2.size() - 1));
				}
		    }


			
		}
		
		
		
		
		
		
		
		/* Mise en place finale*/
		this.add(boiteConf , BorderLayout.CENTER);

		
		this.setVisible(true);

	}



	public void modifierParametre(int index) throws IllegalArgumentException, IllegalAccessException {

	    if (fields[index].getType().equals(Integer.class)){
	        final Object oldValue = fields[index].get(null);
	    	fields[index].set(oldValue, comboBox.get(index).getSelectedItem());
	    }
	    else if (fields[index].getType().equals(Double.class)){
	        final Object oldValue = fields[index].get(null);
	    	fields[index].set(oldValue, comboBox.get(index).getSelectedItem());
	    }
	    
	}
	
	public void permuterModifierConf(){
		clearBox();
		this.add(boiteConf , BorderLayout.CENTER);	
		this.revalidate();
		this.repaint();
	}
	
	public void permuterModifierCogni(){
		clearBox();
		this.add(boiteCogni , BorderLayout.CENTER);	
		this.revalidate();
		this.repaint();
	}
	
	private void clearBox(){
		this.remove(boiteConf);
		this.remove(boiteCogni);

	}



	public void modifierParametreCogni(int index, int indiceCogni) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field fPoids = listeCognitons.get(indiceCogni).getDeclaredField("poids");
		int[] arrayInt = (int[]) fPoids.get(null);
		int taille = Array.getLength(arrayInt);
		int[] newArrayInt = new int[taille];

		for (int i = 0; i < taille; i++){
			newArrayInt[i] = (Integer) comboBox2.get(index + i).getSelectedItem();
		}
    	fPoids.set(arrayInt, newArrayInt);

	}


	
}




