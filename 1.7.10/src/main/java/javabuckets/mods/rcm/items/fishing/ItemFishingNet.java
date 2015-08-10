package javabuckets.mods.rcm.items.fishing;

import javabuckets.mods.rcm.items.ItemBase;
import javabuckets.mods.rcm.main.RCM;
import javabuckets.mods.rcm.skills.fishing.FishingEventHandler;
import javabuckets.mods.rcm.utility.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemFishingNet extends ItemBase
{
	public ItemFishingNet(String unlocalizedName)
	{
		super(unlocalizedName);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) 
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
		ItemStack heldItem = player.getHeldItem();
		
		if (movingobjectposition == null)
		{
			return itemstack;
		}
		else
		{
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
			{
				int x = movingobjectposition.blockX;
				int y = movingobjectposition.blockY;
				int z = movingobjectposition.blockZ;
				
				if (!world.canMineBlock(player, x, y, z))
				{
					return itemstack;
				}
				
				if (world.getBlock(x, y, z).getMaterial() == Material.water)
				{
					if (!RCM.instance.fishing.isFishing)
					{
						RCM.instance.fishing.isFishing = true;
						FishingEventHandler.didPlayerAccesClassFromFishingEquipment = true;
					}
				}
			}
			
			return itemstack;
		}
	}
}