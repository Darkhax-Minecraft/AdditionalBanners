package net.darkhax.additionalbanners.config;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.darkhax.additionalbanners.AdditionalBanners;
import net.darkhax.bookshelf.registry.BannerRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class WanderingTraderConfig {
    
    private final ForgeConfigSpec spec;
    
    public final BooleanValue enableWanderingVillagerTrades;
    public final Map<BannerPattern, VillagerTradeData> wanderingTrades = new HashMap<>();
    
    public WanderingTraderConfig(BannerRegistry registry) {
        
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("Should pattern stencils be added to the wandering trader pool?");
        this.enableWanderingVillagerTrades = builder.define("enable_stencil_trades", true);
        
        for (final BannerPattern pattern : registry.getPatterns()) {
            
            if (registry.getStencilItem(pattern) != null) {
                
                this.wanderingTrades.put(pattern, new VillagerTradeData(pattern, registry.getStencilItem(pattern), builder));
            }
        }
        
        this.spec = builder.build();
    }
    
    public ForgeConfigSpec getSpec () {
        
        return this.spec;
    }
    
    public void forceLoad () {
        
        Path path = FMLPaths.CONFIGDIR.get().resolve(AdditionalBanners.MOD_ID);
        final File dir = path.toFile();
        
        if (!dir.exists()) {
            
            dir.mkdirs();
        }
        
        path = path.resolve("wandering_trader.toml");
        
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        this.spec.setConfig(configData);
    }
    
    public static class VillagerTradeData {
        
        private final Item stencilItem;
        
        public final BooleanValue addBasicTrade;
        public final IntValue emeralds;
        public final IntValue maxTrades;
        public final IntValue xp;
        public final DoubleValue priceMultiplier;
        
        public VillagerTradeData(BannerPattern pattern, Item stencil, ForgeConfigSpec.Builder builder) {
            
            final String name = pattern.name().toLowerCase().replace("additionalbanners_", "");
            this.stencilItem = stencil;
            
            builder.comment("Wandering Trader trade for " + name + ".");
            builder.push(name);
            
            builder.comment("Should the stencil item for this pattern show up in wandering trader's trades?");
            this.addBasicTrade = builder.define("enabled", true);
            
            builder.comment("What is the base emerald cost for the stencil item?");
            this.emeralds = builder.defineInRange("emerald_cost", 12, 1, Integer.MAX_VALUE);
            
            builder.comment("How many trades can the player make before this trade becomes unavailable?");
            this.maxTrades = builder.defineInRange("max_trades", 1, 1, Integer.MAX_VALUE);
            
            builder.comment("How much XP should the player get from this trade?");
            this.xp = builder.defineInRange("reward_xp", 16, 0, Integer.MAX_VALUE);
            
            builder.comment("The price multiplier for the trade.");
            this.priceMultiplier = builder.defineInRange("price_multiplier", 1d, 0d, 1d);
            
            builder.pop();
        }
        
        @Nullable
        public BasicTrade buildTrade () {
            
            return this.stencilItem == null || !this.addBasicTrade.get() ? null : new BasicTrade(this.emeralds.get(), new ItemStack(this.stencilItem), this.maxTrades.get(), this.xp.get(), this.priceMultiplier.get().floatValue());
        }
    }
}