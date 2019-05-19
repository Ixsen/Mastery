package de.ixsen.minecraft.mastery.capability.player.skillclasses;

public class AthleticsMastery extends MasteryClass {

    public AthleticsMastery() {
        this.name = "Athletics";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.ATHLETICS;
    }

    public double getSpeedModifier() {
        return 0.1D;
    }

    public double getSwimModifier() {
        return this.getSpeedModifier();
    }

    public double getJumpModifier() {
        return Math.min(this.getLevel(), 1D);
    }

}
