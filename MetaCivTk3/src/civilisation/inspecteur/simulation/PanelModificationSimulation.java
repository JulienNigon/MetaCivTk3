package civilisation.inspecteur.simulation;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.simulation.attributes.PanelAttributes;
import civilisation.inspecteur.simulation.civilisations.ActionsToolBarListeCivilisations;
import civilisation.inspecteur.simulation.civilisations.PanelCivilisations;
import civilisation.inspecteur.simulation.civilisations.PanelListeCivilisations;
import civilisation.inspecteur.simulation.environnement.ActionsToolBarEnvironnement;
import civilisation.inspecteur.simulation.environnement.ActionsToolBarTerrains;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;
import civilisation.inspecteur.simulation.environnement.PanelTerrains;
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
	JButton boutonAddCloudCogniton;

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
	
	JToolBar toolBarGroupManager;
	
	JToolBar toolBarGroupTree;

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
		
		ImageIcon iconeSauvegarder = new ImageIcon(this.getClass().getResource("../icones/disk-black.png"));	
		boutonSauvegarder = new JButton(iconeSauvegarder);
		boutonSauvegarder.setToolTipText("Save");
		boutonSauvegarder.addActionListener(new ActionPanelCognitonsGraphiques(this, 0));
		toolBar.add(boutonSauvegarder);
		
		ImageIcon iconeArchiver = new ImageIcon(this.getClass().getResource("../icones/disks-black.png"));	
		boutonArchiver = new JButton(iconeArchiver);
		boutonArchiver.setToolTipText("Make a copy of current save");
		boutonArchiver.addActionListener(new ActionPanelCognitonsGraphiques(this, 1));
		toolBar.add(boutonArchiver);
		
		toolBar.addSeparator();

		ImageIcon iconeStructureCognitive = new ImageIcon(this.getClass().getResource("../icones/brain.png"));	
		boutonStructureCognitive = new JButton(iconeStructureCognitive);
		boutonStructureCognitive.setToolTipText("Edit cognitive scheme");
		boutonStructureCognitive.addActionListener(new ActionPanelCognitonsGraphiques(this, 2));
		toolBar.add(boutonStructureCognitive);

		ImageIcon iconeEnvironnement = new ImageIcon(this.getClass().getResource("../icones/globe.png"));	
		boutonEnvironnement = new JButton(iconeEnvironnement);
		boutonEnvironnement.setToolTipText("Edit environment");
		boutonEnvironnement.addActionListener(new ActionPanelCognitonsGraphiques(this, 3));
		toolBar.add(boutonEnvironnement);
		
		ImageIcon iconeObjets = new ImageIcon(this.getClass().getResource("../icones/briefcase.png"));	
		boutonObjets = new JButton(iconeObjets);
		boutonObjets.setToolTipText("Edit item");
		boutonObjets.addActionListener(new ActionPanelCognitonsGraphiques(this, 4));
		toolBar.add(boutonObjets);
		
		ImageIcon iconeCivilisations = new ImageIcon(this.getClass().getResource("../icones/bank.png"));	
		boutonCivilisations = new JButton(iconeCivilisations);
		boutonCivilisations.setToolTipText("Edit civilizations");
		boutonCivilisations.addActionListener(new ActionPanelCognitonsGraphiques(this, 5));
		toolBar.add(boutonCivilisations);
		
		ImageIcon iconeAttributes = new ImageIcon(this.getClass().getResource("../icones/blue-document-attribute.png"));	
		boutonAttribute = new JButton(iconeAttributes);
		boutonAttribute.setToolTipText("Edit attributes");
		boutonAttribute.addActionListener(new ActionPanelCognitonsGraphiques(this, 6));
		toolBar.add(boutonAttribute);
		
		ImageIcon iconeGroupManager = new ImageIcon(this.getClass().getResource("../icones/blue-document-attribute.png"));	
		boutonGroupManager = new JButton(iconeGroupManager);
		boutonGroupManager.setToolTipText("Manage group");
		boutonGroupManager.addActionListener(new ActionPanelCognitonsGraphiques(this, 7));
		toolBar.add(boutonGroupManager);
		


		panelArbreActions = new PanelArbreActions(null);
		panelStructureCognitive = new PanelStructureCognitive(this);
		panelEnvironnement = new PanelEnvironnement(this);
		panelGroupManager = new PanelGroupManager(this);

		panelTerrains = new PanelTerrains();
		panelObjets = new PanelObjets(this , panelListeObjets);
		panelListeObjets = new PanelListeObjets(panelObjets);
		panelObjets.setPanelListeObjets(panelListeObjets);
		panelCivilisations = new PanelCivilisations(this , panelListeCivilisations);
		panelListeCivilisations = new PanelListeCivilisations(panelCivilisations);
		panelCivilisations.setPanelListeCivilisations(panelListeCivilisations);
		panelAttributes = new PanelAttributes(this);

		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Arborescence d'actions");
		bordure.setTitleJustification(TitledBorder.LEFT);
		bordure.setTitle("Action tree");
		panelArbreActions.setBorder(bordure);
		
		
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Arborescence d'actions");
		bordure.setTitleJustification(TitledBorder.LEFT);
		bordure.setTitle("Cognitons and plans");
		panelStructureCognitive.setBorder(bordure);
		
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Arborescence d'actions");
		bordure.setTitleJustification(TitledBorder.LEFT);
		bordure.setTitle("Environment");
		panelEnvironnement.setBorder(bordure);
		
		bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Arborescence d'actions");
		bordure.setTitleJustification(TitledBorder.LEFT);
		bordure.setTitle("Patch type");
		panelTerrains.setBorder(bordure);
		 

		/*Creation de la toolBar pour la structure cognitive*/
		toolBarStructureCognitive = new JToolBar();
		
		ImageIcon iconeAjouterCogniton = new ImageIcon(this.getClass().getResource("../icones/brain--plus.png"));
		boutonAjouterCogniton = new JButton(iconeAjouterCogniton);
		boutonAjouterCogniton.addActionListener(new ActionStructureCognitive(this,0));
		toolBarStructureCognitive.add(boutonAjouterCogniton);
		
		ImageIcon iconeAjouterPlan = new ImageIcon(this.getClass().getResource("../icones/hammer--plus.png"));
		boutonAjouterPlan = new JButton(iconeAjouterPlan);
		boutonAjouterPlan.addActionListener(new ActionStructureCognitive(this,1));
		toolBarStructureCognitive.add(boutonAjouterPlan);
		
		ImageIcon iconeAddCloudCogniton = new ImageIcon(this.getClass().getResource("../icones/weather-cloud.png"));
		boutonAddCloudCogniton = new JButton(iconeAddCloudCogniton);
		boutonAddCloudCogniton.addActionListener(new ActionStructureCognitive(this,2));
		toolBarStructureCognitive.add(boutonAddCloudCogniton);
		
		/*Creation de la toolBar pour l'environnement*/
		toolBarEnvironnement = new JToolBar();
		
		ImageIcon iconeEnregistrerEnvironnement = new ImageIcon(this.getClass().getResource("../icones/disk.png"));
		enregistrerEnvironnement = new JButton(iconeEnregistrerEnvironnement);
		enregistrerEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,0));
		enregistrerEnvironnement.setToolTipText("Enregistrer cet environnement");
		toolBarEnvironnement.add(enregistrerEnvironnement);
		
		ImageIcon iconeChargerEnvironnement = new ImageIcon(this.getClass().getResource("../icones/disk--arrow.png"));
		chargerEnvironnement = new JButton(iconeChargerEnvironnement);
		chargerEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,1));
		chargerEnvironnement.setToolTipText("Charger un environnement");
		toolBarEnvironnement.add(chargerEnvironnement);
		
		ImageIcon iconeLimitesEnvironnement = new ImageIcon(this.getClass().getResource("../icones/compass.png"));
		limitesEnvironnement = new JButton(iconeLimitesEnvironnement);
		limitesEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,2));
		limitesEnvironnement.setToolTipText("D�finir les limites de l'environnement");
		toolBarEnvironnement.add(limitesEnvironnement);
		
		ImageIcon iconeGenererEnvironnement = new ImageIcon(this.getClass().getResource("../icones/picture--arrow.png"));
		genererEnvironnement = new JButton(iconeGenererEnvironnement);
		genererEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,3));
		genererEnvironnement.setToolTipText("Generer un environnement � partir d'une image existante");
		toolBarEnvironnement.add(genererEnvironnement);
		
		toolBarEnvironnement.addSeparator();

		ImageIcon iconeCrayon = new ImageIcon(this.getClass().getResource("../icones/pencil.png"));
		crayon = new JButton(iconeCrayon);
		crayon.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,4));
		crayon.setToolTipText("Dessiner avec le crayon");
		toolBarEnvironnement.add(crayon);
		
		ImageIcon iconePotPeinture = new ImageIcon(this.getClass().getResource("../icones/paint-can.png"));
		potPeinture = new JButton(iconePotPeinture);
		potPeinture.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,5));
		potPeinture.setToolTipText("Dessiner avec le pot de peinture");
		toolBarEnvironnement.add(potPeinture);
		
		toolBarEnvironnement.addSeparator();

		ImageIcon iconeZoomer = new ImageIcon(this.getClass().getResource("../icones/plus.png"));
		zoomer = new JButton(iconeZoomer);
		zoomer.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,6));
		zoomer.setToolTipText("Zoomer");
		toolBarEnvironnement.add(zoomer);
		
		ImageIcon iconeDezoomer = new ImageIcon(this.getClass().getResource("../icones/minus.png"));
		dezoomer = new JButton(iconeDezoomer);
		dezoomer.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,7));
		dezoomer.setToolTipText("Dezoomer");
		toolBarEnvironnement.add(dezoomer);
		
		toolBarEnvironnement.addSeparator();

		ImageIcon iconeChoisirEnv = new ImageIcon(this.getClass().getResource("../icones/ui-color-picker-switch.png"));
		choisirEnvironnementActif = new JButton(iconeChoisirEnv);
		choisirEnvironnementActif.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,8));
		choisirEnvironnementActif.setToolTipText("Choisir l'environnment � utiliser pour la simulation");
		toolBarEnvironnement.add(choisirEnvironnementActif);

		ImageIcon iconePheromone = new ImageIcon(this.getClass().getResource("../icones/ui-color-picker-switch.png"));
		pheromone = new JButton(iconePheromone);
		pheromone.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,9));
		pheromone.setToolTipText("Manage pheromon");
		toolBarEnvironnement.add(pheromone);
		
		/*Creation de la toolBar pour l'arbre d'actions*/	
		ImageIcon icone = new ImageIcon(this.getClass().getResource("../icones/lightning--plus.png"));
		ajouterAction = new JButton(icone);
		ajouterAction.setToolTipText("Add a new action at the start of the plan");
		ajouterAction.addActionListener(new ActionsToolBarArbreActions(panelArbreActions , 0));

		toolBarArbreActions = new JToolBar();
		toolBarArbreActions.add(ajouterAction);
		
		
		
		/*Creation de la toolBar pour la liste des terrains*/	
		toolBarListeTerrains = new JToolBar();

		icone = new ImageIcon(this.getClass().getResource("../icones/tree--plus.png"));
		ajouterTerrain = new JButton(icone);
		ajouterTerrain.addActionListener(new ActionsToolBarTerrains(panelTerrains,0));
		toolBarListeTerrains.add(ajouterTerrain);

		/*Creation de la toolBar pour la liste des objets*/	
		toolBarListeObjets = new JToolBar();
		
		icone = new ImageIcon(this.getClass().getResource("../icones/ui-color-picker-switch.png"));
		createItem = new JButton(icone);
		createItem.addActionListener(new ActionsToolBarListeObjets(panelListeObjets,0));
		createItem.setToolTipText("Create new item");
		toolBarListeObjets.add(createItem);
		
		/*Creation de la toolBar pour le panel des objets*/	
		toolBarObjets = new JToolBar();
		
		/*ToolBar for group manager*/	
		toolBarGroupManager = new JToolBar();
		
		/*Creation de la toolBar pour la liste des civilisations*/	
		toolBarListeCivilisations = new JToolBar();
		
		icone = new ImageIcon(this.getClass().getResource("../icones/bank.png"));
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
		
		panelCentral.add(panelEnvironnement, BorderLayout.CENTER);
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
		
		panelCentral.add(panelStructureCognitive, BorderLayout.CENTER);
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
		
		panelCentral.add(panelGroupManager, BorderLayout.CENTER);
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
	


}
