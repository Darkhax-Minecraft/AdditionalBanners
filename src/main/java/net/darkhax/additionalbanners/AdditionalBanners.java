package net.darkhax.additionalbanners;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.darkhax.additionalbanners.lib.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER)
public class AdditionalBanners {
    
    @Mod.Instance(Constants.MOD_ID)
    public static AdditionalBanners instance;
    
    public static final CreativeTabs TAB_ADDITONAL_BANNERS = new CreativeTabAdditionalBanners();
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent pre) {
        
        PatternHandler.initCraftingBanners();
    }
}