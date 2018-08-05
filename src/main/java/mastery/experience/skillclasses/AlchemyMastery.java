package mastery.experience.skillclasses;

public class AlchemyMastery extends MasteryClass {

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.ALCHEMY;
    }

    public AlchemyMastery() {
        this.name = "Alchemy";
    }
}
