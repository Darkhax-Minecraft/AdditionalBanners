package net.darkhax.additionalbanners;

import net.darkhax.bookshelf.api.Services;
import net.darkhax.bookshelf.api.entity.merchant.MerchantTier;
import net.darkhax.bookshelf.api.entity.merchant.trade.VillagerSells;
import net.darkhax.bookshelf.api.registry.IRegistryObject;
import net.darkhax.bookshelf.api.registry.RegistryDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class Content extends RegistryDataProvider {

    public static void init() {

        Services.REGISTRIES.loadContent(new Content());
    }

    private Content() {

        super(Constants.MOD_ID);

        this.withItemTab(() -> new ItemStack(Items.CREEPER_BANNER_PATTERN));

        createPattern("anchor", Rarity.COMMON);
        createPattern("balance", Rarity.COMMON);
        createPattern("grass", Rarity.COMMON);
        createPattern("kelp", Rarity.COMMON);
        createPattern("mushroom", Rarity.COMMON);
        createPattern("pumpkin", Rarity.COMMON);
        createPattern("snowflake", Rarity.COMMON);
        createPattern("steps", Rarity.COMMON);

        createPattern("squid", Rarity.UNCOMMON);
        createPattern("shield", Rarity.UNCOMMON);
        createPattern("sword", Rarity.UNCOMMON);
        createPattern("frame", Rarity.UNCOMMON);
        createPattern("hex", Rarity.UNCOMMON);
        createPattern("waterfall", Rarity.UNCOMMON);
        createPattern("starburst", Rarity.UNCOMMON);
        createPattern("prismarine", Rarity.UNCOMMON);

        createPattern("dragon", Rarity.RARE);
        createPattern("phantom", Rarity.RARE);
        createPattern("trident", Rarity.RARE);
        createPattern("cobweb", Rarity.RARE);
        createPattern("spider", Rarity.RARE);

        createPattern("clubs", Rarity.EPIC);
        createPattern("diamond", Rarity.EPIC);
        createPattern("heart", Rarity.EPIC);
        createPattern("spades", Rarity.EPIC);
        createPattern("moon", Rarity.EPIC);
        createPattern("squares", Rarity.EPIC);
    }

    private void createPattern(String name, Rarity rarity) {

        final TagKey<BannerPattern> bannerTag = Services.TAGS.bannerPatternTag(new ResourceLocation(Constants.MOD_ID, "pattern_item/" + name));
        final IRegistryObject<BannerPattern> pattern = this.bannerPatterns.add(() -> new BannerPattern(name), name);
        final IRegistryObject<BannerPatternItem> stencilItem = this.items.add(() -> new BannerPatternItem(bannerTag, new Item.Properties().stacksTo(1).rarity(rarity)), name);

        // Register Trades
        switch (rarity) {

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