package civilisation.inspecteur.simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import civilisation.Configuration;
import civilisation.individu.decisionMaking.DecisionMaker;
import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.simulation.attributes.PanelAttributes;
import civilisation.inspecteur.simulation.civilisations.ActionsToolBarListeCivilisations;
import civilisation.inspecteur.simulation.civilisations.PanelCivilisations;
import civilisation.inspecteur.simulation.civilisations.PanelListeCivilisations;
import civilisation.inspecteur.simulation.environnement.ActionsToolBarEnvironnement;
import civilisation.inspecteur.simulation.environnement.ActionsToolBarTerrains;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;
import civilisation.inspecteur.simulation.environnement.PanelTerrains;
import civilisation.inspecteur.simulation.groupManager.GroupToolBar;
import civilisation.inspecteur.simulation.groupManager.GroupTreeToolBar;
import civilisation.inspecteur.simulation.groupManager.PanelGroupManager;
import civilisation.inspecteur.simulation.groupManager.PanelGroupTree;
import civilisation.inspecteur.simulation.objets.ActionsToolBarListeObjets;
import civilisation.inspecteur.simulation.objets.PanelListeObjets;
import civilisation.inspecteur.simulation.objets.PanelObjets;

public class PanelModificationSimulation extends JPanel{

	JToolBar toolBar;
	
	JButton boutonSauvegarder;
	JButton boutonArchiver;
	JButton boutonStructureCognitive;
	JButton boutonEnvironnement;
	JButton boutonObjets;
	JButton boutonCivilisations;
	JButton boutonAttribute;
	JButton boutonGroupManager;

	PanelStructureCognitive panelStructureCognitive;
	PanelEnvironnement panelEnvironnement;
	PanelArbreActions panelArbreActions;
	PanelTerrains panelTerrains;
	PanelObjets panelObjets;
	PanelListeObjets panelListeObjets;
	PanelCivilisations panelCivilisations;
	PanelListeCivilisations panelListeCivilisations;
	PanelAttributes panelAttributes;
	PanelGroupTree panelGroupTree;
	PanelGroupManager panelGroupManager;
	
	JPanel panelCentral;
	JPanel panelEast;

	JToolBar toolBarStructureCognitive;
	JButton boutonAjouterCogniton;
	JButton boutonAjouterPlan;
	JComboBox<DecisionMaker> selectDecisionMaker;

	JToolBar toolBarArbreActions;
	JButton ajouterAction;
	JButton ajouterSousAction;
	
	JToolBar toolBarEnvironnement;
	JButton enregistrerEnvironnement;
	JButton limitesEnvironnement;
	JButton chargerEnvironnement;
	JButton genererEnvironnement;
	JButton crayon;
	JButton potPeinture;
	JButton zoomer;
	JButton dezoomer;
	JButton choisirEnvironnementActif;
	JButton pheromone;
	
	GroupToolBar toolBarGroupManager;
	
	GroupTreeToolBar toolBarGroupTree;

	JToolBar toolBarCivilisations;
	
	JToolBar toolBarListeCivilisations;
	JButton createCivilization;

	
	JToolBar toolBarListeTerrains;
	JButton ajouterTerrain;
	
	JToolBar toolBarListeObjets;
	JButton createItem;
	
	JToolBar toolBarObjets;
	
	
	int agentID = 0;
	
	
	
	public PanelModificationSimulation()
	{
		super();
		
		this.setLayout(new BorderLayout());
		
		/*Create main toolBar*/
		toolBar = new JToolBar(SwingConstants.VERTICAL);
		
		ImageIcon iconeSauvegarder = Configuration.getIcon("disk-black.png");	
		boutonSauvegarder = new JButton(iconeSauvegarder);
		boutonSauvegarder.setToolTipText("Save");
		boutonSauvegarder.addActionListener(new ActionPanelCognitonsGraphiques(this, 0));
		toolBar.add(boutonSauvegarder);
		
		ImageIcon iconeArchiver = Configuration.getIcon("disks-black.png");	
		boutonArchiver = new JButton(iconeArchiver);
		boutonArchiver.setToolTipText("Make a copy of current save");
		boutonArchiver.addActionListener(new ActionPanelCognitonsGraphiques(this, 1));
		toolBar.add(boutonArchiver);
		
		toolBar.addSeparator();

		ImageIcon iconeStructureCognitive = Configuration.getIcon("brain.png");	
		boutonStructureCognitive = new JButton(iconeStructureCognitive);
		boutonStructureCognitive.setToolTipText("Edit cognitive scheme");
		boutonStructureCognitive.addActionListener(new ActionPanelCognitonsGraphiques(this, 2));
		toolBar.add(boutonStructureCognitive);

		ImageIcon iconeEnvironnement = Configuration.getIcon("globe.png");	
		boutonEnvironnement = new JButton(iconeEnvironnement);
		boutonEnvironnement.setToolTipText("Edit environment");
		boutonEnvironnement.addActionListener(new ActionPanelCognitonsGraphiques(this, 3));
		toolBar.add(boutonEnvironnement);
		
		ImageIcon iconeObjets = Configuration.getIcon("briefcase.png");	
		boutonObjets = new JButton(iconeObjets);
		boutonObjets.setToolTipText("Edit item");
		boutonObjets.addActionListener(new ActionPanelCognitonsGraphiques(this, 4));
		toolBar.add(boutonObjets);
		
		ImageIcon iconeCivilisations = Configuration.getIcon("bank.png");	
		boutonCivilisations = new JButton(iconeCivilisations);
		boutonCivilisations.setToolTipText("Edit civilizations");
		boutonCivilisations.addActionListener(new ActionPanelCognitonsGraphiques(this, 5));
		toolBar.add(boutonCivilisations);
		
		ImageIcon iconeAttributes = Configuration.getIcon("blue-document-attribute.png");	
		boutonAttribute = new JButton(iconeAttributes);
		boutonAttribute.setToolTipText("Edit attributes");
		boutonAttribute.addActionListener(new ActionPanelCognitonsGraphiques(this, 6));
		toolBar.add(boutonAttribute);
		
		ImageIcon iconeGroupManager = Configuration.getIcon("foaf.png");	
		boutonGroupManager = new JButton(iconeGroupManager);
		boutonGroupManager.setToolTipText("Manage group");
		boutonGroupManager.addActionListener(new ActionPanelCognitonsGraphiques(this, 7));
		toolBar.add(boutonGroupManager);
		


		panelArbreActions = new PanelArbreActions(null);
		panelStructureCognitive = new PanelStructureCognitive(this);
		panelEnvironnement = new PanelEnvironnement(this);
		panelGroupManager = new PanelGroupManager(this , null);
		panelGroupTree = new PanelGroupTree(panelGroupManager);

		panelTerrains = new PanelTerrains();
		panelObjets = new PanelObjets(this , panelListeObjets);
		panelListeObjets = new PanelListeObjets(panelObjets);
		panelObjets.setPanelListeObjets(panelListeObjets);
		panelCivilisations = new PanelCivilisations(this , panelListeCivilisations);
		panelListeCivilisations = new PanelListeCivilisations(panelCivilisations);
		panelCivilisations.setPanelListeCivilisations(panelListeCivilisations);
		panelAttributes = new PanelAttributes(this);

		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Action tree");
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelArbreActions.setBorder(bordure);
			
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Cognitons and plans");
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelStructureCognitive.setBorder(bordure);
		
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Environment");
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelEnvironnement.setBorder(bordure);
		
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Patch type");
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelTerrains.setBorder(bordure);
		
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Group manager");
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelGroupManager.setBorder(bordure);
		 
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Group tree");
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelGroupTree.setBorder(bordure);

		/*Creation de la toolBar pour la structure cognitive*/
		toolBarStructureCognitive = new JToolBar();
		
		ImageIcon iconeAjouterCogniton = Configuration.getIcon("brain--plus.png");
		boutonAjouterCogniton = new JButton(iconeAjouterCogniton);
		boutonAjouterCogniton.addActionListener(new ActionStructureCognitive(this,0));
		toolBarStructureCognitive.add(boutonAjouterCogniton);
		
		ImageIcon iconeAjouterPlan = Configuration.getIcon("hammer--plus.png");
		boutonAjouterPlan = new JButton(iconeAjouterPlan);
		boutonAjouterPlan.addActionListener(new ActionStructureCognitive(this,1));
		toolBarStructureCognitive.add(boutonAjouterPlan);
		
		this.selectDecisionMaker = new JComboBox<DecisionMaker>((DecisionMaker[]) Configuration.allDecisionMakers.toArray(new DecisionMaker[0]));
		selectDecisionMaker.setSelectedItem(Configuration.decisionMaker);
		toolBarStructureCognitive.add(selectDecisionMaker);
		
		/*Creation de la toolBar pour l'environnement*/
		toolBarEnvironnement = new JToolBar();
		
		ImageIcon iconeEnregistrerEnvironnement = Configuration.getIcon("disk.png");
		enregistrerEnvironnement = new JButton(iconeEnregistrerEnvironnement);
		enregistrerEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,0));
		enregistrerEnvironnement.setToolTipText("Enregistrer cet environnement");
		toolBarEnvironnement.add(enregistrerEnvironnement);
		
		ImageIcon iconeChargerEnvironnement = Configuration.getIcon("disk--arrow.png");
		chargerEnvironnement = new JButton(iconeChargerEnvironnement);
		chargerEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,1));
		chargerEnvironnement.setToolTipText("Charger un environnement");
		toolBarEnvironnement.add(chargerEnvironnement);
		
		ImageIcon iconeLimitesEnvironnement = Configuration.getIcon("compass.png");
		limitesEnvironnement = new JButton(iconeLimitesEnvironnement);
		limitesEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,2));
		limitesEnvironnement.setToolTipText("Set environment bounds");
		toolBarEnvironnement.add(limitesEnvironnement);
		
		ImageIcon iconeGenererEnvironnement = Configuration.getIcon("picture--arrow.png");
		genererEnvironnement = new JButton(iconeGenererEnvironnement);
		genererEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,3));
		genererEnvironnement.setToolTipText("Generer un environnement ___ partir d'une image existante");
		toolBarEnvironnement.add(genererEnvironnement);
		
		toolBarEnvironnement.addSeparator();

		ImageIcon iconeCrayon = Configuration.getIcon("pencil.png");
		crayon = new JButton(iconeCrayon);
		crayon.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,4));
		crayon.setToolTipText("Dessiner avec le crayon");
		toolBarEnvironnement.add(crayon);
		
		ImageIcon iconePotPeinture = Configuration.getIcon("paint-can.png");
		potPeinture = new JButton(iconePotPeinture);
		potPeinture.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,5));
		potPeinture.setToolTipText("Dessiner avec le pot de peinture");
		toolBarEnvironnement.add(potPeinture);
		
		toolBarEnvironnement.addSeparator();

		ImageIcon iconeZoomer = Configuration.getIcon("plus.png");
		zoomer = new JButton(iconeZoomer);
		zoomer.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,6));
		zoomer.setToolTipText("Zoomer");
		toolBarEnvironnement.add(zoomer);
		
		ImageIcon iconeDezoomer = Configuration.getIcon("minus.png");
		dezoomer = new JButton(iconeDezoomer);
		dezoomer.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,7));
		dezoomer.setToolTipText("Dezoomer");
		toolBarEnvironnement.add(dezoomer);
		
		toolBarEnvironnement.addSeparator();

		ImageIcon iconeChoisirEnv = Configuration.getIcon("ui-color-picker-switch.png");
		choisirEnvironnementActif = new JButton(iconeChoisirEnv);
		choisirEnvironnementActif.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,8));
		choisirEnvironnementActif.setToolTipText("Choisir l'environnment ___ utiliser pour la simulation");
		toolBarEnvironnement.add(choisirEnvironnementActif);

		ImageIcon iconePheromone = Configuration.getIcon("marker.png");
		pheromone = new JButton(iconePheromone);
		pheromone.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,9));
		pheromone.setToolTipText("Manage pheromon");
		toolBarEnvironnement.add(pheromone);
		
		/*Creation de la toolBar pour l'arbre d'actions*/	
		ImageIcon icone = Configuration.getIcon("lightning--plus.png");
		ajouterAction = new JButton(icone);
		ajouterAction.setToolTipText("Add a new action at the start of the plan");
		ajouterAction.addActionListener(new ActionsToolBarArbreActions(panelArbreActions , 0));

		toolBarArbreActions = new JToolBar();
		toolBarArbreActions.add(ajouterAction);
		
		
		
		/*Creation de la toolBar pour la liste des terrains*/	
		toolBarListeTerrains = new JToolBar();

		icone = Configuration.getIcon("tree--plus.png");
		ajouterTerrain = new JButton(icone);
		ajouterTerrain.addActionListener(new ActionsToolBarTerrains(panelTerrains,0));
		toolBarListeTerrains.add(ajouterTerrain);

		/*Creation de la toolBar pour la liste des objets*/	
		toolBarListeObjets = new JToolBar();
		
		icone = Configuration.getIcon("ui-color-picker-switch.png");
		createItem = new JButton(icone);
		createItem.addActionListener(new ActionsToolBarListeObjets(panelListeObjets,0));
		createItem.setToolTipText("Create new item");
		toolBarListeObjets.add(createItem);
		
		/*Creation de la toolBar pour le panel des objets*/	
		toolBarObjets = new JToolBar();
		
		/*ToolBar for group manager*/	
		toolBarGroupManager = new GroupToolBar(panelGroupManager);
		toolBarGroupTree = new GroupTreeToolBar(panelGroupTree);
		panelGroupManager.setToolBar(toolBarGroupManager);

		/*Creation de la toolBar pour la liste des civilisations*/	
		toolBarListeCivilisations = new JToolBar();
		
		icone = Configuration.getIcon("bank.png");
		createCivilization = new JButton(icone);
		createCivilization.addActionListener(new ActionsToolBarListeCivilisations(panelListeCivilisations,0));
		createCivilization.setToolTipText("Create new civilization");
		toolBarListeCivilisations.add(createCivilization);
		
		/*Creation de la toolBar pour le panel des civilisations*/	
		toolBarCivilisations = new JToolBar();
		
		this.add(toolBar, BorderLayout.WEST);
		afficherStructureCognitive();


		
	}
	
	public void afficherEnvironnement(){
		
		setPanelButtonAvailable();
		this.boutonEnvironnement.setEnabled(false);

		if (panelCentral != null){
			this.remove(panelCentral);
		}
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		panelCentral.add(new JScrollPane(panelEnvironnement,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		panelCentral.add(toolBarEnvironnement, BorderLayout.NORTH);

		
		if (panelEast != null){
			this.remove(panelEast);
		}
		panelEast = new JPanel();
		panelEast.setLayout(new BorderLayout());
		
		panelEast.add(panelTerrains, BorderLayout.CENTER);
		panelEast.add(toolBarListeTerrains, BorderLayout.NORTH);
		
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelEast, BorderLayout.EAST);
		
	}
	
	
	public void afficherStructureCognitive(){
		
		setPanelButtonAvailable();
		this.boutonStructureCognitive.setEnabled(false);


		if (panelCentral != null){
			this.remove(panelCentral);
		}
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		panelCentral.add(new JScrollPane(panelStructureCognitive,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);		
		panelCentral.add(toolBarStructureCognitive, BorderLayout.NORTH);

		
		if (panelEast != null){
			this.remove(panelEast);
		}
		panelEast = new JPanel();
		panelEast.setLayout(new BorderLayout());
		
		panelEast.add(panelArbreActions, BorderLayout.CENTER);
		panelEast.add(toolBarArbreActions, BorderLayout.NORTH);
		
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelEast, BorderLayout.EAST);
		
	}
	

	public void afficherObjets() {
		
		setPanelButtonAvailable();
		this.boutonObjets.setEnabled(false);

		if (panelCentral != null){
			this.remove(panelCentral);
		}
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		panelCentral.add(panelObjets, BorderLayout.CENTER);
		panelCentral.add(toolBarObjets, BorderLayout.NORTH);

		
		if (panelEast != null){
			this.remove(panelEast);
		}
		panelEast = new JPanel();
		panelEast.setLayout(new BorderLayout());
		
		panelEast.add(panelListeObjets, BorderLayout.CENTER);
		panelEast.add(toolBarListeObjets, BorderLayout.NORTH);
		
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelEast, BorderLayout.EAST);	
	}
	
	public void afficherCivilisations() {
		
		setPanelButtonAvailable();
		this.boutonCivilisations.setEnabled(false);

		if (panelCentral != null){
			this.remove(panelCentral);
		}
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		panelCentral.add(panelCivilisations, BorderLayout.CENTER);
		panelCentral.add(toolBarCivilisations, BorderLayout.NORTH);

		
		if (panelEast != null){
			this.remove(panelEast);
		}
		panelEast = new JPanel();
		panelEast.setLayout(new BorderLayout());
		
		panelEast.add(panelListeCivilisations, BorderLayout.CENTER);
		panelEast.add(toolBarListeCivilisations, BorderLayout.NORTH);
		
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelEast, BorderLayout.EAST);	
	}
	
	public void afficherAttributes() {
		
		setPanelButtonAvailable();
		this.boutonAttribute.setEnabled(false);

		if (panelCentral != null){
			this.remove(panelCentral);
		}
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		panelCentral.add(panelAttributes, BorderLayout.CENTER);

		if (panelEast != null){
			this.remove(panelEast);
		}

		this.add(panelCentral, BorderLayout.CENTER);
	}
	
	private void setPanelButtonAvailable(){
		this.boutonStructureCognitive.setEnabled(true);
		this.boutonObjets.setEnabled(true);
		this.boutonEnvironnement.setEnabled(true);
		this.boutonCivilisations.setEnabled(true);
		this.boutonAttribute.setEnabled(true);
		this.boutonGroupManager.setEnabled(true);
	}
	
	public void changerArbreActions(NPlan plan){
		System.out.println("change to "+plan.getNom());
		panelArbreActions.changePlan(plan);
		/*panelEast.remove(panelArbreActions);
		panelArbreActions = new PanelArbreActions(plan);
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), plan.getNom());
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelArbreActions.setBorder(bordure);
		panelEast.add(panelArbreActions, BorderLayout.CENTER);
		this.validate();*/
	}

	public PanelStructureCognitive getPanelStructureCognitive() {
		return panelStructureCognitive;
	}

	public PanelTerrains getPanelTerrains() {
		return panelTerrains;
	}

	public void setOutilDeDessinEnvironnement(int i) {
		if (i == 0){
			crayon.setEnabled(false);
			potPeinture.setEnabled(true);
		}
		else if (i == 1){
			crayon.setEnabled(true);
			potPeinture.setEnabled(false);
		}
	}

	public PanelListeObjets getPanelListeObjets() {
		return panelListeObjets;
	}

	public void showGroupManager() {
		//TODO
		setPanelButtonAvailable();
		this.boutonGroupManager.setEnabled(false);

		if (panelCentral != null){
			this.remove(panelCentral);
		}
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		panelCentral.add(new JScrollPane(panelGroupManager,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);		
		panelCentral.add(toolBarGroupManager, BorderLayout.NORTH);

		
		if (panelEast != null){
			this.remove(panelEast);
		}
		panelEast = new JPanel();
		panelEast.setLayout(new BorderLayout());
		
		panelEast.add(panelGroupTree, BorderLayout.CENTER);
		panelEast.add(toolBarGroupTree, BorderLayout.NORTH);
		
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelEast, BorderLayout.EAST);			
	}
	
	public String getSelectedDecisionMaker() {
		return selectDecisionMaker.getSelectedItem().toString();
	}


}
