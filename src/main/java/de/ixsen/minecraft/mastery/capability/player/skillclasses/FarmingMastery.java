package de.ixsen.minecraft.mastery.capability.player.skillclasses;

public class FarmingMastery extends MasteryClass {

    public static final float DOUBLE_CHANCE_PER_LEVEL = 0.01f;
    public static final float QUAD_CHANCE_PER_LEVEL = 0.001f;

    public FarmingMastery() {
        this.name = "Farming";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.FARMING;
    }

    @Override
    public String getBonusDescription() {
        return null;
    }

    public boolean applyQuadDrop() {
        return Math.random() <= Math.min(this.getLevel() * FarmingMastery.QUAD_CHANCE_PER_LEVEL, 1);
    }

    public boolean applyDoubleDrop() {
        return Math.random() <= Math.min(this.getLevel() * FarmingMastery.DOUBLE_CHANCE_PER_LEVEL, 1);
    }
}
