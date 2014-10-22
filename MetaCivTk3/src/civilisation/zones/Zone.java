package civilisation.zones;

import turtlekit.kernel.Patch;

public class Zone {

	Patch centroide;
	int width;
	int height;

	public Zone(Patch centroïde)
	{
		this.centroide = centroïde;
	}
	
	public Zone()
	{
		this.centroide = null;
	}
	
	public Patch getCentroïde()
	{
		return centroide;
	}
	
	
}
