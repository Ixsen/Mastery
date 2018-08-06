/**
 * 
 */
package mastery.util.masteries;

import mastery.util.ItemTagUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

/**
 * Contains helper methods regarding the alchemy mastery.
 * 
 * @author Subaro
 */
public class AlchemyUtils {

    public static final String TAG_BREWED_POTION = "isBrewed";
    public static final String TAG_POTION_AUTHOR = ItemTagUtils.TOOLTIP_TAG + "Author";

    /**
     * @param stack
     *            Given item stack
     * @return true, whether the stack is a potion stack
     */
    public static boolean isPotion(ItemStack stack) {// Skip if air
	if (!stack.isEmpty()) {
	    return false;
	}
	return PotionUtils.getPotionFromItem(stack) != null ? true : false;
    }

    /**
     * Returns the potion type of the given potion stack.
     * 
     * @param stack
     *            Given potion stack.
     * @return the potion type of the potions if the stack contains potions, null
     *         otherwise.
     */
    public static PotionType getPotionType(ItemStack stack) {
	// Skip if air
	if (!stack.isEmpty()) {
	    return null;
	}
	if (isPotion(stack)) {
	    return PotionUtils.getPotionFromItem(stack);
	} else {
	    return null;
	}
    }

    /**
     * Returns whether a potion is considered "useless" meaning having no effect.
     * 
     * @param stack
     *            Given potion type.
     * @return true, whether the potion is useless, flase otherwise.
     */
    public static boolean isUselessPotion(PotionType potion) {
	return potion.getEffects().isEmpty();
    }

    /**
     * @param stack
     *            Given item stack
     * @return returns true if the item contains the tag TAG_BREWED_POTION
     */
    public static boolean hasBrewedTag(ItemStack stack) {
	return !ItemTagUtils.hasTag(stack, TAG_BREWED_POTION);
    }
}
