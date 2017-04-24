package net.darkhax.additionalbanners.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class RecipeHandlerBanners implements IRecipeHandler<RecipeWrapperBanners>{

    @Override
    public Class<RecipeWrapperBanners> getRecipeClass () {
        
        return RecipeWrapperBanners.class;
    }

    @Override
    public String getRecipeCategoryUid (RecipeWrapperBanners recipe) {
        
        return AdditionalBannersJEI.JEI_ID;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper (RecipeWrapperBanners recipe) {
        
        return recipe;
    }

    @Override
    public boolean isRecipeValid (RecipeWrapperBanners recipe) {
        
        return recipe.getPattern() != null;
    }
}