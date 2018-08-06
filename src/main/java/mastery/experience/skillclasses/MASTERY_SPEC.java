package mastery.experience.skillclasses;

/**
 * Created by Granis on 18/03/2018.
 */
public enum MASTERY_SPEC {
    MINING(0), COMBAT(1), ALCHEMY(2), FARMING(3), HUSBANDRY(4), SURVIVAL(5), CRAFTING(6), ATHLETICS(7);
    public int order;

    MASTERY_SPEC(int order) {
        this.order = order;
    }

    public static MASTERY_SPEC getByOrder(int order) {
        for (MASTERY_SPEC mastery_spec : MASTERY_SPEC.values()) {
            if (mastery_spec.order == order) {
                return mastery_spec;
            }
        }
        throw new RuntimeException("No Enum value for " + order + " found.");
    }
}
