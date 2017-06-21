package net.darkhax.additionalbanners.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;

public  class RecipeWrapperBanners  extends BlankRecipeWrapper {
    
    private BannerPattern pattern;
    
    public RecipeWrapperBanners(BannerPattern pattern) {
        
        System.out.println("Added banner pattern: " + pattern.getFileName());
        this.pattern = pattern;
    }
    
    public BannerPattern getPattern() {
        
        return this.pattern;
    }

    @Override
    public void getIngredients (IIngredients ingredients) {
        
        if (this.pattern.hasPatternItem()) {
            
            ingredients.setInput(ItemStack.class, this.pattern.getPatternItem());
        }
    }
}