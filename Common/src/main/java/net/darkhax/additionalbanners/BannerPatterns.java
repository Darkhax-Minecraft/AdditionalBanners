package net.darkhax.additionalbanners;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BannerPatterns {

    public static final Map<String, PatternData> PATTERNS = new HashMap<>();

    private static final String MOD_ID = "additionalbanners";
    private static final String COMMON = "COMMON";
    private static final String UNCOMMON = "UNCOMMON";
    private static final String RARE = "RARE";
    private static final String EPIC = "EPIC";

    public static final PatternData ANCHOR = createPattern("anchor", COMMON);
    public static final PatternData BALANCE = createPattern("balance", COMMON);
    public static final PatternData GRASS = createPattern("grass", COMMON);
    public static final PatternData KELP = createPattern("kelp", COMMON);
    public static final PatternData MUSHROOM = createPattern("mushroom", COMMON);
    public static final PatternData PUMPKIN = createPattern("pumpkin", COMMON);
    public static final PatternData SNOWFLAKE = createPattern("snowflake", COMMON);

    public static final PatternData SQUID = createPattern("squid", UNCOMMON);
    public static final PatternData SHIELD = createPattern("shield", UNCOMMON);
    public static final PatternData SWORD = createPattern("sword", UNCOMMON);
    public static final PatternData FRAME = createPattern("frame", UNCOMMON);
    public static final PatternData HEX = createPattern("hex", UNCOMMON);

    public static final PatternData DRAGON = createPattern("dragon", RARE);
    public static final PatternData PHANTOM = createPattern("phantom", RARE);
    public static final PatternData TRIDENT = createPattern("trident", RARE);

    public static final PatternData CLUBS = createPattern("clubs", EPIC);
    public static final PatternData DIAMOND = createPattern("diamond", EPIC);
    public static final PatternData HEART = createPattern("heart", EPIC);
    public static final PatternData SPADES = createPattern("spades", EPIC);
    public static final PatternData MOON = createPattern("moon", EPIC);
    public static final PatternData SQUARES = createPattern("squares", EPIC);

    private static PatternData createPattern(String name, String rarity) {

        final PatternData pattern = new PatternData(name, rarity);
        PATTERNS.put(name, pattern);
        return pattern;
    }

    public static class PatternData {

        public final String name;
        public final String rarity;
        public final String enumName;
        public final String texture;

        public PatternData(String name, String rarity) {
            this.name = name;
            this.rarity = rarity;
            this.texture = MOD_ID + "_" + this.name;
            this.enumName = this.texture.toUpperCase(Locale.ROOT);
        }
    }
}
