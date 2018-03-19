package mastery.experience.skillclasses;

/**
 * Created by Granis on 16/03/2018.
 */
public class MiningMastery extends MasteryClasses {

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.MINING;
    }

    public float getMiningSpeed(float originalSpeed) {
        float miningSpeed =  originalSpeed * getLevel();
        if(miningSpeed == 0.0f) {
            miningSpeed = 1.0f;
        }
        return miningSpeed;
    }
}
