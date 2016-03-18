package net.darkhax.additionalbanners.handler;

import net.darkhax.additionalbanners.lib.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner.EnumBannerPattern;
import net.minecraftforge.common.util.EnumHelper;

public class BannerPatternHandler {
    
    public static void initCraftingBanners () {
        
        addCraftingPattern("bread", new ItemStack(Items.bread));
        addCraftingPattern("fish", new ItemStack(Items.fish));
        addCraftingPattern("fishn", new ItemStack(Items.fishing_rod));
        addCraftingPattern("shield", new ItemStack(Items.oak_door));
        addCraftingPattern("pot", new ItemStack(Items.potionitem));
        addCraftingPattern("bed", new ItemStack(Items.bed));
        addCraftingPattern("book", new ItemStack(Items.book));
        addCraftingPattern("bow", new ItemStack(Items.bow));
        addCraftingPattern("bucket", new ItemStack(Items.bucket));
        addCraftingPattern("write", new ItemStack(Items.writable_book));
        addCraftingPattern("wheat", new ItemStack(Items.wheat));
        addCraftingPattern("paper", new ItemStack(Items.paper));
        addCraftingPattern("shear", new ItemStack(Items.shears));
        addCraftingPattern("apple", new ItemStack(Items.apple));
        addCraftingPattern("egg", new ItemStack(Items.egg));
        addCraftingPattern("sword", new ItemStack(Items.golden_sword));
        addCraftingPattern("axe", new ItemStack(Items.golden_axe));
        addCraftingPattern("hoe", new ItemStack(Items.golden_hoe));
        addCraftingPattern("pick", new ItemStack(Items.golden_pickaxe));
        addCraftingPattern("shovel", new ItemStack(Items.golden_shovel));
        addCraftingPattern("boots", new ItemStack(Items.golden_boots));
        addCraftingPattern("legs", new ItemStack(Items.golden_leggings));
        addCraftingPattern("chest", new ItemStack(Items.golden_chestplate));
        addCraftingPattern("helm", new ItemStack(Items.golden_helmet));
        addCraftingPattern("horse", new ItemStack(Items.golden_horse_armor));
        addCraftingPattern("pumpkin", new ItemStack(Blocks.pumpkin));
        addCraftingPattern("grass", new ItemStack(Blocks.grass));
        addCraftingPattern("pillar", new ItemStack(Blocks.quartz_block, 1, 2));
        addCraftingPattern("cobble", new ItemStack(Blocks.cobblestone));
        addCraftingPattern("tag", new ItemStack(Items.name_tag));
        addCraftingPattern("template", new ItemStack(Blocks.bedrock));
    }
    
    /**
     * Adds a new banner pattern to the game. This banner pattern will be hidden and will not
     * have a crafting recipe. An example of such is the base white pattern in vanilla.
     * 
     * @param name : The name of the banner pattern. This is used for the texture file, and is
     *            also converted into upper case and used for the enum entry. Given how this
     *            system works, it's critical that this value is unique, consider adding the
     *            mod id to the name.
     * @param id : A small string used to represent the pattern without taking up much space.
     *            An example of this is "bri". Given how the system works, it is critical that
     *            this is a unique value. please consider adding the mod id to the pattern id.
     * @return EnumBannerPattern: A reference to the new EnumBannerPattern entry that has been
     *         created.
     */
    public static EnumBannerPattern addBasicPattern (String name, String id) {
        
        Class<?>[] paramTypes = { String.class, String.class };
        Object[] paramValues = { name, id };
        return EnumHelper.addEnum(EnumBannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }
    
    /**
     * Adds a new banner pattern to the game. This banner pattern will be applied by using the
     * provided item in a crafting recipe with the banner.
     * 
     * @param name : The name of the banner pattern. This is used for the texture file, and is
     *            also converted into upper case and used for the enum entry. Given how this
     *            system works, it's critical that this value is unique, consider adding the
     *            mod id to the name.
     * @param id : A small string used to represent the pattern without taking up much space.
     *            An example of this is "bri". Given how the system works, it is critical that
     *            this is a unique value. please consider adding the mod id to the pattern id.
     *            
     * @param craftingStack : An ItemStack which is used in the crafting recipe for this
     *            pattern. An example of this would be the creeper skull being used for the
     *            creeper pattern.
     * @return EnumBannerPattern: A reference to the new EnumBannerPattern entry that has been
     *         created.
     */
    public static EnumBannerPattern addCraftingPattern (String name, ItemStack craftingStack) {
        
        Class<?>[] paramTypes = { String.class, String.class, ItemStack.class };
        Object[] paramValues = { Constants.MOD_ID + "_" + name, Constants.MOD_ID + "." + name, craftingStack };
        return EnumHelper.addEnum(EnumBannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }
    
    /**
     * Adds a new banner pattern to the game. This banner pattern will be available by using
     * dyes in a specific configuration. Keep in mind that this pattern only accepts dyes, and
     * recipes can conflict. This means this option is limited to 2^9 (512) possibilities.
     * 
     * @param name : The name of the banner pattern. This is used for the texture file, and is
     *            also converted into upper case and used for the enum entry. Given how this
     *            system works, it's critical that this value is unique, consider adding the
     *            mod id to the name.
     * @param id : A small string used to represent the pattern without taking up much space.
     *            An example of this is "bri". Given how the system works, it is critical that
     *            this is a unique value. please consider adding the mod id to the pattern id.
     * @param craftingTop : A layout for the pattern in the top row of the crafting grid. This
     *            is represented by a string with three characters. A blank space means nothing
     *            goes in that slot, while a # means that a dye would go in that slot. Example:
     *            "# #"
     * @param craftingMid : A layout for the pattern in the middle row of the crafting grid.
     *            This is represented by a string with three characters. A blank space means
     *            nothing goes in that slot, while a # means that a dye would go in that slot.
     *            Example: " # "
     * @param craftingBot : A layout for the pattern in the bottom row of the crafting grid.
     *            This is represented by a string with three characters. A blank space means
     *            nothing goes in that slot, while a # means that a dye would go in that slot.
     *            Example: "###"
     * @return EnumBannerPattern: A reference to the new EnumBannerPattern entry that has been
     *         created.
     */
    public static EnumBannerPattern addDyePattern (String name, String id, String craftingTop, String craftingMid, String craftingBot) {
        
        Class<?>[] paramTypes = { String.class, String.class, String.class, String.class, String.class };
        Object[] paramValues = { name, id, craftingTop, craftingMid, craftingBot };
        return EnumHelper.addEnum(EnumBannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }
}
