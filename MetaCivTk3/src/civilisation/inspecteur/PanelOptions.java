package civilisation.inspecteur;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import turtlekit.kernel.Turtle;
import civilisation.Configuration;
import civilisation.GroupAndRole;
import civilisation.individu.Humain;
import civilisation.world.World;
import civilisation.world.WorldViewer;

/** 
 * Le JPanel contenant les options pour l'affichage
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public class PanelOptions extends JPanel{

	JToolBar toolBar = new JToolBar();
	Box boite;
	JComboBox planAffiche;
	JComboBox frontiereAffichee;
	JComboBox pheroMap;
	JComboBox groupesAndRoles;
	ArrayList<String> listePlan;
	
	public PanelOptions()
	{
		listePlan =new ArrayList<String>();
		listePlan.add("--NONE--");
		
		for (int i = 0; i < Configuration.plans.size(); i++) {
			listePlan.add(Configuration.plans.get(i).getNom());  
		}

		
        boite = new Box(BoxLayout.PAGE_AXIS);

        //boite.add(Box.createVerticalStrut(600));
        
        planAffiche = new JComboBox(listePlan.toArray());
        planAffiche.addActionListener(new ActionOptionsListener(this, 0));
        JLabel labelAffichage = new JLabel("Affichage des plans : ");
        
        frontiereAffichee = new JComboBox();
        frontiereAffichee.addItem("Fronti�res visibles");
        frontiereAffichee.addItem("Fronti�res masqu�es");
        frontiereAffichee.addActionListener(new ActionOptionsListener(this, 1));
        
        /* Allow to select a phero-map*/
        JLabel labelPheroMap = new JLabel("Phero map : ");
        pheroMap = new JComboBox();
        
        
        pheroMap.addItem("--NONE--");
		for (int i = 0; i < Configuration.itemsPheromones.size(); i++) {
			pheroMap.addItem(Configuration.itemsPheromones.get(i).getNom());  
		}
        pheroMap.addActionListener(new ActionOptionsListener(this, 2));
        
        groupesAndRoles = new JComboBox();
        groupesAndRoles.addItem("--NONE--");
		for (int j = 0; j < Configuration.groups.size(); j++){
			Object[] keys = (Object[]) Configuration.groups.get(j).getCulturons().keySet().toArray();
			for (int k = 0 ; k < Configuration.groups.get(j).getCulturons().size() ; k++) {
				groupesAndRoles.addItem(Configuration.groups.get(j).getName()+":"+(String)keys[k]);	
			}
		}
		groupesAndRoles.addActionListener(new ActionOptionsListener(this, 3));

        boite.add(labelAffichage);
		boite.add(planAffiche);
		boite.add(frontiereAffichee);
		boite.add(labelPheroMap);
		boite.add(pheroMap);
		boite.add(new JLabel("Show specific group and role :"));
		boite.add(groupesAndRoles);

		this.add(boite);
		
		this.setVisible(true);
	}
	
	
	public void modifierAffichagePlans()
	{
		if (planAffiche.getSelectedIndex() > 0) {
			WorldViewer.getInstance().setPlanVisible(Configuration.plans.get(planAffiche.getSelectedIndex() - 1));
		} else {
			WorldViewer.getInstance().setPlanVisible(null);
		}
	}
	
	public void modifierAffichageFrontieres()
	{
		if (frontiereAffichee.getSelectedItem().equals("Show limes :"))
		{
			WorldViewer.getInstance().setFrontieresVisibles(true);
		}
		else
		{
			WorldViewer.getInstance().setFrontieresVisibles(false);
		}

	}
	
	public void showPheroMap()
	{
		if (pheroMap.getSelectedIndex() > 0) {
			WorldViewer.getInstance().setPheroToMap(Configuration.itemsPheromones.get(pheroMap.getSelectedIndex() - 1));
		} else {
			WorldViewer.getInstance().setPheroToMap(null);
		}
	}


	public void showGroupAndRole() {
		
		for (int i = 0 ; i < Configuration.maxAgents ; i++) {
			
			if ((select(i) != null && select(i).getClass() == Humain.class))
			{
				System.out.println(i);
				if ( ((Humain)select(i)).getEsprit().hasGroupAndRole(new GroupAndRole((String)groupesAndRoles.getSelectedItem()) ))
				{
					System.out.println(true);
					((Humain) select(i)).isShowGroup = true;
				} else {
					((Humain) select(i)).isShowGroup = false;			
				}
			}		
		}
		
		
		/*if (groupesAndRoles.getSelectedIndex() > 0) {
			WorldViewer.getInstance().setGroupAndRoleToMap(new GroupAndRole((String)groupesAndRoles.getSelectedItem()));
		} else {
			WorldViewer.getInstance().setGroupAndRoleToMap(null);
		}	*/	
	}


	private Turtle select(int id) {
		return (Humain) World.getInstance().getTurtlesWithRoles("Humain").get(id);
	}
	


	
}




