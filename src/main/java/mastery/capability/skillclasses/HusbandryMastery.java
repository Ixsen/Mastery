package mastery.capability.skillclasses;

public class HusbandryMastery extends MasteryClass {

    public HusbandryMastery() {
        this.name = "Husbandry";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.HUSBANDRY;
    }
}
