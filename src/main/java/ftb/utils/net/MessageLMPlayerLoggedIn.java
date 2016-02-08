package ftb.utils.net;

import com.mojang.authlib.GameProfile;
import ftb.lib.api.net.*;
import ftb.utils.api.EventLMPlayerClient;
import ftb.utils.world.*;
import latmod.lib.ByteCount;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.*;

import java.util.UUID;

public class MessageLMPlayerLoggedIn extends MessageLM_IO
{
	public MessageLMPlayerLoggedIn() { super(ByteCount.INT); }
	
	public MessageLMPlayerLoggedIn(LMPlayerServer p, boolean first, boolean self)
	{
		this();
		
		io.writeInt(p.getPlayerID());
		io.writeUUID(p.getProfile().getId());
		io.writeUTF(p.getProfile().getName());
		io.writeBoolean(first);
		p.writeToNet(io, self);
	}
	
	public LMNetworkWrapper getWrapper()
	{ return FTBUNetHandler.NET; }
	
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(MessageContext ctx)
	{
		if(LMWorldClient.inst == null) return null;
		
		int playerID = io.readInt();
		UUID uuid = io.readUUID();
		String username = io.readUTF();
		boolean firstTime = io.readBoolean();
		
		LMPlayerClient p = LMWorldClient.inst.getPlayer(playerID);
		boolean add = p == null;
		if(add) p = new LMPlayerClient(LMWorldClient.inst, playerID, new GameProfile(uuid, username));
		p.readFromNet(io, p.getPlayerID() == LMWorldClient.inst.clientPlayerID);
		LMWorldClient.inst.playerMap.put(p.getPlayerID(), p);
		new EventLMPlayerClient.LoggedIn(p, firstTime).post();
		new EventLMPlayerClient.DataLoaded(p).post();
		return null;
	}
}