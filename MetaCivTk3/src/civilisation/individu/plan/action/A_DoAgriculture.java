package civilisation.individu.plan.action;

import turtlekit.kernel.Patch;
import turtlekit.kernel.Turtle;
import civilisation.amenagement.Amenagement_Champ;
import civilisation.individu.Humain;

public class A_DoAgriculture extends Action{

	@Override
	public Action effectuer(Humain h) {
		if (!h.getBuildings().containsKey(Amenagement_Champ.class.getName())) {
			h.setHeading(Math.random()*360.);
			h.fd(1);
			if (h.getPatch().isMarkPresent(Amenagement_Champ.class.getName())) {
				return this;
			} else {
				Amenagement_Champ champ = new Amenagement_Champ(h.getPatch(), h);
				h.getBuildings().put(Amenagement_Champ.class.getName(), champ);
				h.getPatch().dropMark(Amenagement_Champ.class.getName(), champ);
				h.putAttr("StockDeCereale", h.getAttr().get("StockDeCereale") + 10 );
				return nextAction;
			}
		} else {
			Patch p = h.getBuildings().get(Amenagement_Champ.class.getName()).getPosition();
			h.face(p);
			h.fd(1); //TODO : mettre le pathfinder
			h.allerVers(p);
			if (h.getPatch().x == p.x && h.getPatch().y == p.y) {		
				h.putAttr("StockDeCereale", h.getAttr().get("StockDeCereale") + 10 );
				return nextAction;
			} else {
				return this;
			}
		}

		
	}

	
	@Override
	public String getInfo() {
		return super.getInfo() + "Create a new agent.<html>";
	}

	
	
}
