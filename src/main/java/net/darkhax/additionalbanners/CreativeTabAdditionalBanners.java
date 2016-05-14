package net.darkhax.additionalbanners;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.darkhax.additionalbanners.lib.BannerUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabAdditionalBanners extends CreativeTabs {
    
    private static ItemStack DISPLAY = null;
    private static List<ItemStack> CACHE = null;
    
    public CreativeTabAdditionalBanners() {
        
        super("additionalbanners");
        this.setBackgroundImageName("item_search.png");
    }
    
    @Override
    public Item getTabIconItem () {
        
        return Items.BANNER;
    }
    
    @Override
    public boolean hasSearchBar () {
        
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems (List<ItemStack> itemList) {
        
        super.displayAllRelevantItems(itemList);
        
        if (CACHE == null)
            for (final EnumDyeColor color : EnumDyeColor.values())
                for (final BannerUtils.TypeDesign design : BannerUtils.TypeDesign.values()) {
                    
                    System.out.println(color.name() + " - " + design.name());
                    final ItemStack stack = BannerUtils.createBanner(color, BannerUtils.createPatternList(color, design.getLayers()));
                    stack.setStackDisplayName(ChatFormatting.RESET + "Design: " + design.name().toLowerCase());
                    CACHE.add(stack);
                }
                
        itemList.addAll(CACHE);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack () {
        
        if (DISPLAY == null)
            DISPLAY = BannerUtils.createBanner(EnumDyeColor.WHITE, BannerUtils.createPatternList(BannerUtils.TypeDesign.ADD.getLayers()));
            
        return DISPLAY;
    }
}