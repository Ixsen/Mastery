package mastery.eventhandlers.husbandry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;

class HusbandryUtils {

    static boolean isShearable(Entity target, Item item) {
        if (target instanceof EntitySheep) {
            EntitySheep sheep = (EntitySheep) target;
            return item == Items.SHEARS && (!sheep.getSheared()) && !sheep.isChild();
        }
        return false;
    }

    static boolean isFeedable(Entity target, EntityPlayer entityPlayer, EnumHand hand) {
        return target.processInitialInteract(entityPlayer, hand) && !((EntityAgeable) target).isChild()
                && !(target instanceof AbstractHorse) && !(target instanceof EntityTameable);
    }
}
