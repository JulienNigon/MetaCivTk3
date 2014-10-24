package civilisation.inspecteur.simulation.dialogues;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.group.Group;
import civilisation.group.GroupAndRole;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.individu.plan.action.Action;
import civilisation.individu.plan.action.Comparator;
import civilisation.individu.plan.action.OptionsActions;
import civilisation.inspecteur.simulation.PanelArbreActions;
import civilisation.inventaire.Objet;

public class DialogueEditerAction extends JDialog implements ActionListener, PropertyChangeListener{
	
	ArrayList<JComponent> boxs = new ArrayList<JComponent>();
	ArrayList<JComponent> components = new ArrayList<JComponent>();

    JOptionPane optionPane;
    PanelArbreActions p;
    Action a;
    ArrayList<String[]> schema;
    
	public DialogueEditerAction(Frame f , boolean modal , PanelArbreActions p , Action a) throws IOException{
		super(f,modal);

		this.setTitle("Editer une action");


		this.a = a;
		this.p = p;
		schema = a.getSchemaParametres();
		
		
		if (schema != null){
			for (int i = 0; i < schema.size(); i++){
				JComboBox box = new JComboBox();
				boolean isComboBox = true;
				if (schema.get(i)[0].equals("**Objet**")){
					for (int j = 0; j < Configuration.objets.size(); j++){
						box.addItem(Configuration.objets.get(j).getNom());
					}
					if(a != null && a.getOptions().get(i) != null)
					{
						box.setSelectedItem(((Objet)a.getOptions().get(i).getParametres().get(0)).getNom());
					}
				}
				else if (schema.get(i)[0].equals("**Cogniton**")){
					for (int j = 0; j < Configuration.cognitons.size(); j++){
						box.addItem(Configuration.cognitons.get(j).getNom());
					}
					if(a != null )
					{
						box.setSelectedItem(((TypeCogniton)a.getOptions().get(i).getParametres().get(0)).getNom());
					}
				}
				else if (schema.get(i)[0].equals("**Pheromone**")){
					for (int j = 0; j < Configuration.itemsPheromones.size(); j++){
						box.addItem(Configuration.itemsPheromones.get(j).getNom());
					}
					if(a != null )
					{
						box.setSelectedItem(((ItemPheromone)a.getOptions().get(i).getParametres().get(0)).getNom());
					}
				}
				else if (schema.get(i)[0].equals("**Integer**")){
					for (int j = Integer.parseInt(schema.get(i)[2]); j < Integer.parseInt(schema.get(i)[3]); j++){
						box.addItem(j);
					}
					box.setSelectedItem(Integer.parseInt((String) a.getOptions().get(i).getParametres().get(0)));
				}
				else if (schema.get(i)[0].equals("**Double**")){
					for (double j = Double.parseDouble(schema.get(i)[2]); j < Double.parseDouble(schema.get(i)[3]); j+=Double.parseDouble(schema.get(i)[4])){
						box.addItem(j);
					}
					box.setSelectedItem(Double.parseDouble((String) a.getOptions().get(i).getParametres().get(0).toString()));
				}
				else if (schema.get(i)[0].equals("**Attribute**")){
					for (int j = 0; j < Configuration.attributesNames.size() ; j++){
						box.addItem(Configuration.attributesNames.get(j));
					}
					if(a != null )
					{
						box.setSelectedItem(((String)a.getOptions().get(i).getParametres().get(0)));
					}
				}
				else if (schema.get(i)[0].equals("**Group**")){
					for (int j = 0; j < Configuration.groups.size(); j++){
						box.addItem(Configuration.groups.get(j).getName());
					}
					if(a != null )
					{
						box.setSelectedItem(((Group)a.getOptions().get(i).getParametres().get(0)).getName());
					}
				}
				else if (schema.get(i)[0].equals("**GroupAndRole**")){
					for (int j = 0; j < Configuration.groups.size(); j++){
						System.out.println(Configuration.groups.size() + " j : " + j + "keyset size : " + Configuration.groups.get(j).getCulturons().keySet().size());
						Object[] keys = (Object[]) Configuration.groups.get(j).getCulturons().keySet().toArray();
						for (int k = 0 ; k < Configuration.groups.get(j).getCulturons().size() ; k++) {
							box.addItem(Configuration.groups.get(j).getName()+":"+(String)keys[k]);	
						}
					}

				}
				else if (schema.get(i)[0].equals("**Comparator**")){
					for (int j = 0; j < Comparator.values().length ; j++){
						box.addItem(Comparator.values()[j].toSymbol());
					}
					if(a != null )
					{
						box.setSelectedItem(((Comparator)a.getOptions().get(i).getParametres().get(0)));
					}
				}
				else if (schema.get(i)[0].equals("**String**")){
					
					
					boxs.add(new JTextField(a.getOptions().get(i).getParametres().get(0).toString()));
					isComboBox = false;
				}
				else{
					for (int j = 0; j < schema.get(i).length; j++){
						box.addItem(schema.get(i)[j]);
					}
				}
				if (isComboBox){
					boxs.add(box);
				}
				
				Box hb = Box.createHorizontalBox();
				hb.add(new JLabel(schema.get(i)[1]));
				hb.add(boxs.get(boxs.size()-1));
				components.add(hb);
			}
		}

		JTextPane jTextPane = new JTextPane();
		jTextPane.setContentType("text/html");
		jTextPane.setText(a.getInfo());
		jTextPane.setEditable(false);
		jTextPane.setBackground(this.getBackground());
		components.add(jTextPane);
		Object[] array;
		if (components.size() != 0){
		    array = components.toArray();
		}
		else{
			JLabel label = new JLabel("You can't edit this action!");
		    array = new Object[1];
		    array[0] = label;
		}

		
		
	       

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
				a.clearOptions(); //Suppression des anciennes options
				
				for (int i = 0; i < boxs.size(); i++){	
					OptionsActions opt = null;

					if (schema.get(i)[0].equals("**Objet**")){
						opt = new OptionsActions(schema.get(i)[1]); /*Le deuxi___me terme est toujours le nom du param___tre pour les param___tres complexes*/
						opt.addParametre(Configuration.getObjetByName((String)((JComboBox) boxs.get(i)).getSelectedItem()));
					}
					else if (schema.get(i)[0].equals("**Pheromone**")){
						opt = new OptionsActions(schema.get(i)[1]); /*Le deuxi___me terme est toujours le nom du param___tre pour les param___tres complexes*/
						opt.addParametre(Configuration.getPheromoneByName((String)((JComboBox) boxs.get(i)).getSelectedItem()));
					}
					else if (schema.get(i)[0].equals("**Cogniton**")){
						opt = new OptionsActions(schema.get(i)[1]);
						opt.addParametre(Configuration.getCognitonByName((String)((JComboBox) boxs.get(i)).getSelectedItem()));
					}
					else if (schema.get(i)[0].equals("**Group**")){
						opt = new OptionsActions(schema.get(i)[1]);
						opt.addParametre(Configuration.getGroupModelByName((String)((JComboBox) boxs.get(i)).getSelectedItem()));
					}
					else if (schema.get(i)[0].equals("**GroupAndRole**")){
						opt = new OptionsActions(schema.get(i)[1]);
						opt.addParametre(new GroupAndRole((String)((JComboBox) boxs.get(i)).getSelectedItem()));
					}
					else if (schema.get(i)[0].equals("**Integer**")){
						opt = new OptionsActions(schema.get(i)[1]); /*Le deuxi___me terme est toujours le nom du param___tre pour les param___tres complexes*/
						opt.addParametre((Integer)((JComboBox) boxs.get(i)).getSelectedItem());
					}
					else if (schema.get(i)[0].equals("**Double**")){
						opt = new OptionsActions(schema.get(i)[1]); /*Le deuxi___me terme est toujours le nom du param___tre pour les param___tres complexes*/
						opt.addParametre((Double)((JComboBox) boxs.get(i)).getSelectedItem());
					}
					else if (schema.get(i)[0].equals("**Attribute**")){
						opt = new OptionsActions(schema.get(i)[1]);
						opt.addParametre(((JComboBox) boxs.get(i)).getSelectedItem());
					}
					else if (schema.get(i)[0].equals("**Comparator**")){
						opt = new OptionsActions(schema.get(i)[1]);
						opt.addParametre(Comparator.toComparator((String)((JComboBox) boxs.get(i)).getSelectedItem()));

					}
					else if (schema.get(i)[0].equals("**String**")){
						opt = new OptionsActions(schema.get(i)[1]);
						opt.addParametre(((JTextField) boxs.get(i)).getText(), "String");

					}
					else if (schema.get(i)[0] != null){
						System.out.println(schema.get(i)[0]);
						opt = new OptionsActions((String)((JComboBox) boxs.get(i)).getSelectedItem());
					}
					a.parametrerOption(opt);
				}

			/*	for (int i = 0; i < fields.size(); i++){	
					OptionsActions opt = null;
					a.parametrerOption(opt);
				}*/
			}		
	        setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
