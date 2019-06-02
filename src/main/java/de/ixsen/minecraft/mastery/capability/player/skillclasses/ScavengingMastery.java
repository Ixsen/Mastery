package de.ixsen.minecraft.mastery.capability.player.skillclasses;

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

    @Override
    public String getBonusDescription() {
        return null;
    }
}
