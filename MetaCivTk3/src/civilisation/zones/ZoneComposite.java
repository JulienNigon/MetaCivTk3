package civilisation.zones;

import java.util.ArrayList;
import java.util.HashMap;

import civilisation.group.Group;

public class ZoneComposite extends Zone {

	ArrayList<ZoneElementaire> content;

	
	public ZoneComposite(ArrayList<ZoneElementaire> content)
	{
		this.content = content;
	}
	
	public ZoneComposite(ZoneElementaire content)
	{
		this.content = new ArrayList<ZoneElementaire>();
		this.content.add(content);
	}
	
	public void addZone(ZoneElementaire zone)
	{
		this.content.add(zone);
	}
}
