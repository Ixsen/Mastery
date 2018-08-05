package mastery.experience.skillclasses;

public class CraftingMastery extends MasteryClass {
    public CraftingMastery() {
        this.name = "Crafting";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.CRAFTING;
    }
}
