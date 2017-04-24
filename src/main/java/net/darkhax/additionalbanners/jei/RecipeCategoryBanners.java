package net.darkhax.additionalbanners.jei;

import javax.annotation.Nullable;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.util.Translator;
import net.darkhax.additionalbanners.handler.PatternHandler;
import net.darkhax.additionalbanners.handler.PatternHandler.BannerLayer;
import net.darkhax.additionalbanners.lib.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class RecipeCategoryBanners extends BlankRecipeCategory<RecipeWrapperBanners> {
    
    private final ModelBanner bannerModel = new ModelBanner();
    public static final int recipeWidth = 160;
    public static final int recipeHeight = 125;
    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    private BannerPattern pattern;
    private EnumDyeColor color = EnumDyeColor.WHITE;
    private int updateTicker = 0;
    private IDrawable slot;
    
    public RecipeCategoryBanners (IGuiHelper guiHelper) {
        
        background = guiHelper.createBlankDrawable(recipeWidth, recipeHeight);
        ResourceLocation recipeBackgroundResource = new ResourceLocation(Constants.MOD_ID, "textures/gui/sprites.png");
        icon = guiHelper.createDrawable(recipeBackgroundResource, 240, 0, 16, 16);
        localizedName = Translator.translateToLocal("gui.additionalbanners.jei.category.banners");
        slot = guiHelper.getSlotDrawable();
    }
    
    @Override
    public void drawExtras(Minecraft minecraft) {
        
        slot.draw(minecraft, 0, 35);
        
        if (updateTicker == 60) {
            
            updateTicker = 0;
            color = next();
        }
        
        TileEntityBanner tile = new TileEntityBanner();
        tile.setItemValues(PatternHandler.createBanner(color, PatternHandler.createPatternList(EnumDyeColor.BLACK, new BannerLayer(this.pattern, EnumDyeColor.BLACK))), false);
        GlStateManager.pushMatrix();

        //GlStateManager.translate((float)0 + 0.5F, (float)0 - 0.16666667F, (float)0 + 0.5F);
        GlStateManager.translate(75, 100, 0);
        GlStateManager.rotate(180f, 0F, 0F, 1.0F);
        this.bannerModel.bannerStand.showModel = false;

        GlStateManager.enableRescaleNormal();
        ResourceLocation resourcelocation = this.getBannerResourceLocation(tile);

        if (resourcelocation != null)
        {
            minecraft.getTextureManager().bindTexture(resourcelocation);
            GlStateManager.pushMatrix();
            GlStateManager.scale(-20f, -20F, -20F);
            this.bannerModel.renderBanner();
            GlStateManager.popMatrix();
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
        
        updateTicker++;
    }
    
    @Nullable
    private ResourceLocation getBannerResourceLocation(TileEntityBanner bannerObj)
    {
        return BannerTextures.BANNER_DESIGNS.getResourceLocation(bannerObj.getPatternResourceLocation(), bannerObj.getPatternList(), bannerObj.getColorList());
    }
    
    @Override
    public String getUid () {
        
        return AdditionalBannersJEI.JEI_ID;
    }
    
    @Override
    public String getTitle () {
        
        return localizedName;
    }
    
    @Nullable
    @Override
    public IDrawable getIcon () {
        
        return icon;
    }
    
    @Override
    public IDrawable getBackground () {
        
        return background;
    }

    @Override
    public void setRecipe (IRecipeLayout recipeLayout, RecipeWrapperBanners recipeWrapper, IIngredients ingredients) {
        
        this.pattern = recipeWrapper.getPattern();
        
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

        itemStacks.init(0, true, 0, 35);
        
        itemStacks.set(ingredients);
    }
    
    public EnumDyeColor next() {
        
        return (this.color == EnumDyeColor.BLACK) ? EnumDyeColor.WHITE : EnumDyeColor.byMetadata(this.color.getMetadata() + 1);
    }
}