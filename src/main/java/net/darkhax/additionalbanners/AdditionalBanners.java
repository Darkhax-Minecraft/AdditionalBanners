package net.darkhax.additionalbanners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.additionalbanners.config.WanderingTraderConfig;
import net.darkhax.additionalbanners.config.WanderingTraderConfig.VillagerTradeData;
import net.darkhax.bookshelf.item.ItemGroupBase;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = AdditionalBanners.MOD_ID)
public class AdditionalBanners {
    
    public static final String MOD_ID = "additionalbanners";
    
    private final Logger log;
    private final ItemGroup tab;
    private final RegistryHelper registry;
    
    public final BannerPattern anchor;
    public final BannerPattern balance;
    public final BannerPattern grass;
    public final BannerPattern kelp;
    public final BannerPattern mushroom;
    public final BannerPattern pumpkin;
    public final BannerPattern shield;
    public final BannerPattern snowflake;
    
    public final BannerPattern squid;
    public final BannerPattern sword;
    
    public final BannerPattern dragon;
    public final BannerPattern phantom;
    public final BannerPattern trident;
    
    public final BannerPattern clubs;
    public final BannerPattern diamond;
    public final BannerPattern heart;
    public final BannerPattern spades;
    public final BannerPattern moon;
    public final BannerPattern frame;
    public final BannerPattern squares;
    
    public AdditionalBanners() {
        
        this.log = LogManager.getLogger(MOD_ID);
        this.tab = new ItemGroupBase(MOD_ID, Items.MOJANG_BANNER_PATTERN);
        this.registry = new RegistryHelper(MOD_ID, this.log).withItemGroup(this.tab);
        
        // banners
        this.anchor = this.registry.banners.registerItemPattern("anchor", Rarity.COMMON);
        this.balance = this.registry.banners.registerItemPattern("balance", Rarity.COMMON);
        this.grass = this.registry.banners.registerItemPattern("grass", Rarity.COMMON);
        this.kelp = this.registry.banners.registerItemPattern("kelp", Rarity.COMMON);
        this.mushroom = this.registry.banners.registerItemPattern("mushroom", Rarity.COMMON);
        this.pumpkin = this.registry.banners.registerItemPattern("pumpkin", Rarity.COMMON);
        this.snowflake = this.registry.banners.registerItemPattern("snowflake", Rarity.COMMON);
        
        this.squid = this.registry.banners.registerItemPattern("squid", Rarity.UNCOMMON);
        this.shield = this.registry.banners.registerItemPattern("shield", Rarity.UNCOMMON);
        this.sword = this.registry.banners.registerItemPattern("sword", Rarity.UNCOMMON);
        
        this.dragon = this.registry.banners.registerItemPattern("dragon", Rarity.RARE);
        this.phantom = this.registry.banners.registerItemPattern("phantom", Rarity.RARE);
        this.trident = this.registry.banners.registerItemPattern("trident", Rarity.RARE);
        this.frame = this.registry.banners.registerItemPattern("frame", Rarity.RARE);
        
        this.clubs = this.registry.banners.registerItemPattern("clubs", Rarity.EPIC);
        this.diamond = this.registry.banners.registerItemPattern("diamond", Rarity.EPIC);
        this.heart = this.registry.banners.registerItemPattern("heart", Rarity.EPIC);
        this.spades = this.registry.banners.registerItemPattern("spades", Rarity.EPIC);
        this.moon = this.registry.banners.registerItemPattern("moon", Rarity.EPIC);
        this.squares = this.registry.banners.registerItemPattern("squares", Rarity.EPIC);
        
        final WanderingTraderConfig config = new WanderingTraderConfig(this.registry.banners);
        config.forceLoad();
        
        if (config.enableWanderingVillagerTrades.get()) {
            
            for (final BannerPattern pattern : this.registry.banners.getPatterns()) {
                
                final VillagerTradeData tradeConfig = config.wanderingTrades.get(pattern);
                
                if (tradeConfig != null) {
                    
                    final BasicTrade trade = tradeConfig.buildTrade();
                    
                    if (trade != null) {
                        
                        this.registry.trades.addBasicWanderingTrade(trade);
                        this.log.debug("Wandering Villager can trade " + pattern.name());
                    }
                }
            }
        }
        
        this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
    }
}