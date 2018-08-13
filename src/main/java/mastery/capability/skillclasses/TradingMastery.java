package mastery.capability.skillclasses;

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

}
