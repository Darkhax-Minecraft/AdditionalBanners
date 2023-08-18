package net.darkhax.additionalbanners.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import net.darkhax.additionalbanners.Constants;
import net.darkhax.bookshelf.api.Services;
import net.darkhax.bookshelf.api.entity.merchant.MerchantTier;
import net.darkhax.bookshelf.api.entity.merchant.trade.VillagerSells;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.function.Supplier;

public class TradeConfig {

    @Expose
    private WanderingOffer wanderingVillager;

    @Expose
    private VillagerOffer shepherdOffer;

    public WanderingOffer getWanderingOffer() {

        return this.wanderingVillager;
    }

    public VillagerOffer getShepherdOffer() {

        return this.shepherdOffer;
    }

    private TradeConfig(boolean isRare, int cost, boolean canShepherdTrade) {

        this.wanderingVillager = new WanderingOffer(true, cost, isRare);
        this.shepherdOffer = new VillagerOffer(canShepherdTrade, cost);
    }

    public static TradeConfig getDefaultConfig(Rarity rarity) {

        return switch (rarity) {

            case COMMON -> new TradeConfig(false, 8, false);
            case UNCOMMON -> new TradeConfig(false, 13, false);
            case RARE -> new TradeConfig(true, 18, false);
            case EPIC -> new TradeConfig(true, 23, true);
        };
    }

    public static class VillagerOffer extends Offer {

        @Expose
        private MerchantTier tier = MerchantTier.EXPERT;

        private VillagerOffer(boolean enabled, int emeraldCost) {
            super(enabled, emeraldCost);
        }

        public MerchantTier getTier() {

            return this.tier;
        }
    }
    public static class WanderingOffer extends Offer {

        @Expose
        private boolean isRareTrade;

        private WanderingOffer(boolean enabled, int emeraldCost, boolean isRare) {

            super(enabled, emeraldCost);
            this.isRareTrade = isRare;
        }

        public boolean isRareTrade() {

            return this.isRareTrade;
        }
    }

    public static class Offer {

        @Expose
        private boolean enabled;

        @Expose
        private int emeraldCost;

        @Expose
        private int maxUses = 8;

        @Expose
        private int villagerExp = 1;

        @Expose
        private float priceMultiplier = 0.5f;

        private Offer(boolean enabled, int emeraldCost) {

            this.enabled = enabled;
            this.emeraldCost = emeraldCost;
        }

        public VillagerTrades.ItemListing createTrade(Supplier<? extends ItemLike> toBuy) {

            return VillagerSells.create(toBuy, this.emeraldCost, this.maxUses, this.villagerExp, this.priceMultiplier);
        }

        public boolean isEnabled() {

            return this.enabled;
        }
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    public static TradeConfig load(String patternName, Rarity rarity) {

        File configDir = new File (new File(Services.PLATFORM.getConfigDirectory(), Constants.MOD_ID), "trades");

        if (!configDir.exists()) {
            Constants.LOG.info("Generating trades config dir at {}.", configDir.getPath());
            configDir.mkdirs();
        }

        File configFile = new File(configDir, patternName + ".json");

        TradeConfig defaultConfig = TradeConfig.getDefaultConfig(rarity);
        TradeConfig config = defaultConfig;

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = GSON.fromJson(reader, TradeConfig.class);
            }

            catch (Exception e) {

                Constants.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath(), e);
            }
        }

        else {

            Constants.LOG.debug("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(configFile)) {

            GSON.toJson(config, writer);
        }

        catch (Exception e) {

            Constants.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath(), e);
        }

        if (config == null) {

            Constants.LOG.error("Corrupted or invalid config file found! Defaults will be used. {}", configFile.getAbsolutePath());
            return defaultConfig;
        }

        return config;
    }
}