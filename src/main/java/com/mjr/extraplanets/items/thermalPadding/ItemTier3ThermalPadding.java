package com.mjr.extraplanets.items.thermalPadding;

import java.util.List;

import micdoodle8.mods.galacticraft.api.item.IItemThermal;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.ExtraPlanets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTier3ThermalPadding extends Item implements IItemThermal {
	public static String[] names = { "tier3ThermalHelm", "tier3ThermalChestplate", "tier3ThermalLeggings", "tier3ThermalBoots", "tier3ThermalHelm0", "tier3ThermalChestplate0", "tier3ThermalLeggings0", "tier3ThermalBoots0" };
	protected IIcon[] icons = new IIcon[ItemTier3ThermalPadding.names.length];

	public ItemTier3ThermalPadding(String assetName) {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(assetName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
		if (pass == 1) {
			if (this.icons.length > damage + 4) {
				return this.icons[damage + 4];
			}
		}

		return this.getIconFromDamage(damage);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public CreativeTabs getCreativeTab() {
		return ExtraPlanets.ItemsTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		int i = 0;

		for (String name : ItemTier3ThermalPadding.names) {
			this.icons[i++] = iconRegister.registerIcon(Constants.TEXTURE_PREFIX + name);
		}
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		if (this.icons.length > damage) {
			return this.icons[damage];
		}

		return super.getIconFromDamage(damage);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < ItemTier3ThermalPadding.names.length / 2; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		if (this.icons.length > par1ItemStack.getItemDamage()) {
			return "item." + ItemTier3ThermalPadding.names[par1ItemStack.getItemDamage()];
		}

		return "unnamed";
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public int getThermalStrength() {
		return 100;
	}

	@Override
	public boolean isValidForSlot(ItemStack stack, int armorSlot) {
		return stack.getItemDamage() == armorSlot;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (player.worldObj.isRemote) {
			list.add(EnumChatFormatting.AQUA + GCCoreUtil.translate("tier3.information"));
		}
	}
}
