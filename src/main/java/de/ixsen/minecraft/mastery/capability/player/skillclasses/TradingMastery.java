package de.ixsen.minecraft.mastery.capability.player.skillclasses;

/**
 * @author Subaro
 */
public class TradingMastery extends MasteryClass {

    public TradingMastery() {
        this.name = "Trading";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.TRADING;
    }

    @Override
    public String getBonusDescription() {
        return null;
    }

}
