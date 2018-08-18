package mastery.capability.skillclasses;

public class SurvivalMastery extends MasteryClass {

    public SurvivalMastery() {
        this.name = "Survival";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.SURVIVAL;
    }

    public float getHungerBonus() {
        return (float) Math.min(0.1f, Math.random());
    }

    public float getSaturationBonus() {
        return (float) Math.min(0.1f, Math.random());
    }

    public float getDurationMultiplier() {
        return (float) Math.min(0.1f, Math.random());
    }
}
