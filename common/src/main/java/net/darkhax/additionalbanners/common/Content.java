package net.darkhax.additionalbanners.common;

import net.darkhax.bookshelf.common.api.registry.ContentProvider;
import net.darkhax.bookshelf.common.impl.registry.adapter.CreativeModeTabAdapter;
import net.darkhax.bookshelf.common.impl.registry.adapter.ItemRegistryAdapter;
import net.minecraft.core.component.DataComponents;

public final class Content implements ContentProvider {

    @Override
    public void defineItems(ItemRegistryAdapter registry) {
        for (PatternType type : PatternType.values()) {
            registry.addSimple(type.itemId.getPath(), p -> p.stacksTo(1).rarity(type.rarity).delayedComponent(DataComponents.PROVIDES_BANNER_PATTERNS, ctx -> ctx.getOrThrow(type.bannerTag)));
        }
    }

    @Override
    public void defineCreativeTabs(CreativeModeTabAdapter registry) {
        registry.add("tab", () -> PatternType.MOON.item.get().getDefaultInstance(), (params, builder) -> {
            for (PatternType type : PatternType.values()) {
                if (type.hasVariants()) {

                }
                else {

                }
                builder.accept(type.item.get());
            }
        });
    }

    @Override
    public String namespace() {
        return AdditionalBanners.MOD_ID;
    }
}