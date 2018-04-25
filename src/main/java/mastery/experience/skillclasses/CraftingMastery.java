package mastery.experience.skillclasses;

public class CraftingMastery extends MasteryClasses {
    public CraftingMastery() {
        name = "Crafting";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.CRAFTING;
    }



}
