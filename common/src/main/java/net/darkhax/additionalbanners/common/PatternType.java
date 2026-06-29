package net.darkhax.additionalbanners.common;

import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.function.Supplier;

public enum PatternType {

    ANCHOR("anchor", Rarity.COMMON),
    BALANCE("balance", Rarity.COMMON),
    GRASS("grass", Rarity.COMMON),
    KELP("kelp", Rarity.COMMON),
    MUSHROOM("mushroom", Rarity.COMMON),
    PUMPKIN("pumpkin", Rarity.COMMON),
    SNOWFLAKE("snowflake", Rarity.COMMON),
    STEPS("steps", Rarity.COMMON),

    SQUID("squid", Rarity.UNCOMMON),
    SHIELD("shield", Rarity.UNCOMMON),
    SWORD("sword", Rarity.UNCOMMON),

    FRAME("frame", Rarity.UNCOMMON),
    HEX("hex", Rarity.UNCOMMON),
    WATERFALL("waterfall", Rarity.UNCOMMON),
    STARBURST("starburst", Rarity.UNCOMMON),
    PRISMARINE("prismarine", Rarity.UNCOMMON),
    SCALES("scales", Rarity.UNCOMMON, "up", "down", "left", "right"),
    CHAIN("chain", Rarity.UNCOMMON),

    DRAGON("dragon", Rarity.RARE),
    PHANTOM("phantom", Rarity.RARE),
    TRIDENT("trident", Rarity.RARE),
    SPIDER("spider", Rarity.RARE),

    CLUBS("suit", Rarity.EPIC, "clubs", "diamond", "heart", "spades"),
    MOON("moon", Rarity.EPIC),
    SQUARES("squares", Rarity.EPIC);

    public final String main;
    public final String[] variants;
    public final Rarity rarity;
    public final TagKey<BannerPattern> bannerTag;
    public final Identifier itemId;
    public final Supplier<Item> item;

    PatternType(String main, Rarity rarity, String... variants) {
        this.main = main;
        this.rarity = rarity;
        this.variants = variants;
        this.bannerTag = TagKey.create(Registries.BANNER_PATTERN, AdditionalBanners.id("pattern_item/" + main));
        this.itemId = AdditionalBanners.id(main + "_banner_pattern");
        this.item = CachedSupplier.cache(() -> BuiltInRegistries.ITEM.getValue(this.itemId));
    }

    public boolean hasVariants() {
        return variants.length > 0;
    }
}