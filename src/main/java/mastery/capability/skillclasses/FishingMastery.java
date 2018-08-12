package mastery.capability.skillclasses;

/**
 * @author Subaro
 */
public class FishingMastery extends MasteryClass {

    public FishingMastery() {
        this.name = "Fishing";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.FISHING;
    }
}
