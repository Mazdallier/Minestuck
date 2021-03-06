package com.mraof.minestuck.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.mraof.minestuck.Minestuck;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDisk extends Item {

	private IIcon[] icons = new IIcon[2];
	private String[] subNames = { "Client", "Server" };

	public ItemDisk() {
		super();
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setCreativeTab(Minestuck.tabMinestuck);
		this.setUnlocalizedName("disk");
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister) {
		for (int i = 0; i < subNames.length; i++)
			icons[i] = par1IIconRegister.registerIcon("minestuck:"
					+ subNames[i] + "Disk");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}

	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for (int i = 0; i < subNames.length; i++)
			subItems.add(new ItemStack(this, 1, i));
	}
}
