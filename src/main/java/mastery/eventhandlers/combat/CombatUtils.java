package mastery.eventhandlers.combat;

import net.minecraft.entity.EntityLivingBase;

public class CombatUtils {
    public static int calculateMurderExperience(EntityLivingBase entity) {
        return Math.round(entity.getMaxHealth());
    }
}
