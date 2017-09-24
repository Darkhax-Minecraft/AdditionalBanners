package net.darkhax.additionalbanners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.additionalbanners.handler.PatternHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AdditionalBanners.MOD_ID, name = AdditionalBanners.MOD_NAME, version = "@VERSION@", certificateFingerprint = "@FINGERPRINT@")
public class AdditionalBanners {

    public static final String MOD_ID = "additionalbanners";
    public static final String MOD_NAME = "Additional Banners";
    public static final CreativeTabs TAB_ADDITONAL_BANNERS = new CreativeTabAdditionalBanners();

    private static final Logger LOG = LogManager.getLogger(MOD_NAME);
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent pre) {

        PatternHandler.initCraftingBanners();
    }
    
    @EventHandler
    public void onFingerprintViolation (FMLFingerprintViolationEvent event) {

        LOG.error("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
}