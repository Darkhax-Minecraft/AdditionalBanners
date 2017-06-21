package net.darkhax.additionalbanners;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AdditionalBanners.MOD_ID, name = AdditionalBanners.MOD_NAME, version = "@VERSION@")
public class AdditionalBanners {

    public static final String MOD_ID = "additionalbanners";
    public static final String MOD_NAME = "Additional Banners";
    public static final CreativeTabs TAB_ADDITONAL_BANNERS = new CreativeTabAdditionalBanners();

    @EventHandler
    public void preInit (FMLPreInitializationEvent pre) {

        PatternHandler.initCraftingBanners();
    }
}