package civilisation.inventaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import civilisation.individu.Humain;

public class NInventaire {

	HashMap<Objet, Integer> listeObjets;
	Humain h;
	int weight;

	public NInventaire(Humain h){
		listeObjets = new HashMap<Objet, Integer>();
		this.h = h;
		this.weight = 0;
	}
	
	public void addObjets(Objet o , int i){
		if (!listeObjets.containsKey(o)){
			listeObjets.put(o, i);
		}
		else{
			int nbObjets = listeObjets.get(o);
			listeObjets.remove(o);
			listeObjets.put(o, nbObjets + i);
		}
		//TODO : problem if many item??
		for(int k = 0; k < i;k++)
		{
			for(int j = 0; j < o.getEffets().size();++j)
			{
				if(o.getEffets().get(j).getActivation() == 0)
				{
					o.getEffets().get(j).Activer(h);
			//		if (listeObjets.get(o) > 1) {
			//			System.out.println(listeObjets.get(o));
			//		}
				}
			}
		}
		
		
	}
	
	public void useObjets(Objet o,int i)
	{
		if(this.listeObjets.containsKey(o))
		{
			if(this.listeObjets.get(o) >= i)
			{
				for(int j = 0; j < i;j++)
				{
					for(int k = 0; k < o.getEffets().size();++k)
					{
						if(o.getEffets().get(k).getActivation() == 1)
						{
							o.getEffets().get(k).Activer(h);
						}
					}
					
				}
			}
			else
			{
				for(int j = 0; j < this.listeObjets.get(o);j++)
				{
					for(int k = 0; k < o.getEffets().size();++k)
					{
						if(o.getEffets().get(k).getActivation() == 1)
						{
							o.getEffets().get(k).Activer(h);
						}
					}
					
				}
			}
			this.deleteObjets(o, i);
		}
	}
	
	public void deleteObjets(Objet o, int i)
	{
		int val;
		if (listeObjets.containsKey(o)){
			val = listeObjets.get(o);
			listeObjets.remove(o);
			if(val - i < 0)
			{
				for(int j = 0; j < val; ++j)
				{
					for(int k = 0; k < o.getEffets().size();++k)
					{
						if(!o.getEffets().get(k).isPermanent())
						{
							o.getEffets().get(k).Desactiver(h);
						}
					}
				}
			}
			else
			{
				for(int j = 0; j < i; ++j)
				{
					for(int k = 0; k < o.getEffets().size();++k)
					{
						if(!o.getEffets().get(k).isPermanent())
						{
							o.getEffets().get(k).Desactiver(h);
						}
					}
				}
				listeObjets.put(o, val - i);
			}
			
		}
	}
	
	public ArrayList<Objet> getAll()
	{
		ArrayList<Objet> retour = new ArrayList<Objet>();
		Set cles = listeObjets.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()){
		   Objet cle = (Objet) it.next();
		   Integer valeur = listeObjets.get(cle);
		   for(int i = 0; i < valeur;++i)
		   {
			   retour.add(cle);
		   }
		}
		return retour;
	}

	public HashMap<Objet, Integer> getListeObjets() {
		return listeObjets;
	}

	public void setListeObjets(HashMap<Objet, Integer> listeObjets) {
		this.listeObjets = listeObjets;
	}
	
	
	
}
