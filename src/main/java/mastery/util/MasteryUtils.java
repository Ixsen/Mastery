package mastery.util;

import mastery.capability.player.IMastery;
import mastery.capability.player.MasteryProvider;
import mastery.capability.skillclasses.AlchemyMastery;
import mastery.capability.skillclasses.AthleticsMastery;
import mastery.capability.skillclasses.CombatMastery;
import mastery.capability.skillclasses.CraftingMastery;
import mastery.capability.skillclasses.FarmingMastery;
import mastery.capability.skillclasses.FishingMastery;
import mastery.capability.skillclasses.HusbandryMastery;
import mastery.capability.skillclasses.MasteryClass;
import mastery.capability.skillclasses.MasterySpec;
import mastery.capability.skillclasses.MiningMastery;
import mastery.capability.skillclasses.ScavengingMastery;
import mastery.capability.skillclasses.SneakingMastery;
import mastery.capability.skillclasses.SurvivalMastery;
import mastery.capability.skillclasses.TradingMastery;
import mastery.capability.world.BlockInfoProvider;
import mastery.capability.world.IBlockInfo;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * Gives utitlity methods for getting materies. Does not check whether or not the giving entity even has masteries
 */
public class MasteryUtils {

    public static IBlockInfo getBlockInfoMastery(World world) {
        return world.getCapability(BlockInfoProvider.BLOCK_INFO_CAPABILITY, null);
    }

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

    public static TradingMastery getTradingMastery(Entity player) {
        return (TradingMastery) getMastery(player, MasterySpec.TRADING);
    }

    public static SneakingMastery getSneakingMastery(Entity player) {
        return (SneakingMastery) getMastery(player, MasterySpec.SNEAKING);
    }

    public static ScavengingMastery getScavengingMastery(Entity player) {
        return (ScavengingMastery) getMastery(player, MasterySpec.SCAVENGING);
    }

    public static FishingMastery getFishingMastery(Entity player) {
        return (FishingMastery) getMastery(player, MasterySpec.FISHING);
    }

}
