package net.darkhax.additionalbanners.lib;

import net.darkhax.additionalbanners.handler.BannerPatternHandler;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBanner.EnumBannerPattern;

public class BannerUtils {
    
    /**
     * Creates a new Banner ItemStack that has all of the patterns in the NBTTagList written to
     * it.
     * 
     * @param baseColor The base color for the banner.
     * @param patterns The patterns to apply. This can be null if you want no patterns. See
     *            {@link #createPatternList(BannerLayer...)} for an easy way to make this.
     * @return The ItemStack that was created. All of the data is on the NBT.
     */
    public static ItemStack createBanner (EnumDyeColor baseColor, NBTTagList patterns) {
        
        final ItemStack stack = new ItemStack(Items.BANNER, 1, baseColor.getDyeDamage());
        final NBTTagCompound blockTag = new NBTTagCompound();
        final NBTTagCompound stackTag = new NBTTagCompound();
        
        TileEntityBanner.setBaseColorAndPatterns(blockTag, baseColor.getDyeDamage(), patterns);
        stackTag.setTag("BlockEntityTag", blockTag);
        stack.setTagCompound(stackTag);
        
        return stack;
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
            tag.setString("Pattern", layer.pattern.getPatternID());
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
            tag.setString("Pattern", layer.pattern.getPatternID());
            tag.setInteger("Color", layer.color == EnumDyeColor.WHITE ? color.getDyeDamage() : layer.color.getDyeDamage());
            patterns.appendTag(tag);
        }
        
        return patterns;
    }
    
    public static class BannerLayer {
        
        private final EnumBannerPattern pattern;
        private final EnumDyeColor color;
        
        public BannerLayer(EnumBannerPattern pattern, EnumDyeColor color) {
            
            this.pattern = pattern;
            this.color = color;
        }
        
        public EnumBannerPattern getPattern () {
            
            return this.pattern;
        }
        
        public EnumDyeColor getColor () {
            
            return this.color;
        }
    }
    
    public static void addCharacters (EnumDyeColor baseColor) {
    
    }
    
    /**
     * An enumeration of all the english alphabet characters and several additional symbols.
     * Credits to pawjwp who created the command used to generate these
     * http://mcstacker.bimbimma.com/#!477
     */
    public static enum TypeDesign {
        
        A(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        B(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        C(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        D(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.CURLY_BORDER, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        E(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        F(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        G(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_VERTICAL, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        H(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        I(new BannerLayer[] { new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_CENTER, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        J(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        K(new BannerLayer[] { new BannerLayer(EnumBannerPattern.CROSS, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_VERTICAL, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        L(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        M(new BannerLayer[] { new BannerLayer(EnumBannerPattern.TRIANGLE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.TRIANGLES_TOP, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        N(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNRIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        O(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        P(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL_MIRROR, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        Q(new BannerLayer[] { new BannerLayer(EnumBannerPattern.RHOMBUS_MIDDLE, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.SQUARE_BOTTOM_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        R(new BannerLayer[] { new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNRIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        S(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNRIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        T(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_CENTER, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        U(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        V(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNLEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        W(new BannerLayer[] { new BannerLayer(EnumBannerPattern.TRIANGLE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.TRIANGLES_BOTTOM, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        X(new BannerLayer[] { new BannerLayer(EnumBannerPattern.CROSS, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        Y(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_DOWNRIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL_MIRROR, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_DOWNLEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        Z(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNLEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        PERIOD(new BannerLayer[] { new BannerLayer(EnumBannerPattern.TRIANGLES_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        EXCLAMATION(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_CENTER, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.TRIANGLES_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        // QUESTION(new BannerLayer[] { new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL,
        // EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT,
        // EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT,
        // EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.SQUARE_BOTTOM_RIGHT,
        // EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_TOP,
        // EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE)
        // }),
        ADD(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRAIGHT_CROSS, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        MINUS(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        MULTIPLY(new BannerLayer[] { new BannerLayer(EnumBannerPattern.CROSS, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        DIVIDE(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        PERCENT(new BannerLayer[] { new BannerLayer(EnumBannerPattern.SQUARE_TOP_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.SQUARE_BOTTOM_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNLEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) }),
        MONEY(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNRIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_CENTER, EnumDyeColor.BLACK) }),
        HASHTAG(new BannerLayer[] { new BannerLayer(BannerPatternHandler.HASHTAG, EnumDyeColor.BLACK) }),
        ONE(new BannerLayer[] { new BannerLayer(EnumBannerPattern.SQUARE_TOP_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_CENTER, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        TWO(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNLEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        THREE(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        FOUR(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL_MIRROR, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        FIVE(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_DOWNRIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        SIX(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        SEVEN(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.DIAGONAL_RIGHT, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_DOWNLEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        EIGHT(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        NINE(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.HALF_HORIZONTAL_MIRROR, EnumDyeColor.WHITE), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_MIDDLE, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE), new BannerLayer(BannerPatternHandler.CAP, EnumDyeColor.WHITE) }),
        ZERO(new BannerLayer[] { new BannerLayer(EnumBannerPattern.STRIPE_RIGHT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_TOP, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_BOTTOM, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.STRIPE_LEFT, EnumDyeColor.BLACK), new BannerLayer(EnumBannerPattern.BORDER, EnumDyeColor.WHITE) });
        
        private final BannerLayer[] layers;
        
        TypeDesign(BannerLayer[] layer) {
            
            this.layers = layer;
        }
        
        public BannerLayer[] getLayers () {
            
            return this.layers;
        }
    }
}