package net.darkhax.additionalbanners.handler;

import net.darkhax.additionalbanners.lib.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.common.util.EnumHelper;

public class PatternHandler {

    public static BannerPattern HASHTAG;
    public static BannerPattern CAP;

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
        addCraftingPattern("dragon", new ItemStack(Items.DRAGON_BREATH));
        addCraftingPattern("squid", new ItemStack(Items.PRISMARINE_SHARD));
        addCraftingPattern("planks", new ItemStack(Blocks.PLANKS));
        addCraftingPattern("balance", new ItemStack(Items.MILK_BUCKET));

        HASHTAG = addCraftingPattern("hashtag", new ItemStack(Blocks.CRAFTING_TABLE));
        CAP = addCraftingPattern("cap", new ItemStack(Item.getItemFromBlock(Blocks.STONE_SLAB)));
    }

    /**
     * Adds a new banner pattern to the game. This banner pattern will be hidden and will not
     * have a crafting recipe. An example of such is the base white pattern in vanilla.
     *
     * @param name The name of the banner pattern. This is used for the texture file, and is
     *        also converted into upper case and used for the enum entry. Given how this system
     *        works, it's critical that this value is unique, consider adding the mod id to the
     *        name.
     * @param id A small string used to represent the pattern without taking up much space. An
     *        example of this is "bri". Given how the system works, it is critical that this is
     *        a unique value. please consider adding the mod id to the pattern id.
     * @return BannerPattern: A reference to the new BannerPattern entry that has been created.
     */
    public static BannerPattern addBasicPattern (String name) {

        final Class<?>[] paramTypes = { String.class, String.class };
        final Object[] paramValues = { Constants.MOD_ID + "_" + name, Constants.MOD_ID + "." + name };
        return EnumHelper.addEnum(BannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }

    /**
     * Adds a new banner pattern to the game. This banner pattern will be applied by using the
     * provided item in a crafting recipe with the banner.
     *
     * @param name The name of the banner pattern. This is used for the texture file, and is
     *        also converted into upper case and used for the enum entry. Given how this system
     *        works, it's critical that this value is unique, consider adding the mod id to the
     *        name.
     * @param id A small string used to represent the pattern without taking up much space. An
     *        example of this is "bri". Given how the system works, it is critical that this is
     *        a unique value. please consider adding the mod id to the pattern id.
     *
     * @param craftingStack An ItemStack which is used in the crafting recipe for this pattern.
     *        An example of this would be the creeper skull being used for the creeper pattern.
     * @return BannerPattern: A reference to the new BannerPattern entry that has been created.
     */
    public static BannerPattern addCraftingPattern (String name, ItemStack craftingStack) {

        final Class<?>[] paramTypes = { String.class, String.class, ItemStack.class };
        final Object[] paramValues = { Constants.MOD_ID + "_" + name, Constants.MOD_ID + "." + name, craftingStack };
        return EnumHelper.addEnum(BannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }

    /**
     * Adds a new banner pattern to the game. This banner pattern will be available by using
     * dyes in a specific configuration. Keep in mind that this pattern only accepts dyes, and
     * recipes can conflict. This means this option is limited to 2^9 (512) possibilities.
     *
     * @param name The name of the banner pattern. This is used for the texture file, and is
     *        also converted into upper case and used for the enum entry. Given how this system
     *        works, it's critical that this value is unique, consider adding the mod id to the
     *        name.
     * @param id A small string used to represent the pattern without taking up much space. An
     *        example of this is "bri". Given how the system works, it is critical that this is
     *        a unique value. please consider adding the mod id to the pattern id.
     * @param craftingTop A layout for the pattern in the top row of the crafting grid. This is
     *        represented by a string with three characters. A blank space means nothing goes
     *        in that slot, while a # means that a dye would go in that slot. Example: "# #"
     * @param craftingMid A layout for the pattern in the middle row of the crafting grid. This
     *        is represented by a string with three characters. A blank space means nothing
     *        goes in that slot, while a # means that a dye would go in that slot. Example: " #
     *        "
     * @param craftingBot A layout for the pattern in the bottom row of the crafting grid. This
     *        is represented by a string with three characters. A blank space means nothing
     *        goes in that slot, while a # means that a dye would go in that slot. Example:
     *        "###"
     * @return BannerPattern: A reference to the new BannerPattern entry that has been created.
     */
    public static BannerPattern addDyePattern (String name, String id, String craftingTop, String craftingMid, String craftingBot) {

        final Class<?>[] paramTypes = { String.class, String.class, String.class, String.class, String.class };
        final Object[] paramValues = { name, id, craftingTop, craftingMid, craftingBot };
        return EnumHelper.addEnum(BannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }

    /**
     * Creates a new Banner ItemStack that has all of the patterns in the NBTTagList written to
     * it.
     *
     * @param baseColor The base color for the banner.
     * @param patterns The patterns to apply. This can be null if you want no patterns. See
     *        {@link #createPatternList(BannerLayer...)} for an easy way to make this.
     * @return The ItemStack that was created. All of the data is on the NBT.
     */
    public static ItemStack createBanner (EnumDyeColor baseColor, NBTTagList patterns) {

        return ItemBanner.makeBanner(baseColor, patterns);
    }

    /**
     * Creates an NBTTagList which contains the data for the banner layers passed. Each layer
     * is stored as a String for the ID and an int that represents an EnumDyeColor. The created
     * NBTTagList can be directly used by the Banner TileEntity.
     *
     * @param layers The layers to write to NBT.
     * @return An NBTTagList that contains all of the banner layers written as tag compound.
     */
    public static NBTTagList createPatternList (BannerLayer... layers) {

        final NBTTagList patterns = new NBTTagList();

        for (final BannerLayer layer : layers) {

            final NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Pattern", layer.pattern.getHashname());
            tag.setInteger("Color", layer.color.getDyeDamage());
            patterns.appendTag(tag);
        }

        return patterns;
    }

    /**
     * Creates an NBTTagList which contains the data for the banner layers passed. Each layer
     * is stored as a String for the ID and an int that represents an EnumDyeColor. The created
     * NBTTagList can be directly used by the Banner TileEntity.
     *
     * @param layers The layers to write to NBT.
     * @return An NBTTagList that contains all of the banner layers written as tag compound.
     */
    public static NBTTagList createPatternList (EnumDyeColor color, BannerLayer... layers) {

        final NBTTagList patterns = new NBTTagList();

        for (final BannerLayer layer : layers) {

            final NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Pattern", layer.pattern.getHashname());
            tag.setInteger("Color", layer.color == EnumDyeColor.WHITE ? color.getDyeDamage() : layer.color.getDyeDamage());
            patterns.appendTag(tag);
        }

        return patterns;
    }

    public static class BannerLayer {

        private final BannerPattern pattern;
        private final EnumDyeColor color;

        public BannerLayer (BannerPattern pattern, EnumDyeColor color) {

            this.pattern = pattern;
            this.color = color;
        }

        public BannerPattern getPattern () {

            return this.pattern;
        }

        public EnumDyeColor getColor () {

            return this.color;
        }
    }

    public static void addCharacters (EnumDyeColor baseColor) {

    }
}
