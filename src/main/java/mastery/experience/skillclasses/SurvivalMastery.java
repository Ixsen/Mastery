package mastery.experience.skillclasses;

public class SurvivalMastery extends MasteryClasses {

    public SurvivalMastery() {
        name = "Survival";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.SURVIVAL;
    }
}
