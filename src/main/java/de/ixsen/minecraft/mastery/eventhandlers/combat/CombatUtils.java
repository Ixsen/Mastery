package de.ixsen.minecraft.mastery.eventhandlers.combat;

import net.minecraft.entity.EntityLivingBase;

class CombatUtils {
    static final double doDamageExpThreshold = 1.0f;
    static final float getDamagedExpThreshold = 1.0f;

    static int calculateMurderExperience(EntityLivingBase entity) {
        return Math.round(entity.getMaxHealth());
    }
}
