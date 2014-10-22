package civilisation.zones;

import java.util.ArrayList;

import civilisation.world.EvoluPatch;
import civilisation.group.Group;
import civilisation.world.World;

public class Zonage {

	ArrayList<ZoneComposite> zon;
	Group groupe;
	String nom;
	
	public Zonage()
	{
		this.zon = new ArrayList<ZoneComposite>();
	}
	
	public Zonage(ArrayList<ZoneComposite> zon)
	{
		this.zon = new ArrayList<ZoneComposite>();
		this.zon = zon;
	}
	
	public Zonage(ZoneComposite zon)
	{
		this.zon = new ArrayList<ZoneComposite>();
		this.zon.add(zon);
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void addZone(ZoneComposite zone)
	{
		for(int i = 0; i < zon.size();++i)
		{
			int touche = this.touch(zon.get(i),zone); 
			if(touche > 0)
			{
				this.mix(zon.get(i),zone,touche);
			}
			else
			{
				this.zon.add(zone);
			}
		}

	}
	
	public void addZone(ZoneElementaire zone)
	{
		for(int i = 0; i < zon.size();++i)
		{
			ZoneComposite temp = new ZoneComposite(zone);
			int touche = this.touch(zon.get(i),temp); 
			if(touche > 0)
			{
				this.mix(zon.get(i),temp,touche);
			}
			else
			{
				this.zon.add(temp);
			}
		}

	}
	
	public Zone getAZone()
	{
		int rand = (int) (Math.random()*(this.zon.size() - 1));
		
		return this.zon.get(rand);
	}
	
	private void mix(ZoneComposite a, ZoneComposite b,int touche) {
		ZoneElementaire temp = new ZoneElementaire();
		switch (touche)
		{
			case 1: //ca touche en haut a droite
				for(int i = b.centroide.x; i < b.centroide.x + b.width;++i)
				{
					for(int j = b.centroide.y; j < b.centroide.y + b.height;++j)
					{
						if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
						{

							if(World.getInstance().getPatchAt(i, j).getClass().equals(civilisation.world.EvoluPatch.class) && !((EvoluPatch) World.getInstance().getPatchAt(i, j)).IsinZone(b) && ((EvoluPatch) World.getInstance().getPatchAt(i, j)).IsinZone(a))
							{
								temp.addPatch(World.getInstance().getPatchAt(i, j));
							}
						}
					}
				}
				a.addZone(temp);
				break;
			case 2: //ca touche en haut a gauche
				
				for(int i = b.centroide.x - b.width; i < b.centroide.x;++i)
				{
					for(int j = b.centroide.y; j < b.centroide.y + b.height;++j)
					{
						if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
						{
							if(!((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(b) && ((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(a))
							{
								temp.addPatch(World.getInstance().getPatchAt(i, j));
							}
						}
					}
				}
				a.addZone(temp);
				break;
			case 3: //ca touche en bas a droite
				for(int i = b.centroide.x - b.width; i < b.centroide.x;++i)
				{
					for(int j = b.centroide.y - b.height; j < b.centroide.y;++j)
					{
						if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
						{
							if(!((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(b) && ((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(a))
							{
								temp.addPatch(World.getInstance().getPatchAt(i, j));
							}
						}
					}
				}
				a.addZone(temp);
				break;
			case 4: //ca touche en bas a gauche
				for(int i = b.centroide.x - b.width; i < b.centroide.x;++i)
				{
					for(int j = b.centroide.y - b.height; j < b.centroide.y;++j)
					{
						if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
						{
							if(!((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(b) && ((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(a))
							{
								temp.addPatch(World.getInstance().getPatchAt(i, j));
							}
						}
					}
				}
				a.addZone(temp);
				break;
			default: // y a un probleme
				break;
		}
	}

	public int touch(ZoneComposite a, ZoneComposite b)
	{
		boolean droite = a.centroide.x + a.width > b.centroide.x - b.width &&  a.centroide.x - a.width < b.centroide.x - b.width;
		boolean gauche = a.centroide.x + a.width > b.centroide.x + b.width &&  a.centroide.x - a.width < b.centroide.x + b.width;
		boolean haut = a.centroide.y + a.height > b.centroide.y - b.height &&  a.centroide.y - a.height < b.centroide.y - b.height;
		boolean bas = a.centroide.y + a.height > b.centroide.y + b.height &&  a.centroide.y - a.height < b.centroide.y + b.height;
		
		
		if(droite && haut)
		{
			for(int i = a.centroide.x; i < a.centroide.x + a.width;++i)
			{
				for(int j = a.centroide.y; j < a.centroide.y + a.height;++j)
				{
					if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
					{
						if(((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(b) && ((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(a))
						{
							return 1;
						}
					}
				}
			}
		}
		if(gauche && haut)
		{
			for(int i = a.centroide.x - a.width; i < a.centroide.x ;++i)
			{
				for(int j = a.centroide.y; j < a.centroide.y + a.height;++j)
				{
					if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
					{
						if(((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(b)&& ((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(a))
						{
							return 2;
						}
					}
				}
			}
		}
		if(droite && bas)
		{
			for(int i = a.centroide.x; i < a.centroide.x + a.width;++i)
			{
				for(int j = a.centroide.y - a.height; j < a.centroide.y;++j)
				{
					if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
					{
						if(((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(b) && ((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(a))
						{
							return 3;
						}
					}
				}
			}
		}
		if(gauche && bas)
		{
			for(int i = a.centroide.x - a.width; i < a.centroide.x ;++i)
			{
				for(int j = a.centroide.y - a.height; j < a.centroide.y;++j)
				{
					if(i > 0 && i < World.getInstance().getWidth() && j > 0 && j < World.getInstance().getHeight())
					{
						if(((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(b) && ((EvoluPatch)World.getInstance().getPatchAt(i, j)).IsinZone(a))
						{
							return 4;
						}
					}
				}
			}
		}
		return 0;
	}
}
