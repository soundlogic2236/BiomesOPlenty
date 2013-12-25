package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockBamboo extends Block
{
	private IIcon bambooSide;
	private IIcon bambooTop;
	
	public BlockBamboo()
	{
		super(Material.plants);
		
		this.setBurnProperties(this, 5, 5);
		this.setTickRandomly(true);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		bambooSide = iconRegister.registerIcon("biomesoplenty:bamboo");
		bambooTop = iconRegister.registerIcon("biomesoplenty:bambootop");
	}
	
	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (side > 1)
			return bambooSide;
		else
			return bambooTop;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.isAirBlock(x, y + 1, z))
		{
			int var6;

			for (var6 = 1; world.getBlockId(x, y - var6, z) == blockID; ++var6)
			{
				;
			}

			if (var6 < 3)
			{
				int var7 = world.getBlockMetadata(x, y, z);

				if (var7 == 15)
				{
					world.setBlock(x, y + 1, z, blockID);
					world.setBlockMetadataWithNotify(x, y, z, 0, 2);
				}
				else
				{
					world.setBlockMetadataWithNotify(x, y, z, var7 + 1, 2);
				}
			}
		}
	}
	
	@Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
		float pixel = 0.0625F;
		
        return AxisAlignedBB.getAABBPool().getAABB((double)x + (1.0F - (pixel * 4)), (double)y, (double)z + (1.0F - (pixel * 4)), (double)x + (pixel * 4), (double)y + 1.0F, (double)z + (pixel * 4));
    }
	
    @Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
		float pixel = 0.0625F;
        this.setBlockBounds((pixel * 4), 0.0F, (pixel * 4), 1.0F - (pixel * 4), 1.0F, 1.0F - (pixel * 4));
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
        this.setBlockBoundsForItemRender();
    }
    
    @Override
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

	@Override
	public boolean canBeReplacedByLeaves(World world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockId(par2, par3 - 1, par4);
		if (var5 == blockID)
			return true;
		else if (var5 == Block.grass.blockID)
			return true;
		else
			return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		this.checkBlockCoordValid(par1World, par2, par3, par4);
	}

	protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4)
	{
		if (!this.canBlockStay(par1World, par2, par3, par4))
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlock(par2, par3, par4, 0);
		}
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		return this.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RenderUtils.bambooModel;
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

}
