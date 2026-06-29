package net.darkhax.additionalbanners.common;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdditionalBanners {

    public static final String MOD_ID = "additionalbanners";
    public static final String MOD_NAME = "Additional Banners";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}