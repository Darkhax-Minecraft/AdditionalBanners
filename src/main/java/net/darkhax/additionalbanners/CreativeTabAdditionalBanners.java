package net.darkhax.additionalbanners;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
    public ItemStack getIconItemStack () {
        
        if (DISPLAY == null)
            DISPLAY = PatternHandler.createBanner(EnumDyeColor.WHITE, PatternHandler.createPatternList(PatternHandler.TypeDesign.ADD.getLayers()));
            
        return DISPLAY;
    }
    
    @Override
    public boolean hasSearchBar () {
        
        return true;
    }
    
    @Override
    public void displayAllRelevantItems (List<ItemStack> itemList) {
        
        super.displayAllRelevantItems(itemList);
        
        if (CACHE == null) {
            
            CACHE = new ArrayList<ItemStack>();
            for (final EnumDyeColor color : EnumDyeColor.values())
                for (final PatternHandler.TypeDesign design : PatternHandler.TypeDesign.values()) {
                    
                    final ItemStack stack = PatternHandler.createBanner(color, PatternHandler.createPatternList(color, design.getLayers()));
                    stack.setStackDisplayName(ChatFormatting.RESET + "Design: " + design.name().toLowerCase());
                    CACHE.add(stack);
                }
        }
        
        itemList.addAll(CACHE);
    }
}