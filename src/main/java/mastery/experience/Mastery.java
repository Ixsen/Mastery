package mastery.experience;

import java.util.HashMap;

import mastery.experience.skillclasses.AlchemyMastery;
import mastery.experience.skillclasses.AthleticsMastery;
import mastery.experience.skillclasses.CombatMastery;
import mastery.experience.skillclasses.CraftingMastery;
import mastery.experience.skillclasses.FarmingMastery;
import mastery.experience.skillclasses.HusbandryMastery;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClass;
import mastery.experience.skillclasses.MiningMastery;
import mastery.experience.skillclasses.SurvivalMastery;

/**
 * Created by Granis on 16/03/2018.
 */
public class Mastery implements IMastery {

    @Override
    public int[] toIntArray() {
        int[] intArray = new int[MASTERY_SPEC.values().length];
        for (int i = 0; i < MASTERY_SPEC.values().length; i++) {
            intArray[i] = this.masteryClasses.get(MASTERY_SPEC.getByOrder(i)).getExperience();
        }
        return intArray;
    }

    @Override
    public void readIntArray(int[] array) {
        for (int i = 0; i < MASTERY_SPEC.values().length; i++) {
            MasteryClass masteryClass = this.masteryClasses.get(MASTERY_SPEC.getByOrder(i));
            masteryClass.setExperience(array[i]);
        }
    }

    @Override
    public void setMasteries(HashMap<MASTERY_SPEC, MasteryClass> masteries) {
        this.masteryClasses = masteries;
    }

    @Override
    public HashMap<MASTERY_SPEC, MasteryClass> getMasteries() {
        return this.masteryClasses;
    }

    private HashMap<MASTERY_SPEC, MasteryClass> masteryClasses = new HashMap<>();

    public Mastery() {
        this.masteryClasses.put(MASTERY_SPEC.MINING, new MiningMastery());
        this.masteryClasses.put(MASTERY_SPEC.COMBAT, new CombatMastery());
        this.masteryClasses.put(MASTERY_SPEC.ALCHEMY, new AlchemyMastery());
        this.masteryClasses.put(MASTERY_SPEC.FARMING, new FarmingMastery());
        this.masteryClasses.put(MASTERY_SPEC.HUSBANDRY, new HusbandryMastery());
        this.masteryClasses.put(MASTERY_SPEC.SURVIVAL, new SurvivalMastery());
        this.masteryClasses.put(MASTERY_SPEC.CRAFTING, new CraftingMastery());
        this.masteryClasses.put(MASTERY_SPEC.ATHLETICS, new AthleticsMastery());
    }

}
