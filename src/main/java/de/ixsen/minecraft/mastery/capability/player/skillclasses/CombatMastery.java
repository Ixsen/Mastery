package de.ixsen.minecraft.mastery.capability.player.skillclasses;

/**
 * Created by Granis on 16/03/2018.
 */
public class CombatMastery extends MasteryClass {

    public CombatMastery() {
        this.name = "Combat";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.COMBAT;
    }

    @Override
    public String getBonusDescription() {
        return null;
    }

    public double getAttackModifier() {
        return this.getLevel();
    }

    public float getDamageTaken(float amount) {
        return amount - amount * (float) Math.log(this.getLevel() + 1) / 20.0f;
    }

    public double getHealthModifier() {
        return this.getLevel();
    }

    public double getAttackSpeedModifier() {
        return this.getLevel();
    }
}
