package civilisation.individu.plan.action;

import javax.swing.ImageIcon;

import civilisation.individu.Humain;

public class L_Random extends LAction{
	
	@Override
	public Action effectuer(Humain h) {	
		int rand = (int)( Math.random()*listeActions.size());
		System.out.println(rand);
		h.getEsprit().getActions().push(nextAction);
		Action a = listeActions.get(rand).effectuer(h);	
		return a;
	}

	@Override
	public ImageIcon getIcon(){
		return new ImageIcon(this.getClass().getResource("../../../inspecteur/icones/processor.png"));
	}
	
	@Override
	public int getNumberActionSlot(){
		return -1;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Select randomly one sub-action.<html>";
	}
	
}
