package mastery.util;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
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
import net.minecraft.entity.Entity;

/**
 * Gives utitlity methods for getting materies. Does not check whether or not
 * the giving entity even has masteries
 */
public class MasteryUtils {

    public static IMastery getMasteries(Entity player) {
        return player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
    }

    public static MasteryClass getMastery(Entity player, MASTERY_SPEC spec) {
        return getMasteries(player).getMasteries().get(spec);

    }

    public static AlchemyMastery getAlchemyMastery(Entity player) {
        return (AlchemyMastery) getMastery(player, MASTERY_SPEC.ALCHEMY);
    }

    public static AthleticsMastery getAthleticsMastery(Entity player) {
        return (AthleticsMastery) getMastery(player, MASTERY_SPEC.ATHLETICS);
    }

    public static CombatMastery getCombatMastery(Entity player) {
        return (CombatMastery) getMastery(player, MASTERY_SPEC.COMBAT);
    }

    public static CraftingMastery getCraftingMastery(Entity player) {
        return (CraftingMastery) getMastery(player, MASTERY_SPEC.CRAFTING);
    }

    public static FarmingMastery getFarmingMastery(Entity player) {
        return (FarmingMastery) getMastery(player, MASTERY_SPEC.FARMING);
    }

    public static HusbandryMastery getHusbandryMastery(Entity player) {
        return (HusbandryMastery) getMastery(player, MASTERY_SPEC.HUSBANDRY);
    }

    public static MiningMastery getMiningMastery(Entity player) {
        return (MiningMastery) getMastery(player, MASTERY_SPEC.MINING);
    }

    public static SurvivalMastery getSurvivalMastery(Entity player) {
        return (SurvivalMastery) getMastery(player, MASTERY_SPEC.SURVIVAL);
    }

}
