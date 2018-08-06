package mastery.experience.skillclasses;

import net.minecraft.entity.EntityLivingBase;

/**
 * Created by Granis on 16/03/2018.
 */
public class CombatMastery extends MasteryClass {

    public enum EXP_TYPE {
        ENTITY_DAMAGED,
        PLAYER_DAMAGED;
    }

    public static final double doDamageExpThreshold = 1.0f;
    public static final float getDamagedExpThreshold = 1.0f;

    public CombatMastery() {
        this.name = "Combat";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.COMBAT;
    }

    public float getAttackDamageEffect(float originalDamage) {
        return originalDamage + originalDamage * (float) Math.log(this.getLevel() + 1) / 10.0f;
    }

    public float getDefenseDamageEffect(float originalDamage) {
        return originalDamage - originalDamage * (float) Math.log(this.getLevel() + 1) / 20.0f;
    }

    /**
     * Increases the exp relative to the entity slain
     *
     * @param entity --
     */
    public void increaseExperience_EntitySlain(EntityLivingBase entity) {
        this.increaseExperience(Math.round(entity.getMaxHealth()));
    }

    /**
     * Increases the experience relative to the damage done or taken
     *
     * @param amount --
     * @param type   --
     */
    public void increaseExperience(float amount, EXP_TYPE type) {
        switch (type) {
            case ENTITY_DAMAGED:
                this.increaseExperience();
                break;
            case PLAYER_DAMAGED:
                this.increaseExperience();
                break;
        }
    }
}