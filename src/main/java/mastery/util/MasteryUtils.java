package mastery.util;

import mastery.capability.IMastery;
import mastery.capability.MasteryProvider;
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
import net.minecraft.entity.Entity;

/**
 * Gives utitlity methods for getting materies. Does not check whether or not the giving entity even has masteries
 */
public class MasteryUtils {

    public static IMastery getMasteries(Entity player) {
        return player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
    }

    public static MasteryClass getMastery(Entity player, MasterySpec spec) {
        return getMasteries(player).getMasteries().get(spec);

    }

    public static AlchemyMastery getAlchemyMastery(Entity player) {
        return (AlchemyMastery) getMastery(player, MasterySpec.ALCHEMY);
    }

    public static AthleticsMastery getAthleticsMastery(Entity player) {
        return (AthleticsMastery) getMastery(player, MasterySpec.ATHLETICS);
    }

    public static CombatMastery getCombatMastery(Entity player) {
        return (CombatMastery) getMastery(player, MasterySpec.COMBAT);
    }

    public static CraftingMastery getCraftingMastery(Entity player) {
        return (CraftingMastery) getMastery(player, MasterySpec.CRAFTING);
    }

    public static FarmingMastery getFarmingMastery(Entity player) {
        return (FarmingMastery) getMastery(player, MasterySpec.FARMING);
    }

    public static HusbandryMastery getHusbandryMastery(Entity player) {
        return (HusbandryMastery) getMastery(player, MasterySpec.HUSBANDRY);
    }

    public static MiningMastery getMiningMastery(Entity player) {
        return (MiningMastery) getMastery(player, MasterySpec.MINING);
    }

    public static SurvivalMastery getSurvivalMastery(Entity player) {
        return (SurvivalMastery) getMastery(player, MasterySpec.SURVIVAL);
    }

}
