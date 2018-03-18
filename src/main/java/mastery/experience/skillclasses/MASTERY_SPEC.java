package mastery.experience.skillclasses;

/**
 * Created by Granis on 18/03/2018.
 */
public enum MASTERY_SPEC {
    //TODO reorganize, atm ENUM order should not be changed
    MINING(0),
    COMBAT(1);
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
