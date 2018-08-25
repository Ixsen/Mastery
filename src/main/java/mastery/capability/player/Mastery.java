package mastery.capability.player;

import java.util.HashMap;

import mastery.capability.player.skillclasses.AlchemyMastery;
import mastery.capability.player.skillclasses.AthleticsMastery;
import mastery.capability.player.skillclasses.CombatMastery;
import mastery.capability.player.skillclasses.CraftingMastery;
import mastery.capability.player.skillclasses.FarmingMastery;
import mastery.capability.player.skillclasses.FishingMastery;
import mastery.capability.player.skillclasses.HusbandryMastery;
import mastery.capability.player.skillclasses.MasteryClass;
import mastery.capability.player.skillclasses.MasterySpec;
import mastery.capability.player.skillclasses.MiningMastery;
import mastery.capability.player.skillclasses.ScavengingMastery;
import mastery.capability.player.skillclasses.SneakingMastery;
import mastery.capability.player.skillclasses.SurvivalMastery;
import mastery.capability.player.skillclasses.TradingMastery;

/**
 * Created by Granis on 16/03/2018.
 */
public class Mastery implements IMastery {

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
        this.masteryClasses.put(MasterySpec.TRADING, new TradingMastery());
        this.masteryClasses.put(MasterySpec.SNEAKING, new SneakingMastery());
        this.masteryClasses.put(MasterySpec.SCAVENGING, new ScavengingMastery());
        this.masteryClasses.put(MasterySpec.FISHING, new FishingMastery());
    }

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
    public HashMap<MasterySpec, MasteryClass> getMasteries() {
        return this.masteryClasses;
    }

    @Override
    public void setMasteries(HashMap<MasterySpec, MasteryClass> masteries) {
        this.masteryClasses = masteries;
    }

    @Override
    public MasteryClass getMasteryClass(MasterySpec spec) {
        return this.masteryClasses.get(spec);
    }
}
