package civilisation.individu.plan.action;

import java.util.ArrayList;
import java.util.List;

import turtlekit.kernel.Patch;
import turtlekit.kernel.Turtle;
import civilisation.Configuration;
import civilisation.amenagement.Amenagement_Champ;
import civilisation.individu.Humain;

public class A_DoAgriculture extends Action{

	Patch cible = null;
	@Override
	public Action effectuer(Humain h) {
		System.out.println("Test");
		if (!h.getBuildings().containsKey(Amenagement_Champ.class.getName())) {
			if(cible != null )
			{
				if(h.getPatch().x != cible.x || h.getPatch().y != cible.y)
				{
					h.towards(cible);
					h.fd(1);
					return this;
				}
				else
				{
					if(h.getPatch().isMarkPresent(Amenagement_Champ.class.getName()))
					{
						int libre = 0;
						ArrayList<Patch> libres = new ArrayList<Patch>();
						ArrayList<Patch> nonlibres = new ArrayList<Patch>();
						ArrayList<Patch> voisins = (ArrayList<Patch>) h.getPatch().getNeighbors(1, true);
						for(int j = 0; j < voisins.size();++j)
						{
							if(!voisins.get(j).isMarkPresent(Amenagement_Champ.class.getName()))
							{
								libre++;
								libres.add(voisins.get(j));
							}
							else
							{
								nonlibres.get(j);
							}
						}
						if(libre > 1)
						{
							int rand = (int) (Math.random() * libres.size());
							cible = libres.get(rand);
						}
						else
						{
							int rand = (int) (Math.random() * nonlibres.size());
							cible = nonlibres.get(rand);
						}
						return this;
					}
					else
					{
						Amenagement_Champ a = new Amenagement_Champ(h.getPatch(),h);
						h.getCommunaute().construire(a);
						h.getBuildings().put(Amenagement_Champ.class.getName(), a);
						h.getPatch().dropMark(Amenagement_Champ.class.getName(), a);
						return nextAction;
					}
				}
				
			}
			else
			{
				if(cible == null && h.getCommunaute().getBatiments().containsKey(Amenagement_Champ.class.getName()))
				{
					int rand = (int) (Math.random() * h.getCommunaute().getBatiments().get(Amenagement_Champ.class.getName()).size());
					cible = h.getCommunaute().getBatiments().get(Amenagement_Champ.class.getName()).get(rand).getPosition();
					return this;
				}
				else
				{
					h.setHeading(Math.random()*360.);
					h.fd(1);
					if(!h.getPatch().isMarkPresent(Amenagement_Champ.class.getName()))
					{
						Amenagement_Champ a = new Amenagement_Champ(h.getPatch(),h);
						h.getCommunaute().construire(a);
						h.getBuildings().put(Amenagement_Champ.class.getName(), a);
						h.getPatch().dropMark(Amenagement_Champ.class.getName(), a);
					}
					return this;
				}
			}
		} else {
			Patch p = h.getBuildings().get(Amenagement_Champ.class.getName()).getPosition();
			h.face(p);
			h.fd(1); //TODO : mettre le pathfinder
		//	h.allerVers(p);
			if (h.getPatch().x == p.x && h.getPatch().y == p.y) {	
				ArrayList<Patch> voisins = new ArrayList<Patch>();
				List<Patch> pa = h.getPatch().getNeighbors(1, true);
				for(int k = 0; k < pa.size();++k)
				{
					voisins.add(pa.get(k));
				}
				int libre = 0;
				for(int j = 0; j < voisins.size();++j)
				{
					if(!voisins.get(j).isMarkPresent(Amenagement_Champ.class.getName()))
					{
						libre++;
					}
				}
				h.getInventaire().addObjets(Configuration.getObjetByName("Cereale"), 1+libre);
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
