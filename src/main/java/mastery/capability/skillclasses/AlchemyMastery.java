package mastery.capability.skillclasses;

public class AlchemyMastery extends MasteryClass {

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.ALCHEMY;
    }

    public AlchemyMastery() {
        this.name = "Alchemy";
    }
}
