package mastery.experience.skillclasses;

public class AthleticsMastery extends MasteryClasses {

    public AthleticsMastery() {
        name = "Athletics";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.ATHLETICS;
    }
}
