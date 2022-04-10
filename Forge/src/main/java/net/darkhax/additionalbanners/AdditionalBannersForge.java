package net.darkhax.additionalbanners;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.fml.common.Mod;

@Mod(AdditionalBanners.MOD_ID)
public class AdditionalBannersForge {

    private final AdditionalBanners instance;

    public AdditionalBannersForge() {

        this.instance = new AdditionalBanners();

        for (BannerPatterns.PatternData pattern : BannerPatterns.PATTERNS.values()) {

            BannerPattern.create(pattern.enumName, pattern.texture, pattern.texture, true);
        }
    }
}