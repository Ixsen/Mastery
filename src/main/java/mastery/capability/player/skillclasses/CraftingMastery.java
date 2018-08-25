package mastery.capability.player.skillclasses;

public class CraftingMastery extends MasteryClass {

    public static final double DOUBLE_CRAFT_CHANCE = 0.001;

    public CraftingMastery() {
        this.name = "Crafting";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.CRAFTING;
    }

    public boolean isDoubleCraft() {
        return Math.random() <= Math.min(DOUBLE_CRAFT_CHANCE * this.getLevel(), 1);
    }
}
