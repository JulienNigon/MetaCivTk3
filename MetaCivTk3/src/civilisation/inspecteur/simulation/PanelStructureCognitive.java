package civilisation.inspecteur.simulation;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.Culturon;
import civilisation.individu.cognitons.NCogniton;
import civilisation.individu.plan.NPlan;
import civilisation.inspecteur.animations.JJAnimationOpacite;
import civilisation.inspecteur.animations.JJAnimationRotation;
import civilisation.inspecteur.animations.JJAnimationTranslation;
import civilisation.inspecteur.animations.JJAnimationTranslationEntreDeuxObjets;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.inspecteur.simulation.dialogues.DialogueEditerCogniton;

public class PanelStructureCognitive extends JJPanel{

	ArrayList<GCogniton> gCognitons;
	ArrayList<GPlan> gPlan;
	ArrayList<GLien> gLiens;
	ArrayList<GLien> gLiensConditionnels;
	ArrayList<GLien> gLinksTrigger;

	ArrayList<NCogniton> allCognitons;
	ArrayList<NPlan> plans;
	ArrayList<GTrigger> gTriggers;
	
	double espacement = 40;
	double espaceCognitonsPlans = 350;
	int tempsEntreApparitionSpheresLiens = 100;
	int compteur = 0;
	int initXTrigger = 12;
	
	PanelModificationSimulation panelPrincipal;
	
	JPopupMenu popupGPlans;
	JPopupMenu popupGCognitons;

	

	
	public PanelStructureCognitive(PanelModificationSimulation panelPrincipal)
	{
		super();
		this.panelPrincipal = panelPrincipal;
		this.setDelay(5);
		
		gCognitons = new ArrayList<GCogniton>();
		gPlan = new ArrayList<GPlan>();
		gLiens = new ArrayList<GLien>();
		gLiensConditionnels = new ArrayList<GLien>();
		gLinksTrigger = new ArrayList<GLien>();

		gTriggers = new ArrayList<GTrigger>();

		allCognitons = Configuration.cognitons;
		allCognitons.addAll(Configuration.cloudCognitons);
		plans = Configuration.plans;
		
		for (int i = 0; i < allCognitons.size(); i++){		
			if (allCognitons.get(i) instanceof Culturon) {
				showCloudCogniton((Culturon) allCognitons.get(i),20,40+espacement*i);
			} else {
				afficherCogniton(allCognitons.get(i),80,40+espacement*i);
			}
		}

		
		for (int i = 0; i < plans.size(); i++){	
			afficherPlan(plans.get(i), espaceCognitonsPlans,40+espacement*i);
		}
		
		creerLiensInfluence();
		creerLiensConditionnels();
		createTriggerLink();
		

		}

	

	public void selectionnerPlan(NPlan plan){
		panelPrincipal.changerArbreActions(plan);
	}
	
	
	public void appliquerTransparence(ArrayList<JJComponent> liste){
		
		boolean dansLaListe;
		
        for (int i = 0; i < this.getComponentCount() ; i++){
        	if (this.getComponent(i) instanceof JJComponent){
        		dansLaListe = false;
	        	for (int j = 0; j < liste.size(); j++){
	        		if (liste.get(j).equals(this.getComponent(i)))
	        		{
	        			dansLaListe = true;
	        		}
	        	}
	        	if (!dansLaListe){
	        		((JJComponent) this.getComponent(i)).addAnimation(new JJAnimationOpacite(35, (JJComponent) this.getComponent(i), -0.02, false));
	        	}
        	}
        }
	}
	
	@Override
	public void animate(){
		super.animate();
		compteur++;
		if (compteur == tempsEntreApparitionSpheresLiens){
			compteur = 0;
			for (int i = 0 ; i < gLiens.size(); i++){
				for (int j = 0 ; j < Math.abs(gLiens.get(i).getPoids()) ; j++){
					GIndicateurDeLiens gIndicLien;
					gIndicLien = new GIndicateurDeLiens(this, -500, -500, 16, 16, gLiens.get(i).getPoids() > 0);

					this.add(gIndicLien);
					JJAnimationTranslationEntreDeuxObjets anim = new JJAnimationTranslationEntreDeuxObjets(250, gIndicLien, gLiens.get(i).getA(),gLiens.get(i).getB(), true); 
					gIndicLien.addAnimation(anim);
					anim.decalerPas(j*(-5));
				}

				//gIndicLien.addAnimation(new JJAnimationTranslation(2000, gIndicLien, 1.,0.1, true));
			}
		}
		
	}
	
	public void afficherPopupPlan(MouseEvent e , GPlan p){

		popupGPlans = new JPopupMenu("Plan");
		JMenuItem editerPlan = new JMenuItem("Editer le Plan");
		editerPlan.addActionListener(new ActionsMenuGPlan(p,0));
		editerPlan.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
		popupGPlans.add(editerPlan);
		JMenuItem supprimerPlan = new JMenuItem("Supprimer");
		supprimerPlan.setIcon(new ImageIcon(this.getClass().getResource("../icones/cross.png")));
		popupGPlans.add(supprimerPlan);
		
		popupGPlans.show(this, (int)p.getXx() + e.getX(), (int)p.getYy() + e.getY());
	}
	
	public void afficherPopupCogniton(MouseEvent e , GCogniton c){
		
		popupGCognitons = new JPopupMenu("Cogniton");
		JMenuItem editerCogniton = new JMenuItem("Editer le Cogniton");
		editerCogniton.addActionListener(new ActionsMenuGCogniton(c,0));
		editerCogniton.setIcon(new ImageIcon(this.getClass().getResource("../icones/pencil.png")));
		popupGCognitons.add(editerCogniton);
		JMenuItem editerInfluences = new JMenuItem("Editer les liens d'influence");
		editerInfluences.addActionListener(new ActionsMenuGCogniton(c,1));
		editerInfluences.setIcon(new ImageIcon(this.getClass().getResource("../icones/arrow-out.png")));
		popupGCognitons.add(editerInfluences);
		JMenuItem editerConditions = new JMenuItem("Editer les liens conditionnels");
		editerConditions.addActionListener(new ActionsMenuGCogniton(c,2));
		editerConditions.setIcon(new ImageIcon(this.getClass().getResource("../icones/lock--arrow.png")));
		popupGCognitons.add(editerConditions);
		JMenuItem editTriggeringAttributes = new JMenuItem("Edit triggering attributes");
		editTriggeringAttributes.addActionListener(new ActionsMenuGCogniton(c,3));
		editTriggeringAttributes.setIcon(new ImageIcon(this.getClass().getResource("../icones/lock--arrow.png")));
		popupGCognitons.add(editTriggeringAttributes);
		JMenuItem editerChaine = new JMenuItem("Editer les liens inter-cognitons");
		editerChaine.setIcon(new ImageIcon(this.getClass().getResource("../icones/arrow-in-out.png")));
		popupGCognitons.add(editerChaine);
		JMenuItem supprimerCogniton = new JMenuItem("Supprimer");
		supprimerCogniton.setIcon(new ImageIcon(this.getClass().getResource("../icones/cross.png")));
		popupGCognitons.add(supprimerCogniton);
		popupGCognitons.show(this, (int)c.getXx() + e.getX(), (int)c.getYy() + e.getY());
	}


	public void afficherCogniton(NCogniton c , double posX , double posY){
		gCognitons.add(new GCogniton(this,posX,posY,60,25, c));
		//gCognitons.get(gCognitons.size()-1).addAnimation(new JJAnimationTranslation(-1, gCognitons.get(gCognitons.size()-1), 0.05, 0.05, false));
		this.add(gCognitons.get(gCognitons.size()-1));
		this.setComponentZOrder(gCognitons.get(gCognitons.size()-1), gCognitons.size()-1);
		this.showTrigger(gCognitons.get(gCognitons.size()-1));
	}
	
	public void afficherPlan(NPlan p , double posX , double posY){
		gPlan.add(new GPlan(this,posX,posY,60,25, p));
		//gPlan.get(gPlan.size()-1).addAnimation(new JJAnimationRotation(-1, gPlan.get(gPlan.size()-1), 0.01, false));
		this.add(gPlan.get(gPlan.size()-1));
		this.setComponentZOrder(gPlan.get(gPlan.size()-1), 0);
	}

	public void showCloudCogniton(Culturon c , double posX , double posY){
		gCognitons.add(new GCloudCogniton(this,posX,posY,60,25, c));
		//gCognitons.get(gCognitons.size()-1).addAnimation(new JJAnimationTranslation(-1, gCognitons.get(gCognitons.size()-1), 0.05, 0.05, false));
		this.add(gCognitons.get(gCognitons.size()-1));
		this.setComponentZOrder(gCognitons.get(gCognitons.size()-1), gCognitons.size()-1);
	}
	
	public void showTrigger(GCogniton c) {
		for (int i = 0 ; i < gTriggers.size(); i++) {
			
			GTrigger gt = gTriggers.get(i);

			if (gt.getgCogniton() == c) {
				this.remove(gt);
				gTriggers.remove(i);
			}			
			
		}
		
		// If the GTrigger doesn't exist, create it
		for (int i = 0; i < c.getCogniton().getTriggeringAttributes().size();i++){
			Object[] trigInfo = c.getCogniton().getTriggeringAttributes().get(i);
			GTrigger newGt = new GTrigger(this,initXTrigger,c.getYy());
			newGt.setAttributesIndex(Configuration.getAttributeIndexByName((String)trigInfo[0]));
			newGt.setValue((Integer)trigInfo[1]);
			newGt.setComparator((Integer)trigInfo[2]);
			newGt.setgCogniton(c);
			gTriggers.add(newGt);
			this.add(newGt);

		}
		
	}

	public void creerCogniton() {
		NCogniton nouveauCogniton = new NCogniton();
		Configuration.addCogniton(nouveauCogniton);
		nouveauCogniton.creerCognitonLambda();
		afficherCogniton(nouveauCogniton, 100,100);
		
	}
	
	public void creerPlan() {
		NPlan nouveauPlan = new NPlan();
		nouveauPlan.setNom("Nouveau plan");
		Configuration.addPlan(nouveauPlan);
		afficherPlan(nouveauPlan,100,100);
	}
	
	public void creerLiensInfluence(){
		for (int i = 0; i < gCognitons.size(); i++){
			for (int j = 0; j < gCognitons.get(i).getCogniton().getLiensPlans().size(); j++){
				int k = 0;
				while(!gCognitons.get(i).getCogniton().getLiensPlans().get(j).getP().equals(gPlan.get(k).getPlan())){
					k++;
				}
				gLiens.add(new GLien(this,gCognitons.get(i),gPlan.get(k),gCognitons.get(i).getCogniton().getLiensPlans().get(j).getPoids() , Color.BLACK));
				//this.add(gLiens.get(gLiens.size()-1));
			}

		}
		for (int i = 0; i < gCognitons.size(); i++){
			this.setComponentZOrder(gCognitons.get(i), this.getComponentCount()-1);
		}
		
		for (int i = 0; i < gPlan.size(); i++){
			this.setComponentZOrder(gPlan.get(i), this.getComponentCount()-1);
		}
	}
	
	public void creerLiensConditionnels(){
		for (int i = 0; i < gCognitons.size(); i++){
			for (int j = 0; j < gCognitons.get(i).getCogniton().getPlansAutorises().size(); j++){
				int k = 0;
				while(!gCognitons.get(i).getCogniton().getPlansAutorises().get(j).equals(gPlan.get(k).getPlan())){
					k++;
				}
				gLiensConditionnels.add(new GLien(this,gCognitons.get(i),
						gPlan.get(k),
						-1 , Color.RED));
				//this.add(gLiens.get(gLiens.size()-1));
			}

		}
		for (int i = 0; i < gCognitons.size(); i++){
			this.setComponentZOrder(gCognitons.get(i), this.getComponentCount()-1);
		}
		
		for (int i = 0; i < gPlan.size(); i++){
			this.setComponentZOrder(gPlan.get(i), this.getComponentCount()-1);
		}
	}
	
	public void createTriggerLink(){
		for (int i = 0; i < gTriggers.size(); i++){
				this.gLinksTrigger.add(new GLien(this,gTriggers.get(i),
						gTriggers.get(i).getgCogniton(),
						-1 , Color.ORANGE));
				//System.out.println(gCognitons.get(i).getCogniton().getNom() +" i");
		}
		
		for (int i = 0; i < gCognitons.size(); i++){
			this.setComponentZOrder(gCognitons.get(i), this.getComponentCount()-1);
		}
		
		for (int i = 0; i < gPlan.size(); i++){
			this.setComponentZOrder(gPlan.get(i), this.getComponentCount()-1);
		}
	}
	
	
	public void clearTriggerLink(){
		for (int i = 0 ; i < gLinksTrigger.size(); i++){
			this.remove(gLinksTrigger.get(i));
		}
		gLinksTrigger.clear();
	}
	
	public void supprimerLiensInfluence(){
		for (int i = 0 ; i < gLiens.size(); i++){
			this.remove(gLiens.get(i));
		}
		gLiens.clear();
	}
	
	public void supprimerLiensConditionnels(){
		for (int i = 0 ; i < gLiensConditionnels.size(); i++){
			this.remove(gLiensConditionnels.get(i));
		}
		gLiensConditionnels.clear();
	}
	
    @Override
	public void paintComponent(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
    	super.paintComponent(g);

    	/*TODO solution pas terrible pour les GLiens qui servent un peu � rien�*/
    	for (int i = 0; i < gLiens.size(); i++){
            g2d.setStroke(new BasicStroke(2));
    		g2d.drawLine((int)gLiens.get(i).getA().getCentreX(), (int)gLiens.get(i).getA().getCentreY(),(int) gLiens.get(i).getB().getCentreX(), (int)gLiens.get(i).getB().getCentreY());
    	}
    	
    	for (int i = 0; i < gLiensConditionnels.size(); i++){
    		g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(2));
    		g2d.drawLine((int)gLiensConditionnels.get(i).getA().getCentreX()-2, (int)gLiensConditionnels.get(i).getA().getCentreY()-2,(int) gLiensConditionnels.get(i).getB().getCentreX()-2, (int)gLiensConditionnels.get(i).getB().getCentreY()-2);
    	}
    	
    	for (int i = 0; i < this.gLinksTrigger.size(); i++){
    		g2d.setColor(Color.ORANGE);
            g2d.setStroke(new BasicStroke(3));
    		g2d.drawLine((int)gLinksTrigger.get(i).getA().getCentreX()-2, (int)gLinksTrigger.get(i).getA().getCentreY()-2,(int) gLinksTrigger.get(i).getB().getCentreX()-2, (int)gLinksTrigger.get(i).getB().getCentreY()-2);
    	}
    	
	}



	public void createCloudCogniton() {

		Culturon newCloudCogniton = new Culturon();
		Configuration.addCloudCogniton(newCloudCogniton);
		newCloudCogniton.creerCognitonLambda();
		afficherCogniton(newCloudCogniton, 100,100);		
	}
	
	}
