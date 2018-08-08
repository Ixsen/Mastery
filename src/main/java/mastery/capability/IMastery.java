package mastery.capability;

import mastery.capability.skillclasses.MasteryClass;
import mastery.capability.skillclasses.MasterySpec;

import java.util.HashMap;

/**
 * Created by Granis on 16/03/2018.
 */
public interface IMastery {
    int[] toIntArray();

    void readIntArray(int[] array);

    void setMasteries(HashMap<MasterySpec, MasteryClass> masteries);

    HashMap<MasterySpec, MasteryClass> getMasteries();

}
