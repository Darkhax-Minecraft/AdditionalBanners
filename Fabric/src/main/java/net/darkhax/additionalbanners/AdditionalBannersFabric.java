package net.darkhax.additionalbanners;

import net.fabricmc.api.ModInitializer;

public class AdditionalBannersFabric implements ModInitializer {

    private AdditionalBanners instance;

    @Override
    public void onInitialize() {

        this.instance = new AdditionalBanners();
    }
}