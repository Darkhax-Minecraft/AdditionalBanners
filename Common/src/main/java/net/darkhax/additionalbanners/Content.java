package net.darkhax.additionalbanners;

import net.darkhax.bookshelf.api.entity.merchant.MerchantTier;
import net.darkhax.bookshelf.api.entity.merchant.trade.VillagerSells;
import net.darkhax.bookshelf.api.registry.IRegistryObject;
import net.darkhax.bookshelf.api.registry.RegistryDataProvider;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BannerPattern;

public class Content extends RegistryDataProvider {

    public Content() {

        super(AdditionalBanners.MOD_ID);

        this.withCreativeTab(() -> Items.CREEPER_BANNER_PATTERN);

        for (BannerPatterns.PatternData pattern : BannerPatterns.PATTERNS.values()) {

            final IRegistryObject<BannerPatternItem> stencilItem = this.items.add(() -> new BannerPatternItem(BannerPattern.valueOf(pattern.enumName), new Item.Properties().rarity(pattern.rarity.get()).stacksTo(1)), pattern.name);

            switch (pattern.rarity.get()) {

                case COMMON -> this.trades.addCommonWanderingTrade(VillagerSells.create(stencilItem, 8, 8, 1, 0.5f));
                case UNCOMMON -> this.trades.addCommonWanderingTrade(VillagerSells.create(stencilItem, 12, 8, 1, 0.5f));
                case RARE -> this.trades.addRareWanderingTrade(VillagerSells.create(stencilItem, 16, 8, 1, 0.5f));
                case EPIC -> {
                    this.trades.addTrade(VillagerProfession.SHEPHERD, MerchantTier.EXPERT, VillagerSells.create(stencilItem, 18, 8, 1, 0.5f));
                    this.trades.addRareWanderingTrade(VillagerSells.create(stencilItem, 24, 8, 1, 0.5f));
                }
            }
        }
    }
}