package net.darkhax.additionalbanners.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.darkhax.additionalbanners.lib.Constants;
import net.minecraft.tileentity.BannerPattern;

@JEIPlugin
public class AdditionalBannersJEI extends BlankModPlugin {
    
    public static final String JEI_ID = Constants.MOD_ID + ".banner";
    
    @Override
    public void register (IModRegistry registry) {
        
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        
        registry.addRecipeCategories(new RecipeCategoryBanners(guiHelper));
        registry.addRecipeHandlers(new RecipeHandlerBanners());
        
        List<RecipeWrapperBanners> recipes = new ArrayList<RecipeWrapperBanners>();
        
        for (BannerPattern pattern : BannerPattern.values()) {
            
            final RecipeWrapperBanners recipe = new RecipeWrapperBanners(pattern);
            recipes.add(recipe);
        }
        
        registry.addRecipes(recipes);
    }
}
