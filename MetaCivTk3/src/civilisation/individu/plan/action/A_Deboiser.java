package civilisation.individu.plan.action;

import turtlekit.pheromone.Pheromone;
import civilisation.Configuration;
import civilisation.individu.Humain;

public class A_Deboiser extends Action {
	
	public Action effectuer(Humain h)
	{
		int quantite = (int) h.smell("Bois");
		Pheromone pheroToCollect = h.getPheromone("Bois");
		h.getPheromone(pheroToCollect.getName()).incValue(h.xcor(), h.ycor() , quantite);
		for(int i = 0; i < Configuration.terrains.size();++i)
		{
			if(Configuration.terrains.get(i).getNom() == "Plaine")
			{
				h.getPatch().setColor(Configuration.terrains.get(i).getCouleur());
			}
		}
		return nextAction;
	}

}
