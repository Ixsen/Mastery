package mastery.capability.skillclasses;

public class AthleticsMastery extends MasteryClass {

    public AthleticsMastery() {
        this.name = "Athletics";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.ATHLETICS;
    }
}
