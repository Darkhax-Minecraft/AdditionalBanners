package net.darkhax.additionalbanners;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.NonNullList;

public class CreativeTabAdditionalBanners extends ItemGroup {

    private final ItemStack display = null;

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

        return this.display;
    }

    @Override
    public boolean hasSearchBar () {

        return true;
    }

    @Override
    public void fill (NonNullList<ItemStack> itemList) {

        super.fill(itemList);

        for (final BannerPattern pattern : PatternHandler.getModdedPatterns()) {

            itemList.add(PatternHandler.createBanner(Items.WHITE_BANNER, pattern));
        }
    }
}