package civilisation.individu.plan.action;

import javax.swing.ImageIcon;

import civilisation.individu.Humain;

public class L_Random extends Action{

	double angle;
	
	@Override
	public Action effectuer(Humain h) {	
		int rand = (int)( Math.random()*listeActions.size());
		System.out.println(rand);
		listeActions.get(rand).effectuer(h);
		return nextAction;
	}

	public ImageIcon getIcon(){
		return new ImageIcon(this.getClass().getResource("../../../inspecteur/icones/processor.png"));
	}
	
}
