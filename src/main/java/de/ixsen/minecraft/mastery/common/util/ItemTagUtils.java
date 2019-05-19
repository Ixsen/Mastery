package de.ixsen.minecraft.mastery.common.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Can be used to manage the tags for an item stack.
 * 
 * @author Subaro
 *
 */
public class ItemTagUtils {

    public static final String TOOLTIP_TAG = "TOOLTIP_";

    /**
     * Retrieves the tag compound from an item stack or creates a new one if needed.
     * 
     * @param stack
     * @return
     */
    private static NBTTagCompound getTagCompound(ItemStack stack) {
        NBTTagCompound compound;
        // Get tag library
        if (stack.hasTagCompound()) {
            compound = stack.getTagCompound();
        } else {
            compound = new NBTTagCompound();
        }
        return compound;
    }

    /**
     * Retrieves all tags that begin with 'TOOLTIP_'. These should be displayed in the tooltip.
     * 
     * @param stack
     * @return List of all 'TOOLTIP_' tags
     */
    public static List<String> getAllTooltipTags(ItemStack stack) {
        NBTTagCompound compound = getTagCompound(stack);
        List<String> allTags = new ArrayList<>();
        for (String string : compound.getKeySet()) {
            if (string.contains(TOOLTIP_TAG)) {
                allTags.add(string);
            }
        }
        return allTags;
    }

    /**
     * Removes a tag and it's value from the item stack.
     * 
     * @param stack
     */
    public static void removeTag(ItemStack stack, String tag) {
        NBTTagCompound compound = getTagCompound(stack);
        // Set the tag and value
        compound.removeTag(tag);
        stack.setTagCompound(compound);
    }

    /**
     * Determines whether the stack contains the given tag.
     * 
     * @param stack
     */
    public static boolean hasTag(ItemStack stack, String tag) {
        NBTTagCompound compound = getTagCompound(stack);
        // Set the tag and value
        return compound.hasKey(tag);
    }

    /**
     * Adds a new tag with a given integer value to the item stack.
     * 
     * @param stack
     * @param value
     */
    public static void addTagInteger(ItemStack stack, String tag, int value) {
        NBTTagCompound compound = getTagCompound(stack);
        // Set the tag and value
        compound.setInteger(tag, value);
        stack.setTagCompound(compound);
    }

    /**
     * Adds a new tag with a given integer value to the item stack.
     * 
     * @param stack
     * @param value
     */
    public static void addTagString(ItemStack stack, String tag, String value) {
        NBTTagCompound compound = getTagCompound(stack);
        // Set the tag and value
        compound.setString(tag, value);
        stack.setTagCompound(compound);
    }

    /**
     * Adds a new tag with a given integer value to the item stack.
     * 
     * @param stack
     * @param value
     */
    public static void addTagBoolean(ItemStack stack, String tag, boolean value) {
        NBTTagCompound compound = getTagCompound(stack);
        // Set the tag and value
        compound.setBoolean(tag, value);
        stack.setTagCompound(compound);
    }

    /**
     * Adds a new tag with a given integer value to the item stack.
     * 
     * @param stack
     * @param value
     */
    public static void addTagDouble(ItemStack stack, String tag, double value) {
        NBTTagCompound compound = getTagCompound(stack);
        // Set the tag and value
        compound.setDouble(tag, value);
        stack.setTagCompound(compound);
    }

    /**
     * Adds a new tag with a given integer value to the item stack.
     * 
     * @param stack
     * @param value
     */
    public static void addTagFloat(ItemStack stack, String tag, float value) {
        NBTTagCompound compound = getTagCompound(stack);
        // Set the tag and value
        compound.setFloat(tag, value);
        stack.setTagCompound(compound);
    }

    /**
     * Retrieves the value for a given tag from a given item stack.
     * 
     * @param stack
     * @param tag
     */
    public static String getTagString(ItemStack stack, String tag) {
        NBTTagCompound compound = getTagCompound(stack);
        return compound.getString(tag);
    }

    /**
     * Retrieves the value for a given tag from a given item stack.
     * 
     * @param stack
     * @param tag
     */
    public static int getTagInteger(ItemStack stack, String tag) {
        NBTTagCompound compound = getTagCompound(stack);
        return compound.getInteger(tag);
    }

    /**
     * Retrieves the value for a given tag from a given item stack.
     * 
     * @param stack
     * @param tag
     */
    public static boolean getTagBoolean(ItemStack stack, String tag) {
        NBTTagCompound compound = getTagCompound(stack);
        return compound.getBoolean(tag);
    }

    /**
     * Retrieves the value for a given tag from a given item stack.
     * 
     * @param stack
     * @param tag
     */
    public static float getTagFloat(ItemStack stack, String tag) {
        NBTTagCompound compound = getTagCompound(stack);
        return compound.getFloat(tag);
    }

    /**
     * Retrieves the value for a given tag from a given item stack.
     * 
     * @param stack
     * @param tag
     */
    public static double getTagDouble(ItemStack stack, String tag) {
        NBTTagCompound compound = getTagCompound(stack);
        return compound.getDouble(tag);
    }
}
