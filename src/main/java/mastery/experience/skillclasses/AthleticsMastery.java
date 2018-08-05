package mastery.experience.skillclasses;

public class AthleticsMastery extends MasteryClass {

    public AthleticsMastery() {
        this.name = "Athletics";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.ATHLETICS;
    }
}
