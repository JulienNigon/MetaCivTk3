package civilisation.amenagement;

import java.awt.Graphics;

import edu.turtlekit2.kernel.environment.Patch;

public class AmenagementPublic extends Amenagement {

	Patch position; //Le patch contenant l'aménagement

	
	
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
