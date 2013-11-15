package civilisation.inspecteur.arbreCognitif;

import java.util.ArrayList;

import civilisation.individu.Humain;
import civilisation.individu.cognitons.Cogniton;
import civilisation.individu.plan.Plan;
import civilisation.inspecteur.animations.JJAnimationOpacite;
import civilisation.inspecteur.animations.JJAnimationRotation;
import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;

public class PanelArbreCognitif extends JJPanel{

	ArrayList<GCogniton> gCognitons;
	ArrayList<GPlan> gPlan;

	Humain h;
	ArrayList<Cogniton> allCognitons;
	ArrayList<Plan> plans;
	static double espacement = 40;
	static double espaceCognitonsPlans = 350;


	
	public PanelArbreCognitif()
	{
		super();		
	}
	
	public PanelArbreCognitif(Humain h)
	{
		super();
		this.h = h;
		gCognitons = new ArrayList<GCogniton>();
		gPlan = new ArrayList<GPlan>();

		allCognitons = h.getEsprit().getAllCognitons();
		plans = h.getEsprit().getProjets();
		
		for (int i = 0; i < allCognitons.size(); i++){
			
			gCognitons.add(new GCogniton(this,20,espacement*i,60,25, allCognitons.get(i)));
			//gCognitons.get(i).addAnimation(new JJAnimationRotation(-1, gCognitons.get(i), 0.01, false));
			this.add(gCognitons.get(i));
		}

		for (int i = 0; i < plans.size(); i++){
			
			gPlan.add(new GPlan(this, 350,espacement*i,60,25, plans.get(i)));
			//gCognitons.get(i).addAnimation(new JJAnimationRotation(-1, gCognitons.get(i), 0.01, false));
			this.add(gPlan.get(i));
		}
		
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
	
	
	
	}
