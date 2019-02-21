package net.darkhax.additionalbanners.handler;

import net.darkhax.additionalbanners.AdditionalBanners;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.BannerPattern;

public class PatternHandler {

    /**
     * Initializes all of the new banner patterns added by the mod.
     */
    public static void initCraftingBanners () {

        addCraftingPattern("bread", new ItemStack(Items.BREAD));
        addCraftingPattern("fish", new ItemStack(Items.COD));
        addCraftingPattern("fishn", new ItemStack(Items.FISHING_ROD));
        addCraftingPattern("shield", new ItemStack(Blocks.OAK_DOOR));
        addCraftingPattern("pot", new ItemStack(Items.POTION));
        addCraftingPattern("bed", new ItemStack(Items.WHITE_BED));
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
        addCraftingPattern("pillar", new ItemStack(Blocks.QUARTZ_PILLAR));
        addCraftingPattern("cobble", new ItemStack(Blocks.COBBLESTONE));
        addCraftingPattern("tag", new ItemStack(Items.NAME_TAG));
        addCraftingPattern("dragon", new ItemStack(Items.DRAGON_BREATH));
        addCraftingPattern("squid", new ItemStack(Items.PRISMARINE_SHARD));
        addCraftingPattern("planks", new ItemStack(Blocks.OAK_PLANKS));
        addCraftingPattern("balance", new ItemStack(Items.MILK_BUCKET));
        addCraftingPattern("mushred", new ItemStack(Blocks.RED_MUSHROOM));
        addCraftingPattern("turtle", new ItemStack(Items.SCUTE));
        addCraftingPattern("phantom", new ItemStack(Items.PHANTOM_MEMBRANE));
        addCraftingPattern("trident", new ItemStack(Items.TRIDENT));
        addCraftingPattern("anchor", new ItemStack(Items.OAK_BOAT));
        
        addCraftingPattern("ingot", new ItemStack(Items.GOLD_INGOT));
        addCraftingPattern("gem", new ItemStack(Items.EMERALD));
        addCraftingPattern("dust", new ItemStack(Items.REDSTONE));
        addCraftingPattern("flint_and_steel", new ItemStack(Items.FLINT_AND_STEEL));
        addCraftingPattern("kelp", new ItemStack(Blocks.KELP));
    }
    
    public static BannerPattern addCraftingPattern (String name, ItemStack craftingStack) {

    	return BannerPattern.create(name.toUpperCase(), AdditionalBanners.MOD_ID + "_" + name, AdditionalBanners.MOD_ID  + "_" + name, craftingStack);
    }
    
    public static ItemStack createBanner(Item base, BannerPattern... patterns) {
    	
    	final ItemStack stack = new ItemStack(base);
    	
    	NBTTagCompound blockEntityTag = stack.getOrCreateChildTag("BlockEntityTag");
    	
    	NBTTagList list = new NBTTagList();
    	
    	for (BannerPattern pattern : patterns) {
    		
    		NBTTagCompound patternTag = new NBTTagCompound();
    		patternTag.setString("Pattern", pattern.getHashname());
    		patternTag.setInt("Color", 15);
    		list.add(patternTag);
    	}
    	
    	blockEntityTag.setTag("Patterns", list);
    	
    	return stack;
    }
}
