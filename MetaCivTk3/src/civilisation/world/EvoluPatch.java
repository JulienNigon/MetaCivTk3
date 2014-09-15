package civilisation.world;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import civilisation.Configuration;
import civilisation.zones.Zone;
import civilisation.zones.ZoneComposite;
import turtlekit.kernel.Patch;

public class EvoluPatch extends Patch {
	
	double evo;
	double limite;
	Terrain suivant;
	ArrayList<ArrayList<Integer>> equations;
	int[] maxEquations; //permet de savoir a quelles equations correspondent a quel terrain suivant
	Patch patch;
	Terrain t;
	ArrayList<ZoneComposite> zonesOuIlEst;
	
	public EvoluPatch(int i, int j, Patch patch) {
		super(i, j);
		// TODO Auto-generated constructor stub
		this.patch = patch;
		t = Configuration.couleurs_terrains.get(patch.getColor());
		
	}

	
	public void calculEquations(ArrayList<Patch> neighbors)
	{
		equations = new ArrayList<ArrayList<Integer>>();
		maxEquations = new int[Configuration.terrains.size()];
		HashMap<Terrain,Integer> voisins = new HashMap<Terrain,Integer>();
		for(int i = 0; i < neighbors.size();++i)
		{
			Terrain crt = Configuration.couleurs_terrains.get(neighbors.get(i).getColor());
			int val = 1;
			if(voisins.containsKey(crt))
			{
				val += voisins.get(crt);
				voisins.remove(crt);
			}
			voisins.put(crt, val);
		}
		this.recursCalcul(0,t,0,voisins);
		this.limite = this.calculLimite();
	}

	private double calculLimite() {
		// TODO Auto-generated method stub
		int min = 0;
		int max = 0;
		for(int i = 0; i < this.equations.size();++i)
		{
			int courant = 0;
			for(int j = 0; j < this.equations.get(i).size();++j)
			{
//				System.out.println(this.equations.get(i));
				courant += this.equations.get(i).get(j) * j;
			}
	//		System.out.println("courant : "+courant);
			if(max < courant)
			{
				max = courant;
				min = i;
			}
		}
		
		int lim = 0;
////		System.out.println("taille equation : " + this.equations.size() + " min : "+min);
		if(this.equations.size() > 0)
		{
			for(int j = 0; j < this.equations.get(min).size();++j)
			{
				lim += this.equations.get(min).get(j) * j;
			}
		}
		
		this.suivant = this.t.suivants[min];
		if(lim == 0)
		{
			return Integer.MAX_VALUE;
		}
//		System.out.println(lim);
		return 4000/lim;
	}


	private int recursCalcul(int min,Terrain ter,int plus,HashMap<Terrain,Integer> voisins) {
		// TODO Auto-generated method stub
		if(ter.suivants.length == 0 || ter.poidSuivant[0] == 0)
		{
					this.equations.add(new ArrayList<Integer>());
					if(!ter.getNom().equals("Mer"))
					{
			//			System.out.println("plus : "+plus + "ter :"+ter.getNom());
					}
					
					if(plus > this.equations.get(equations.size() - 1).size())
					{
						for(int i = this.equations.get(equations.size() - 1).size(); i <= plus ; ++i)
						{
							this.equations.get(equations.size() - 1).add(0);
						}
					}
					if(voisins.get(ter) == null)
					{
						this.equations.get(equations.size() - 1).add(plus,0);
					}
					else
					{
						this.equations.get(equations.size() - 1).add(plus, voisins.get(ter));
					}
			return equations.size();
		}
		else
		{
			int mini = min;
			int temp = 0;
			for(int i = 0; i < ter.suivants.length;++i)
			{
				int vari =plus + ter.poidSuivant[i]; 
				temp = this.recursCalcul(mini,ter.suivants[i], vari, voisins);
			//	System.out.println("new");
				for(int j = mini; j < temp;++j)
				{
					
					if(plus > this.equations.get(j).size())
					{
						for(int k = this.equations.get(j).size(); k <= plus ; ++k)
						{
							this.equations.get(j).add(0);
						}
					}
					if(voisins.get(ter) == null)
					{
						this.equations.get(equations.size() - 1).add(plus,0);
					}
					else
					{
						this.equations.get(equations.size() - 1).add(plus, voisins.get(ter));
					}


				}
				mini = temp;
					
			}
			return mini;
		}
	}


	public double getLimite()
	{
		return this.limite;
	}


	public void maj() {
		this.t = this.suivant;
	}


	public boolean IsinZone(ZoneComposite b) {
		// TODO Auto-generated method stub
		for(int i = 0; i < zonesOuIlEst.size();++i)
		{
			if(zonesOuIlEst.get(i).equals(b))
			{
				return true;
			}
		}
		return false;
	}
}
 