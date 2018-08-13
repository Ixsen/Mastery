package mastery.capability.skillclasses;

/**
 * @author Subaro
 */
public class ScavengingMastery extends MasteryClass {

    public ScavengingMastery() {
        this.name = "Scavenging";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.SCAVENGING;
    }
}
