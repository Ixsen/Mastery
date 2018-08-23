package mastery.capability.player;

import java.util.HashMap;

import mastery.capability.skillclasses.MasteryClass;
import mastery.capability.skillclasses.MasterySpec;

/**
 * Created by Granis on 16/03/2018.
 */
public interface IMastery {
    int[] toIntArray();

    void readIntArray(int[] array);

    MasteryClass getMasteryClass(MasterySpec spec);

    HashMap<MasterySpec, MasteryClass> getMasteries();

    void setMasteries(HashMap<MasterySpec, MasteryClass> masteries);

}
