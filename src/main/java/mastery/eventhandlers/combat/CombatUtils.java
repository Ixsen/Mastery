package mastery.eventhandlers.combat;

import net.minecraft.entity.EntityLivingBase;

class CombatUtils {
    static int calculateMurderExperience(EntityLivingBase entity) {
        return Math.round(entity.getMaxHealth());
    }
}
