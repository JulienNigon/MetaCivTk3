package civilisation.zones;

import turtlekit.kernel.Patch;

public class Zone {

	Patch centroïde;
	int width;
	int height;

	public Zone(Patch centroïde)
	{
		this.centroïde = centroïde;
	}
	
	public Zone()
	{
		this.centroïde = null;
	}
	
	public Patch getCentroïde()
	{
		return centroïde;
	}
	
	
}
