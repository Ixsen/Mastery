package mastery.capability.skillclasses;

public class SurvivalMastery extends MasteryClass {

    public SurvivalMastery() {
        this.name = "Survival";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.SURVIVAL;
    }
}
