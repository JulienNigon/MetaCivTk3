package civilisation.zones;

import java.util.ArrayList;
import java.util.HashMap;

import civilisation.group.Group;
import civilisation.individu.Humain;

public class Permissions {

	HashMap<String,ArrayList<Droits>> droits;
	Group groupe;
	public Permissions(Group groupe)
	{
		this.droits = new HashMap<String, ArrayList<Droits>>();
		this.groupe = groupe;
	}
	
	public void addDroit(String zone,Droits droit)
	{
		if(droits.containsKey(zone))
		{
			ArrayList<Droits> temp = droits.get(zone);
			int i = 0;
			while(i < temp.size() && !temp.get(i).getName().equals(droit.getName()))
			{
				++i;
			}
			if(i < temp.size())
			{
				temp.remove(i);
			}
			temp.add(droit);
			droits.remove(zone);
			droits.put(zone, temp);
		}
		else
		{
			ArrayList<Droits> temp = new ArrayList<Droits>();
			temp.add(droit);
			droits.put(zone, temp);
		}
	}
	
	public void SetDroits(Humain h,String role)
	{
		for(int i = 0; i < this.droits.get(role).size();++i)
		{
			this.droits.get(role).get(i).activer(h);
		}
	}
}
