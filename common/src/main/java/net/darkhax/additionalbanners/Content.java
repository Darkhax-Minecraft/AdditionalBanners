package net.darkhax.additionalbanners;

import net.darkhax.additionalbanners.config.TradeConfig;
import net.darkhax.bookshelf.api.Services;
import net.darkhax.bookshelf.api.registry.IRegistryObject;
import net.darkhax.bookshelf.api.registry.RegistryDataProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
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
        createPattern("scales", Rarity.UNCOMMON, "up", "down", "left", "right");
        createPattern("chain", Rarity.UNCOMMON);

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

    private void createPattern(String name, Rarity rarity, String... variants) {

        final TagKey<BannerPattern> bannerTag = TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(Constants.MOD_ID, "pattern_item/" + name));
        final IRegistryObject<BannerPatternItem> stencilItem = this.items.add(() -> new BannerPatternItem(bannerTag, new Item.Properties().stacksTo(1).rarity(rarity)), name);
        addTradeEntries(name, stencilItem, rarity);

        for (String variant : variants) {

            final String subId = name + "_" + variant;
            this.bannerPatterns.add(() -> new BannerPattern(subId), subId);
        }
    }

    private void createPattern(String name, Rarity rarity) {

        final TagKey<BannerPattern> bannerTag = TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(Constants.MOD_ID, "pattern_item/" + name));
        final IRegistryObject<BannerPatternItem> stencilItem = this.items.add(() -> new BannerPatternItem(bannerTag, new Item.Properties().stacksTo(1).rarity(rarity)), name);
        this.bannerPatterns.add(() -> new BannerPattern(name), name);
        addTradeEntries(name, stencilItem, rarity);
    }

    private void addTradeEntries(String name, IRegistryObject<BannerPatternItem> stencilItem, Rarity rarity) {

        final TradeConfig config = TradeConfig.load(name, rarity);

        if (config != null) {

            final TradeConfig.WanderingOffer wanderingOffer = config.getWanderingOffer();

            if (wanderingOffer.isEnabled()) {

                if (wanderingOffer.isRareTrade()) {
                    this.trades.addRareWanderingTrade(wanderingOffer.createTrade(stencilItem));
                }

                else {
                    this.trades.addCommonWanderingTrade(wanderingOffer.createTrade(stencilItem));
                }
            }

            final TradeConfig.VillagerOffer shepherdOffer = config.getShepherdOffer();

            if (shepherdOffer.isEnabled() && shepherdOffer.getTier() != null) {

                this.trades.addTrade(VillagerProfession.SHEPHERD, shepherdOffer.getTier(), shepherdOffer.createTrade(stencilItem));
            }
        }
    }
}