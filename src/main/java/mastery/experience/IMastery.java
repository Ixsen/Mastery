package mastery.experience;

import java.util.HashMap;

import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClass;

/**
 * Created by Granis on 16/03/2018.
 */
public interface IMastery {
    int[] toIntArray();

    void readIntArray(int[] array);

    void setMasteries(HashMap<MASTERY_SPEC, MasteryClass> masteries);

    HashMap<MASTERY_SPEC, MasteryClass> getMasteries();

}
