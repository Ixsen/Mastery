package mastery.experience.skillclasses;

public class SurvivalMastery extends MasteryClass {

    public SurvivalMastery() {
	this.name = "Survival";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
	return MASTERY_SPEC.SURVIVAL;
    }
}
