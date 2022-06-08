package net.darkhax.additionalbanners;

import net.fabricmc.api.ModInitializer;

public class AdditionalBannersFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        Content.init();
    }
}