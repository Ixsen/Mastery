package mastery.experience.skillclasses;

/**
 * Created by Granis on 16/03/2018.
 */
public class CombatMastery extends MasteryClasses {

    public CombatMastery() {
        name = "Combat";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.COMBAT;
    }
}
