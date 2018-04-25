package mastery.experience;

import java.util.HashMap;

import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClasses;

/**
 * Created by Granis on 16/03/2018.
 */
public interface IMastery {
    int[] toIntArray();

    void readIntArray(int[] array);

    void setMasteries(HashMap<MASTERY_SPEC, MasteryClasses> masteries);

    HashMap<MASTERY_SPEC, MasteryClasses> getMasteries();

}
