package mastery.experience;

import mastery.experience.skillclasses.*;

import java.util.HashMap;

/**
 * Created by Granis on 16/03/2018.
 */
public class Mastery implements IMastery {

    @Override
    public int[] toIntArray() {
        int[] intArray = new int[MASTERY_SPEC.values().length];
        for (int i = 0; i < MASTERY_SPEC.values().length; i++) {
            intArray[i] = masteryClasses.get(MASTERY_SPEC.getByOrder(i)).getExperience();
        }
        return intArray;
    }

    @Override
    public void readIntArray(int[] array) {
        for (int i = 0; i < MASTERY_SPEC.values().length; i++) {
            MasteryClasses masteryClass = this.masteryClasses.get(MASTERY_SPEC.getByOrder(i));
            masteryClass.setExperience(array[i]);
        }
    }

    @Override
    public void setMasteries(HashMap<MASTERY_SPEC, MasteryClasses> masteries) {
        this.masteryClasses = masteries;
    }

    @Override
    public HashMap<MASTERY_SPEC, MasteryClasses> getMasteries() {
        return this.masteryClasses;
    }

    private HashMap<MASTERY_SPEC, MasteryClasses> masteryClasses = new HashMap<>();

    public Mastery() {
        masteryClasses.put(MASTERY_SPEC.MINING, new MiningMastery());
        masteryClasses.put(MASTERY_SPEC.COMBAT, new CombatMastery());
        masteryClasses.put(MASTERY_SPEC.ALCHEMY, new AlchemyMastery());
        masteryClasses.put(MASTERY_SPEC.FARMING, new FarmingMastery());
        masteryClasses.put(MASTERY_SPEC.HUSBANDRY, new HusbandryMastery());
        masteryClasses.put(MASTERY_SPEC.SURVIVAL, new SurvivalMastery());
        masteryClasses.put(MASTERY_SPEC.CRAFTING, new CraftingMastery());
        masteryClasses.put(MASTERY_SPEC.ATHLETICS, new AthleticsMastery());
    }

}
