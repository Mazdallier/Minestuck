package com.mraof.minestuck.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mraof.minestuck.client.gui.playerStats.GuiGristCache;
import com.mraof.minestuck.client.gui.playerStats.GuiPlayerStats;
import com.mraof.minestuck.editmode.ClientEditHandler;
import com.mraof.minestuck.editmode.ServerEditHandler;
import com.mraof.minestuck.inventory.ContainerHandler;
import com.mraof.minestuck.inventory.ContainerMachine;
import com.mraof.minestuck.tileentity.TileEntityComputer;
import com.mraof.minestuck.tileentity.TileEntityMachine;
import com.mraof.minestuck.tileentity.TileEntityTransportalizer;
import com.mraof.minestuck.util.Debug;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler 
{
	public static enum GuiId
	{
		MACHINE,
		COMPUTER,
		TRANSPORTALIZER,
//		PLAYERSTATS	//Keep this last
	}
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityMachine && id == GuiId.MACHINE.ordinal())
			return new ContainerMachine(player.inventory, (TileEntityMachine) tileEntity);
		
//		if(id >= GuiId.PLAYERSTATS.ordinal())
//		{
//			Debug.print("Aquiring server gui element.");
//			return ContainerHandler.getPlayerStatsContainer(player, id - GuiId.PLAYERSTATS.ordinal(), ServerEditHandler.getData(player.getCommandSenderName()) != null);
//		}
		
		return null;
	}

	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityMachine && id == GuiId.MACHINE.ordinal())
			return new GuiMachine(player.inventory, (TileEntityMachine) tileEntity);
		
		if(tileEntity instanceof TileEntityComputer && id == GuiId.COMPUTER.ordinal())
			return new GuiComputer(Minecraft.getMinecraft(),(TileEntityComputer) tileEntity);
		
		if(id == GuiId.TRANSPORTALIZER.ordinal() && tileEntity instanceof TileEntityTransportalizer)
			return new GuiTransportalizer(Minecraft.getMinecraft(), (TileEntityTransportalizer) tileEntity);
		
//		if(id >= GuiId.PLAYERSTATS.ordinal())
//		{
//			Debug.print("Aquiring client gui element.");
//			if(ClientEditHandler.isActive())
//				GuiPlayerStats.editmodeTab = id - GuiId.PLAYERSTATS.ordinal();
//			else GuiPlayerStats.normalTab = id - GuiId.PLAYERSTATS.ordinal();
//			return GuiPlayerStats.createGuiInstance();
//		}
			
		return null;

	}

}
