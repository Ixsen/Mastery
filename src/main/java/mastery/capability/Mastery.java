package mastery.capability;

import mastery.capability.skillclasses.AlchemyMastery;
import mastery.capability.skillclasses.AthleticsMastery;
import mastery.capability.skillclasses.CombatMastery;
import mastery.capability.skillclasses.CraftingMastery;
import mastery.capability.skillclasses.FarmingMastery;
import mastery.capability.skillclasses.HusbandryMastery;
import mastery.capability.skillclasses.MasteryClass;
import mastery.capability.skillclasses.MasterySpec;
import mastery.capability.skillclasses.MiningMastery;
import mastery.capability.skillclasses.SurvivalMastery;

import java.util.HashMap;

/**
 * Created by Granis on 16/03/2018.
 */
public class Mastery implements IMastery {

    @Override
    public int[] toIntArray() {
        int[] intArray = new int[MasterySpec.values().length];
        for (int i = 0; i < MasterySpec.values().length; i++) {
            intArray[i] = this.masteryClasses.get(MasterySpec.getByOrder(i)).getExperience();
        }
        return intArray;
    }

    @Override
    public void readIntArray(int[] array) {
        for (int i = 0; i < MasterySpec.values().length; i++) {
            MasteryClass masteryClass = this.masteryClasses.get(MasterySpec.getByOrder(i));
            masteryClass.setExperience(array[i]);
        }
    }

    @Override
    public void setMasteries(HashMap<MasterySpec, MasteryClass> masteries) {
        this.masteryClasses = masteries;
    }

    @Override
    public HashMap<MasterySpec, MasteryClass> getMasteries() {
        return this.masteryClasses;
    }

    private HashMap<MasterySpec, MasteryClass> masteryClasses = new HashMap<>();

    public Mastery() {
        this.masteryClasses.put(MasterySpec.MINING, new MiningMastery());
        this.masteryClasses.put(MasterySpec.COMBAT, new CombatMastery());
        this.masteryClasses.put(MasterySpec.ALCHEMY, new AlchemyMastery());
        this.masteryClasses.put(MasterySpec.FARMING, new FarmingMastery());
        this.masteryClasses.put(MasterySpec.HUSBANDRY, new HusbandryMastery());
        this.masteryClasses.put(MasterySpec.SURVIVAL, new SurvivalMastery());
        this.masteryClasses.put(MasterySpec.CRAFTING, new CraftingMastery());
        this.masteryClasses.put(MasterySpec.ATHLETICS, new AthleticsMastery());
    }

}
