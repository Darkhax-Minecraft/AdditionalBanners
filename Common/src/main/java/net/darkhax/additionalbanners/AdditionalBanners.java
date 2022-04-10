package net.darkhax.additionalbanners;

import net.darkhax.bookshelf.api.Services;

public class AdditionalBanners {

    public static final String MOD_ID = "additionalbanners";

    public AdditionalBanners() {

        Services.REGISTRIES.loadContent(new Content());
    }
}