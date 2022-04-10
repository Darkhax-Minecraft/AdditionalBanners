package net.darkhax.additionalbanners;

import com.chocohead.mm.api.ClassTinkerers;
import com.chocohead.mm.api.EnumAdder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class AdditionalBannersEarlyRiser implements Runnable {

    @Override
    public void run() {

        final MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
        final String instrument = remapper.mapClassName("intermediary", "net.minecraft.class_2582");
        final EnumAdder adder = ClassTinkerers.enumBuilder(instrument, String.class, String.class, boolean.class);

        for (BannerPatterns.PatternData pattern : BannerPatterns.PATTERNS.values()) {

            adder.addEnum(pattern.enumName, () -> new Object[]{pattern.texture, pattern.texture, true});
        }

        adder.build();
    }
}