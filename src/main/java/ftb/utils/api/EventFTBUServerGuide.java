package ftb.utils.api;

import ftb.lib.api.ForgePlayerMP;
import ftb.utils.api.guide.ServerGuideFile;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventFTBUServerGuide extends Event
{
	public final ServerGuideFile file;
	public final ForgePlayerMP player;
	public final boolean isOP;
	
	public EventFTBUServerGuide(ServerGuideFile f, ForgePlayerMP p, boolean o)
	{
		file = f;
		player = p;
		isOP = o;
	}
}