package net.darkhax.additionalbanners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = AdditionalBanners.MOD_ID)
public class AdditionalBanners {

    public static final String MOD_ID = "additionalbanners";
    public static final String MOD_NAME = "Additional Banners";
    public static final ItemGroup TAB_ADDITONAL_BANNERS = new CreativeTabAdditionalBanners();

    private static final Logger LOG = LogManager.getLogger(MOD_NAME);
    
    public AdditionalBanners() {
    	
    	FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, EventPriority.LOWEST, this::onItemsRegistered);
    }

    public void onItemsRegistered(Register<Item> event) {
    	
    	PatternHandler.initCraftingBanners();
    }
}