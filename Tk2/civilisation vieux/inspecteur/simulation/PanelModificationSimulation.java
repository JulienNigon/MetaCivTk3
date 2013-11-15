package civilisation.inspecteur.simulation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import civilisation.individu.Humain;
import civilisation.individu.plan.NPlan;
import civilisation.individu.plan.action.Action;
import civilisation.inspecteur.ActionInspecteurListener;
import civilisation.inspecteur.PanelGenealogie;
import civilisation.inspecteur.PanelInventaire;
import civilisation.inspecteur.PanelListeCognitons;
import civilisation.inspecteur.PanelListePlans;
import civilisation.inspecteur.PanelPatch;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.environnement.ActionsToolBarEnvironnement;
import civilisation.inspecteur.simulation.environnement.ActionsToolBarTerrains;
import civilisation.inspecteur.simulation.environnement.PanelEnvironnement;
import civilisation.inspecteur.simulation.environnement.PanelTerrains;
import civilisation.inspecteur.simulation.objets.PanelListeObjets;
import civilisation.inspecteur.simulation.objets.PanelObjets;
import civilisation.world.World;

public class PanelModificationSimulation extends JPanel{

	JToolBar toolBar = new JToolBar();
	
	JButton boutonSauvegarder;
	JButton boutonArchiver;
	JButton boutonStructureCognitive;
	JButton boutonEnvironnement;
	JButton boutonObjets;

	PanelStructureCognitive panelStructureCognitive;
	PanelEnvironnement panelEnvironnement;
	PanelArbreActions panelArbreActions;
	PanelTerrains panelTerrains;
	PanelObjets panelObjets;
	PanelListeObjets panelListeObjets;
	
	JPanel panelCentral;
	JPanel panelEast;

	JToolBar toolBarStructureCognitive;
	JButton boutonAjouterCogniton;
	JButton boutonAjouterPlan;

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

	JToolBar toolBarListeTerrains;
	JButton ajouterTerrain;
	
	JToolBar toolBarListeObjets;

	JToolBar toolBarObjets;

	
	int agentID = 0;
	
	
	
	public PanelModificationSimulation()
	{
		super();
		
		this.setLayout(new BorderLayout());
		
		ImageIcon iconeSauvegarder = new ImageIcon(this.getClass().getResource("../icones/disk-black.png"));	
		boutonSauvegarder = new JButton(iconeSauvegarder);
		boutonSauvegarder.setToolTipText("Sauvegarder");
		boutonSauvegarder.addActionListener(new ActionPanelCognitonsGraphiques(this, 0));
		toolBar.add(boutonSauvegarder);
		
		ImageIcon iconeArchiver = new ImageIcon(this.getClass().getResource("../icones/disks-black.png"));	
		boutonArchiver = new JButton(iconeArchiver);
		boutonArchiver.setToolTipText("Archiver la version actuelle");
		boutonArchiver.addActionListener(new ActionPanelCognitonsGraphiques(this, 1));
		toolBar.add(boutonArchiver);
		
		toolBar.addSeparator();

		ImageIcon iconeStructureCognitive = new ImageIcon(this.getClass().getResource("../icones/brain.png"));	
		boutonStructureCognitive = new JButton(iconeStructureCognitive);
		boutonStructureCognitive.setToolTipText("Editer la structure cognitive");
		boutonStructureCognitive.addActionListener(new ActionPanelCognitonsGraphiques(this, 2));
		toolBar.add(boutonStructureCognitive);

		ImageIcon iconeEnvironnement = new ImageIcon(this.getClass().getResource("../icones/globe.png"));	
		boutonEnvironnement = new JButton(iconeEnvironnement);
		boutonEnvironnement.setToolTipText("Editer l'environnement de la simulation");
		boutonEnvironnement.addActionListener(new ActionPanelCognitonsGraphiques(this, 3));
		toolBar.add(boutonEnvironnement);
		
		ImageIcon iconeObjets = new ImageIcon(this.getClass().getResource("../icones/globe.png"));	
		boutonObjets = new JButton(iconeObjets);
		boutonObjets.setToolTipText("Editer les objets d'inventaire");
		boutonObjets.addActionListener(new ActionPanelCognitonsGraphiques(this, 4));
		toolBar.add(boutonObjets);
		
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Arborescence d'actions");
		bordure.setTitleJustification(TitledBorder.LEFT);

		panelArbreActions = new PanelArbreActions(null);
		panelStructureCognitive = new PanelStructureCognitive(this);
		panelEnvironnement = new PanelEnvironnement(this);
		panelTerrains = new PanelTerrains();
		panelListeObjets = new PanelListeObjets();
		panelObjets = new PanelObjets(this);

		panelArbreActions.setBorder(bordure);
		bordure.setTitle("Structure cognitive");
		panelStructureCognitive.setBorder(bordure);
		bordure.setTitle("Carte de l'environnement");
		panelEnvironnement.setBorder(bordure);
		bordure.setTitle("Liste des terrains");
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
		limitesEnvironnement.setToolTipText("Définir les limites de l'environnement");
		toolBarEnvironnement.add(limitesEnvironnement);
		
		ImageIcon iconeGenererEnvironnement = new ImageIcon(this.getClass().getResource("../icones/picture--arrow.png"));
		genererEnvironnement = new JButton(iconeGenererEnvironnement);
		genererEnvironnement.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,3));
		genererEnvironnement.setToolTipText("Generer un environnement à partir d'une image existante");
		toolBarEnvironnement.add(genererEnvironnement);
		
		toolBarEnvironnement.addSeparator();

		ImageIcon iconeCrayon = new ImageIcon(this.getClass().getResource("../icones/pencil.png"));
		crayon = new JButton(iconeCrayon);
		crayon.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,4));
		crayon.setToolTipText("Dessiner avec le crayon");
		crayon.setEnabled(false); /*Mode de dessin utilisé par défaut*/
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

		ImageIcon iconeChoisirEnv = new ImageIcon(this.getClass().getResource("../icones/paint-can.png"));
		choisirEnvironnementActif = new JButton(iconeChoisirEnv);
		choisirEnvironnementActif.addActionListener(new ActionsToolBarEnvironnement(panelEnvironnement,8));
		choisirEnvironnementActif.setToolTipText("Choisir l'environnment à utiliser pour la simulation");
		toolBarEnvironnement.add(choisirEnvironnementActif);
		
		/*Creation de la toolBar pour l'arbre d'actions*/	
		ImageIcon icone = new ImageIcon(this.getClass().getResource("../icones/lightning--plus.png"));
		ajouterAction = new JButton(icone);
		icone = new ImageIcon(this.getClass().getResource("../icones/lightning--plus.png"));
		ajouterSousAction = new JButton(icone);
		
		toolBarArbreActions = new JToolBar();
		toolBarArbreActions.add(ajouterSousAction);
		toolBarArbreActions.add(ajouterAction);
		
		
		
		/*Creation de la toolBar pour la liste des terrains*/	
		toolBarListeTerrains = new JToolBar();

		icone = new ImageIcon(this.getClass().getResource("../icones/tree--plus.png"));
		ajouterTerrain = new JButton(icone);
		ajouterTerrain.addActionListener(new ActionsToolBarTerrains(panelTerrains,0));
		toolBarListeTerrains.add(ajouterTerrain);

		/*Creation de la toolBar pour la liste des objets*/	
		toolBarListeObjets = new JToolBar();
		
		/*Creation de la toolBar pour le panel des objets*/	
		toolBarObjets = new JToolBar();
		
		
		this.add(toolBar, BorderLayout.NORTH);
		afficherStructureCognitive();


		
	}
	
	public void afficherEnvironnement(){
		
		this.boutonStructureCognitive.setEnabled(true);
		this.boutonObjets.setEnabled(true);
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
		
		this.boutonStructureCognitive.setEnabled(false);
		this.boutonObjets.setEnabled(true);
		this.boutonEnvironnement.setEnabled(true);

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
		
		this.boutonStructureCognitive.setEnabled(true);
		this.boutonObjets.setEnabled(false);
		this.boutonEnvironnement.setEnabled(true);

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
	
	public void changerArbreActions(NPlan plan){
		System.out.println("change to "+plan.getNom());
		panelEast.remove(panelArbreActions);
		panelArbreActions = new PanelArbreActions(plan);
		TitledBorder bordure = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Arborescence d'actions");
		bordure.setTitleJustification(TitledBorder.LEFT);
		panelArbreActions.setBorder(bordure);
		panelEast.add(panelArbreActions, BorderLayout.CENTER);
		this.validate();
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
	


}
