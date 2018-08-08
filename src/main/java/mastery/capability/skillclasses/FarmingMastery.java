package mastery.capability.skillclasses;

public class FarmingMastery extends MasteryClass {

    public FarmingMastery() {
        this.name = "Farming";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.FARMING;
    }
}
