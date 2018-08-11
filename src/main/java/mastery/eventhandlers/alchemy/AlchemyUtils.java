package mastery.eventhandlers.alchemy;

import mastery.util.ItemTagUtils;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

/**
 * Contains helper methods regarding the alchemy mastery.
 * 
 * @author Subaro
 */
class AlchemyUtils {

    static final String TAG_BREWED_POTION = "isBrewed";
    static final String TAG_POTION_AUTHOR = ItemTagUtils.TOOLTIP_TAG + "Author";

    /**
     * @param stack
     *            Given item stack
     * @return true, whether the stack is a potion stack
     */
    static boolean isPotion(ItemStack stack) {// Skip if air
        if (stack.isEmpty()) {
            return false;
        }
        return PotionUtils.getPotionFromItem(stack) != null ? true : false;
    }

    /**
     * @param stack
     *            Given item stack
     * @return true, whether the stack is a potion stack
     */
    static boolean isSplashPotion(ItemStack stack) {// Skip if air
        if (!isPotion(stack)) {
            return false;
        }
        PotionType type = PotionUtils.getPotionFromItem(stack);

        return type != null && stack.getItem() instanceof ItemSplashPotion ? true : false;
    }

    /**
     * Returns the potion type of the given potion stack.
     * 
     * @param stack
     *            Given potion stack.
     * @return the potion type of the potions if the stack contains potions, null otherwise.
     */
    static PotionType getPotionType(ItemStack stack) {
        return PotionUtils.getPotionFromItem(stack);
    }

    /**
     * Returns whether a potion is considered "useless" meaning having no effect.
     * 
     * Given potion type.
     * 
     * @return true, whether the potion is useless, flase otherwise.
     */
    static boolean isUselessPotion(PotionType potion) {
        return potion.getEffects().isEmpty();
    }

    /**
     * @param stack
     *            Given item stack
     * @return returns true if the item contains the tag TAG_BREWED_POTION
     */
    static boolean hasBrewedTag(ItemStack stack) {
        return !ItemTagUtils.hasTag(stack, TAG_BREWED_POTION);
    }
}
