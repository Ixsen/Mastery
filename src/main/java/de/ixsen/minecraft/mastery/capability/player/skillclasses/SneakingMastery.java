package de.ixsen.minecraft.mastery.capability.player.skillclasses;

/**
 * TODO description
 * 
 * @author Subaro
 */
public class SneakingMastery extends MasteryClass {

    public SneakingMastery() {
        this.name = "Sneaking";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.SNEAKING;
    }
}
