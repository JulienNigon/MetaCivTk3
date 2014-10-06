package civilisation.individu.plan.action;

import civilisation.Configuration;
import civilisation.individu.Humain;

public class A_GetChampProche extends Action{

	public Action effectuer(Humain h)
	{
		int cpt = 1;
		for(int i = 0; i < Configuration.VisionRadius*2;++i)
		{
			for(int j = 0; j < Configuration.VisionRadius*2;++j)
			{
				if(h.getPatchAt(i - Configuration.VisionRadius, j - Configuration.VisionRadius).isMarkPresent("Champ"))
				{
					h.setCible(h.getPatchAt(i - Configuration.VisionRadius, j - Configuration.VisionRadius));
					break;
				}
			}
		}
		return nextAction;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Met la cible de Allervers sur le champ le plus proche dans le champ de vision.<html>";
	}

}
