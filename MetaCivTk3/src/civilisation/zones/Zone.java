package civilisation.zones;

import turtlekit.kernel.Patch;

public class Zone {

	Patch centro�de;
	int width;
	int height;

	public Zone(Patch centro�de)
	{
		this.centro�de = centro�de;
	}
	
	public Zone()
	{
		this.centro�de = null;
	}
	
	public Patch getCentro�de()
	{
		return centro�de;
	}
	
	
}
