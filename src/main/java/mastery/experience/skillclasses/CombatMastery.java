package mastery.experience.skillclasses;

/**
 * Created by Granis on 16/03/2018.
 */
public class CombatMastery extends MasteryClasses {

    public CombatMastery() {
        name = "Combat";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.COMBAT;
    }

    public float getAttackDamageEffect(float originalDamage) {
        return originalDamage + originalDamage * (float) Math.log(getLevel()+1) / 10;
    }

    public float getDefenseDamageEffect(float originalDamage) {
        return originalDamage - originalDamage * (float) Math.log(getLevel()+1) / 20;
    }
}
