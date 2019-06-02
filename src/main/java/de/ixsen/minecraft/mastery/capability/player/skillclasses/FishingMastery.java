package de.ixsen.minecraft.mastery.capability.player.skillclasses;

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

    @Override
    public String getBonusDescription() {
        return null;
    }
}
