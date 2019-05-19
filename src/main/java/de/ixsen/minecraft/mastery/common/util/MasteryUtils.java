package de.ixsen.minecraft.mastery.common.util;

import de.ixsen.minecraft.mastery.capability.player.IMastery;
import de.ixsen.minecraft.mastery.capability.player.MasteryProvider;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.AlchemyMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.AthleticsMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.CombatMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.CraftingMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.FarmingMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.FishingMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.HusbandryMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MiningMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.ScavengingMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.SneakingMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.SurvivalMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.TradingMastery;
import de.ixsen.minecraft.mastery.capability.world.BlockInfoProvider;
import de.ixsen.minecraft.mastery.capability.world.IBlockInfo;
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
