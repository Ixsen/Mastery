package mastery.capability.skillclasses;

/**
 * Created by Granis on 16/03/2018.
 */
public class MiningMastery extends MasteryClass {

    public static double APPLY_ENDLESS_FORTUNE_EFFECT = 0.001f; // 100% at level 1000
    public static double LEVEL_DIVISOR_FOR_EXTRA_ITEMS = 100; // Chance to get 12 extra items at level 1000

    public MiningMastery() {
        this.name = "Mining";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.MINING;
    }

    public float getMiningSpeed(float originalSpeed) {
        float miningSpeed = originalSpeed * this.getLevel();
        if (miningSpeed == 0.0f) {
            miningSpeed = 1.0f;
        }
        return miningSpeed;
    }

    public int getExtraDropIfLucky() {
        if (Math.random() <= Math.min(1, APPLY_ENDLESS_FORTUNE_EFFECT * this.getLevel())) {
            // Drop additionally items depending on the returned value
            return (int) (Math.random() * (2 + (int) (this.getLevel() / LEVEL_DIVISOR_FOR_EXTRA_ITEMS)));
        }
        return 0;
    }
}
