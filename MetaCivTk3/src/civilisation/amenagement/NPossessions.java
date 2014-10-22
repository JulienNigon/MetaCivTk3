package civilisation.amenagement;

import java.util.HashMap;

import civilisation.individu.Humain;

public class NPossessions {

	HashMap<Amenagement, Integer> possessions;
	Humain h;
	
	public NPossessions(Humain h)
	{
		this.h = h;
		this.possessions = new HashMap<Amenagement,Integer>();
	}
	
	public void addAmenagement(Amenagement a)
	{
		
	}
}
