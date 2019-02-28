package net.darkhax.additionalbanners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(value = AdditionalBanners.MOD_ID)
@EventBusSubscriber(modid = AdditionalBanners.MOD_ID, bus = Bus.MOD)
public class AdditionalBanners {

    public static final String MOD_ID = "additionalbanners";
    public static final ItemGroup TAB_ADDITONAL_BANNERS = new CreativeTabAdditionalBanners();
    public static final Logger Log = LogManager.getLogger(MOD_ID);
    
    @SubscribeEvent
    public static void onLoadComplete (FMLLoadCompleteEvent event) {

        PatternHandler.initCraftingBanners();
    }
}