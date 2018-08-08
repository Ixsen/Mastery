package mastery.capability.skillclasses;

public class CraftingMastery extends MasteryClass {
    public CraftingMastery() {
        this.name = "Crafting";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.CRAFTING;
    }
}
