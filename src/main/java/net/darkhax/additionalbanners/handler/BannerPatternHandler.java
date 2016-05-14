package net.darkhax.additionalbanners.handler;

import net.darkhax.additionalbanners.lib.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner.EnumBannerPattern;
import net.minecraftforge.common.util.EnumHelper;

public class BannerPatternHandler {
    
    public static EnumBannerPattern HASHTAG;
    public static EnumBannerPattern CAP;
    
    /**
     * Initializes all of the new banner patterns added by the mod.
     */
    public static void initCraftingBanners () {
        
        addCraftingPattern("bread", new ItemStack(Items.BREAD));
        addCraftingPattern("fish", new ItemStack(Items.FISH));
        addCraftingPattern("fishn", new ItemStack(Items.FISHING_ROD));
        addCraftingPattern("shield", new ItemStack(Items.OAK_DOOR));
        addCraftingPattern("pot", new ItemStack(Items.POTIONITEM));
        addCraftingPattern("bed", new ItemStack(Items.BED));
        addCraftingPattern("book", new ItemStack(Items.BOOK));
        addCraftingPattern("bow", new ItemStack(Items.BOW));
        addCraftingPattern("bucket", new ItemStack(Items.BUCKET));
        addCraftingPattern("write", new ItemStack(Items.WRITABLE_BOOK));
        addCraftingPattern("wheat", new ItemStack(Items.WHEAT));
        addCraftingPattern("paper", new ItemStack(Items.PAPER));
        addCraftingPattern("shear", new ItemStack(Items.SHEARS));
        addCraftingPattern("apple", new ItemStack(Items.APPLE));
        addCraftingPattern("egg", new ItemStack(Items.EGG));
        addCraftingPattern("sword", new ItemStack(Items.GOLDEN_SWORD));
        addCraftingPattern("axe", new ItemStack(Items.GOLDEN_AXE));
        addCraftingPattern("hoe", new ItemStack(Items.GOLDEN_HOE));
        addCraftingPattern("pick", new ItemStack(Items.GOLDEN_PICKAXE));
        addCraftingPattern("shovel", new ItemStack(Items.GOLDEN_SHOVEL));
        addCraftingPattern("boots", new ItemStack(Items.GOLDEN_BOOTS));
        addCraftingPattern("legs", new ItemStack(Items.GOLDEN_LEGGINGS));
        addCraftingPattern("chest", new ItemStack(Items.GOLDEN_CHESTPLATE));
        addCraftingPattern("helm", new ItemStack(Items.GOLDEN_HELMET));
        addCraftingPattern("horse", new ItemStack(Items.GOLDEN_HORSE_ARMOR));
        addCraftingPattern("pumpkin", new ItemStack(Blocks.PUMPKIN));
        addCraftingPattern("grass", new ItemStack(Blocks.GRASS));
        addCraftingPattern("pillar", new ItemStack(Blocks.QUARTZ_BLOCK, 1, 2));
        addCraftingPattern("cobble", new ItemStack(Blocks.COBBLESTONE));
        addCraftingPattern("tag", new ItemStack(Items.NAME_TAG));
        addCraftingPattern("template", new ItemStack(Blocks.BEDROCK));
        addCraftingPattern("dragon", new ItemStack(Items.DRAGON_BREATH));
        addCraftingPattern("squid", new ItemStack(Items.PRISMARINE_SHARD));
        addCraftingPattern("planks", new ItemStack(Blocks.PLANKS));
        
        HASHTAG = addBasicPattern("hashtag");
        CAP = addCraftingPattern("cap", new ItemStack(Item.getItemFromBlock(Blocks.STONE_SLAB)));
    }
    
    /**
     * Adds a new banner pattern to the game. This banner pattern will be hidden and will not
     * have a crafting recipe. An example of such is the base white pattern in vanilla.
     * 
     * @param name The name of the banner pattern. This is used for the texture file, and is
     *            also converted into upper case and used for the enum entry. Given how this
     *            system works, it's critical that this value is unique, consider adding the
     *            mod id to the name.
     * @param id A small string used to represent the pattern without taking up much space. An
     *            example of this is "bri". Given how the system works, it is critical that
     *            this is a unique value. please consider adding the mod id to the pattern id.
     * @return EnumBannerPattern: A reference to the new EnumBannerPattern entry that has been
     *         created.
     */
    public static EnumBannerPattern addBasicPattern (String name) {
        
        final Class<?>[] paramTypes = { String.class, String.class };
        final Object[] paramValues = { Constants.MOD_ID + "_" + name, Constants.MOD_ID + "." + name };
        return EnumHelper.addEnum(EnumBannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }
    
    /**
     * Adds a new banner pattern to the game. This banner pattern will be applied by using the
     * provided item in a crafting recipe with the banner.
     * 
     * @param name The name of the banner pattern. This is used for the texture file, and is
     *            also converted into upper case and used for the enum entry. Given how this
     *            system works, it's critical that this value is unique, consider adding the
     *            mod id to the name.
     * @param id A small string used to represent the pattern without taking up much space. An
     *            example of this is "bri". Given how the system works, it is critical that
     *            this is a unique value. please consider adding the mod id to the pattern id.
     *            
     * @param craftingStack An ItemStack which is used in the crafting recipe for this pattern.
     *            An example of this would be the creeper skull being used for the creeper
     *            pattern.
     * @return EnumBannerPattern: A reference to the new EnumBannerPattern entry that has been
     *         created.
     */
    public static EnumBannerPattern addCraftingPattern (String name, ItemStack craftingStack) {
        
        final Class<?>[] paramTypes = { String.class, String.class, ItemStack.class };
        final Object[] paramValues = { Constants.MOD_ID + "_" + name, Constants.MOD_ID + "." + name, craftingStack };
        return EnumHelper.addEnum(EnumBannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }
    
    /**
     * Adds a new banner pattern to the game. This banner pattern will be available by using
     * dyes in a specific configuration. Keep in mind that this pattern only accepts dyes, and
     * recipes can conflict. This means this option is limited to 2^9 (512) possibilities.
     * 
     * @param name The name of the banner pattern. This is used for the texture file, and is
     *            also converted into upper case and used for the enum entry. Given how this
     *            system works, it's critical that this value is unique, consider adding the
     *            mod id to the name.
     * @param id A small string used to represent the pattern without taking up much space. An
     *            example of this is "bri". Given how the system works, it is critical that
     *            this is a unique value. please consider adding the mod id to the pattern id.
     * @param craftingTop A layout for the pattern in the top row of the crafting grid. This is
     *            represented by a string with three characters. A blank space means nothing
     *            goes in that slot, while a # means that a dye would go in that slot. Example:
     *            "# #"
     * @param craftingMid A layout for the pattern in the middle row of the crafting grid. This
     *            is represented by a string with three characters. A blank space means nothing
     *            goes in that slot, while a # means that a dye would go in that slot. Example:
     *            " # "
     * @param craftingBot A layout for the pattern in the bottom row of the crafting grid. This
     *            is represented by a string with three characters. A blank space means nothing
     *            goes in that slot, while a # means that a dye would go in that slot. Example:
     *            "###"
     * @return EnumBannerPattern: A reference to the new EnumBannerPattern entry that has been
     *         created.
     */
    public static EnumBannerPattern addDyePattern (String name, String id, String craftingTop, String craftingMid, String craftingBot) {
        
        final Class<?>[] paramTypes = { String.class, String.class, String.class, String.class, String.class };
        final Object[] paramValues = { name, id, craftingTop, craftingMid, craftingBot };
        return EnumHelper.addEnum(EnumBannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }
}
