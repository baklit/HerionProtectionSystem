package HerionProtectionSystem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventBlockInteractContainerClass {
	
	@ForgeSubscribe
	public void blockInteracted(PlayerInteractEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		PlayerInteractEvent.Action action = event.action;
		Event.Result useBlock = event.useBlock;
		Event.Result useItem = event.useItem;
		int x = event.x;
		int y = event.y;
		int z = event.z;
		int face = event.face;
		
		//TileEntity blockInteracted = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(player.getEntityName()).worldObj.getBlockTileEntity(x, y, z);
		
		//MinecraftServer.getServer().getConfigurationManager().func_92027_k(blockInteracted.toString());
		
		if (event.isCancelable())
		{
			event.setCanceled(true);
		}
		
		
	}

}


