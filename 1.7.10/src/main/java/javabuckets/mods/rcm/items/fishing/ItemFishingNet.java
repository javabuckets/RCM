package javabuckets.mods.rcm.items.fishing;

import javabuckets.mods.rcm.items.ItemBase;
import javabuckets.mods.rcm.main.RCM;
import javabuckets.mods.rcm.skills.fishing.FishingEventHandler;
import javabuckets.mods.rcm.utility.Reference;
import javabuckets.mods.rcm.utility.SkillReference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int i, float f1, float f2, float f3) 
	{
		//world.setBlock(x, y+1, z, Blocks.wheat, 7, 2);
		
		return true;
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
//						FishingEventHandler.didPlayerAccesClassFromFishingEquipment = true;
						
						//fish(heldItem, player);
					}
				}
			}
			
			return itemstack;
		}
	}
	
	/*public void fish(ItemStack net, EntityPlayer player)
	{
		int fishLvl = RCM.instance.skillHandler.getLevel(SkillReference.fish);
		int 
		
	}*/
	
	public int getAvailableFish(int fishingLvl)
	{
		if (fishingLvl < 10) { return 1; }
		else if (fishingLvl >= 10 && fishingLvl < 15) { return 2; }
		return 0;
	}
}