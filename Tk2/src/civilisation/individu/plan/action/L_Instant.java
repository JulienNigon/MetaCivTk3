package civilisation.individu.plan.action;

import javax.swing.ImageIcon;

import civilisation.individu.Humain;

public class L_Instant extends LAction{
	
	@Override
	public Action effectuer(Humain h) {	
	//TODO a refaire avec le mécanisme de pile
		
		
		/*
		Action next = listeActions.get(0);
		while ((next = next.effectuer(h)) != null) {
			if (next != null) {
				h.getEsprit()
			}
		}
*/
		return nextAction;
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
		return super.getInfo() + " All actions includes in this logical controller will be played in one tick.<html>";
	}
	
}
