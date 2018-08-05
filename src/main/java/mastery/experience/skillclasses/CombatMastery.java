package mastery.experience.skillclasses;

/**
 * Created by Granis on 16/03/2018.
 */
public class CombatMastery extends MasteryClass {
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
}
