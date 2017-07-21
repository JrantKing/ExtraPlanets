package com.mjr.extraplanets.jei.crystallizer;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCLog;

import com.mjr.extraplanets.jei.RecipeCategories;

public class CrystallizerRecipeHandler implements IRecipeHandler<CrystallizerRecipeWrapper>
{
    @Nonnull
    @Override
    public Class<CrystallizerRecipeWrapper> getRecipeClass()
    {
        return CrystallizerRecipeWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return RecipeCategories.CRYSTALLIZER_ID;
    }

    @Override
    public String getRecipeCategoryUid(CrystallizerRecipeWrapper recipe)
    {
        return this.getRecipeCategoryUid();
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull CrystallizerRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull CrystallizerRecipeWrapper recipe)
    {
        if (recipe.getInputs().size() != 2)
        {
            GCLog.severe(this.getClass().getSimpleName() + " JEI recipe has wrong number of inputs!");
        }
        if (recipe.getOutputs().size() != 1)
        {
            GCLog.severe(this.getClass().getSimpleName() + " JEI recipe has wrong number of outputs!");
        }
        return true;
    }
}
