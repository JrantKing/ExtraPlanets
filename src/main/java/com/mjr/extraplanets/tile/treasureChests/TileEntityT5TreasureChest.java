package com.mjr.extraplanets.tile.treasureChests;

import micdoodle8.mods.galacticraft.core.tile.TileEntityTreasureChest;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityT5TreasureChest extends TileEntityTreasureChest
{
    public TileEntityT5TreasureChest()
    {
        super(5);
    }

    public TileEntityT5TreasureChest(int tier)
    {
        super(tier);
    }

    public static TileEntityT5TreasureChest findClosest(Entity entity)
    {
        double distance = Double.MAX_VALUE;
        TileEntityT5TreasureChest chest = null;
        for (final TileEntity tile : entity.worldObj.loadedTileEntityList)
        {
            if (tile instanceof TileEntityT5TreasureChest)
            {
                double dist = entity.getDistanceSq(tile.getPos().getX() + 0.5, tile.getPos().getY() + 0.5, tile.getPos().getZ() + 0.5);
                if (dist < distance)
                {
                    distance = dist;
                    chest = (TileEntityT5TreasureChest) tile;
                }
            }
        }

        return chest;
    }
}