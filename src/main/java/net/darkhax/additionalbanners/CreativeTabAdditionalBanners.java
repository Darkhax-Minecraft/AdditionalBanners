package net.darkhax.additionalbanners;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.darkhax.additionalbanners.handler.DesignHandler;
import net.darkhax.additionalbanners.handler.PatternHandler;
import net.darkhax.additionalbanners.handler.PatternHandler.BannerLayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.NonNullList;

public class CreativeTabAdditionalBanners extends CreativeTabs {

    private static ItemStack DISPLAY = null;
    private static List<ItemStack> CACHE = null;

    public CreativeTabAdditionalBanners () {

        super("additionalbanners");
        this.setBackgroundImageName("item_search.png");
    }

    @Override
    public ItemStack getIcon () {

        return this.createIcon();
    }

    @Override
    public ItemStack createIcon () {

        if (DISPLAY == null)
            DISPLAY = PatternHandler.createBanner(EnumDyeColor.WHITE, PatternHandler.createPatternList(DesignHandler.LanguageDesign.ADD.getLayers()));

        return DISPLAY;
    }

    @Override
    public boolean hasSearchBar () {

        return true;
    }
    
    @Override
    public void displayAllRelevantItems (NonNullList<ItemStack> itemList) {

        super.displayAllRelevantItems(itemList);

        for (final BannerPattern pattern : BannerPattern.values())
            itemList.add(PatternHandler.createBanner(EnumDyeColor.WHITE, PatternHandler.createPatternList(EnumDyeColor.BLACK, new BannerLayer(pattern, EnumDyeColor.BLACK))));
        if (CACHE == null) {

            CACHE = new ArrayList<>();
            for (final EnumDyeColor color : EnumDyeColor.values())
                for (final DesignHandler.LanguageDesign design : DesignHandler.LanguageDesign.values()) {

                    final ItemStack stack = PatternHandler.createBanner(color, PatternHandler.createPatternList(color, design.getLayers()));
                    stack.setStackDisplayName(ChatFormatting.RESET + "Design: " + design.name().toLowerCase());
                    CACHE.add(stack);
                }
        }

        itemList.addAll(CACHE);
    }
}