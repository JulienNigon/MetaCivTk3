package civilisation.zones;

import turtlekit.kernel.Patch;

public class Zone {

	Patch centroide;
	int width;
	int height;

	public Zone(Patch centro�de)
	{
		this.centroide = centro�de;
	}
	
	public Zone()
	{
		this.centroide = null;
	}
	
	public Patch getCentro�de()
	{
		return centroide;
	}
	
	
}
