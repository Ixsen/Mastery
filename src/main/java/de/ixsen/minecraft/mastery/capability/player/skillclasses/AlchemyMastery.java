package de.ixsen.minecraft.mastery.capability.player.skillclasses;

public class AlchemyMastery extends MasteryClass {

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.ALCHEMY;
    }

    @Override
    public String getBonusDescription() {
        return "--";
    }

    public AlchemyMastery() {
        this.name = "Alchemy";
    }
}
