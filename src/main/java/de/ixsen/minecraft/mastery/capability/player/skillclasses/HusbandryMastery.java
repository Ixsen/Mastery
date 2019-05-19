package de.ixsen.minecraft.mastery.capability.player.skillclasses;

public class HusbandryMastery extends MasteryClass {

    public static final double TWIN_CHANCE = 0.01;
    public static final float MIN_GROW_MULTIPLIER = 0.1f;
    public static final double BASE_TAME_CHANCE = 0.1f;

    public HusbandryMastery() {
        this.name = "Husbandry";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.HUSBANDRY;
    }

    public boolean willSpawnTwins() {
        return Math.random() <= Math.min(TWIN_CHANCE * this.getLevel(), 1);
    }

    public float getGrowingMultiplier() {
        return Math.min(1f / this.getLevel(), MIN_GROW_MULTIPLIER);
    }

    public boolean willBeTamed() {
        return Math.random() < Math.min(0.7, BASE_TAME_CHANCE * this.getLevel());
    }

    public double getPetDamageModifier() {
        return this.getLevel();
    }

    public double getPetMovementModifier() {
        return this.getLevel();
    }

    public double getPetHealthModifier() {
        return this.getLevel();
    }

}
