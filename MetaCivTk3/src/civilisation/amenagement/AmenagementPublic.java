package civilisation.amenagement;

import java.awt.Graphics;

import turtlekit.kernel.Patch;

public class AmenagementPublic extends Amenagement {

	Patch position; //Le patch contenant l'am�nagement

	
	
	public AmenagementPublic(Patch p)
	{
		super(p,null);
	}
	
	@Override
	public void dessiner(Graphics g,int x,int y,int cellS)
	{
		
	}
	
	@Override
	public String getNom()
	{
		return "---Amenagement Public---";
	}


}
