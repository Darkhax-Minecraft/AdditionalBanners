package net.darkhax.additionalbanners;

import java.util.List;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.NonNullList;

public class CreativeTabAdditionalBanners extends ItemGroup {

    private ItemStack display = null;
    private List<ItemStack> cache = null;

    public CreativeTabAdditionalBanners () {

        super("additionalbanners");
        this.setBackgroundImageName("item_search.png");
    }

    @Override
    public ItemStack getIcon () {

        return new ItemStack(Items.STICK);
    }

    @Override
    public ItemStack createIcon () {

        return display;
    }

    @Override
    public boolean hasSearchBar () {

        return true;
    }
    
    @Override
    public void fill (NonNullList<ItemStack> itemList) {

        super.fill(itemList);
        
        ItemBanner itemBanner = null;
        

        for (final BannerPattern pattern : BannerPattern.values()) {
        	
            itemList.add(PatternHandler.createBanner(Items.WHITE_BANNER, pattern));
        }
//        
//        if (cache == null) {
//
//            cache = new ArrayList<>();
//            for (final EnumDyeColor color : EnumDyeColor.values())
//                for (final DesignHandler.LanguageDesign design : DesignHandler.LanguageDesign.values()) {
//
//                    final ItemStack stack = PatternHandler.createBanner(color, PatternHandler.createPatternList(color, design.getLayers()));
//                    stack.setDisplayName(new TextComponentString("Design: " + design.name().toLowerCase()));
//                    cache.add(stack);
//                }
//        }
//
//        itemList.addAll(cache);
    }
}