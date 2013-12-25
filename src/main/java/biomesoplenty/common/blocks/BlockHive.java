package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockHive extends Block
{
	private static final String[] hiveTypes = new String[] {"honeycomb", "hive", "honeycombempty", "honeycombfilled"};
	private IIcon[] textures;
	
	public BlockHive()
	{
		super(Material.wood);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
    	if (world.getBlockMetadata(x, y, z) == 2)
    	{
			EntityWasp wasp = new EntityWasp(world);
			wasp.setLocationAndAngles((double)x + 0.6, (double)y, (double)z + 0.3, 0.0F, 0.0F);
			world.spawnEntityInWorld(wasp);
    	}
    }

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[hiveTypes.length];

		for (int i = 0; i < hiveTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+hiveTypes[i]);
		}
	}
	
	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
        if (meta < 0 || meta >= hiveTypes.length) {
            meta = 0;
        }
		return textures[meta];
	}
	
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < hiveTypes.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		if (meta == 0)
		{
			return Items.miscItems.get().itemID;
		}
		
		if (meta == 3)
		{
			return Items.food.get().itemID;
		}
		
		return this.blockID;
	}
	
	@Override
	public int damageDropped(int meta)
	{
		if (meta == 0)
		{
			return 2;
		}
		if (meta == 2)
		{
			return 0;
		}
		if (meta == 3)
		{
			return 9;
		}
		
		return meta;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
		{
			return (random.nextInt(3) + 1);
		}
		if (meta == 2)
		{
			return 0;
		}
		if (meta == 3)
		{
			return random.nextInt(2);
		}
		
		return 1;
	}
}