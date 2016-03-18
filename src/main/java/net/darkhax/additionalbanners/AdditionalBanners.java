package net.darkhax.additionalbanners;

import net.darkhax.additionalbanners.common.CommonProxy;
import net.darkhax.additionalbanners.handler.BannerPatternHandler;
import net.darkhax.additionalbanners.lib.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER, acceptedMinecraftVersions = Constants.MCVERSION)
public class AdditionalBanners {
    
    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    
    @Mod.Instance(Constants.MOD_ID)
    public static AdditionalBanners instance;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent pre) {
        
        BannerPatternHandler.initCraftingBanners();
    }
}