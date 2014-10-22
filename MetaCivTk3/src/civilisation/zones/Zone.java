package civilisation.zones;

import turtlekit.kernel.Patch;

public class Zone {

	Patch centroide;
	int width;
	int height;

	public Zone(Patch centroide)
	{
		this.centroide = centroide;
	}
	
	public Zone()
	{
		this.centroide = null;
	}
	
	public Patch getCentroide()
	{
		return centroide;
	}
	
	
}
