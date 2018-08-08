package mastery.capability.skillclasses;

/**
 * Created by Granis on 18/03/2018.
 */
public enum MasterySpec {
    MINING(0), COMBAT(1), ALCHEMY(2), FARMING(3), HUSBANDRY(4), SURVIVAL(5), CRAFTING(6), ATHLETICS(7);
    public int order;

    MasterySpec(int order) {
        this.order = order;
    }

    public static MasterySpec getByOrder(int order) {
        for (MasterySpec masterySpec : MasterySpec.values()) {
            if (masterySpec.order == order) {
                return masterySpec;
            }
        }
        throw new RuntimeException("No Enum value for " + order + " found.");
    }
}
