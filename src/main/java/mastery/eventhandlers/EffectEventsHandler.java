package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.CombatMastery;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MiningMastery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EffectEventsHandler {

    @SubscribeEvent
    public void harvestBlocks(BlockEvent.HarvestDropsEvent harvestEvent) { // TODO MINING and FARMING: More drops here
        if (harvestEvent.getDrops().size() > 0 && harvestEvent.getHarvester() != null) {
            IMastery mastery = harvestEvent.getHarvester().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
            int level = mastery.getMasteries().get(MASTERY_SPEC.MINING).getLevel();
            harvestEvent.getDrops().get(0).setCount(harvestEvent.getDrops().get(0).getCount() + level);
        }
    }

    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent babyEntitySpawnEvent) { // TODO HUSBANDRY -> maybe twins or shorter grow time

    }

    @SubscribeEvent
    public void entityDropsItems(LivingDropsEvent dropsEvent) { // TODO HUSBANDRY -> drops evtl scavanging

    }

    @SubscribeEvent
    public void fallingDamage(LivingFallEvent fallEvent) { // TODO SURVIVAL reduce fall or negate if something is done!

    }

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent livingHurtEvent) { // TODO COMBAT (hitting, getting hit), SURVIVAL (falling damage)
        if (livingHurtEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            CombatMastery mastery = (CombatMastery) livingHurtEvent.getSource().getTrueSource().getCapability(MasteryProvider.MASTERY_CAPABILITY, null).getMasteries().get(MASTERY_SPEC.COMBAT);
            livingHurtEvent.setAmount(mastery.getAttackDamageEffect(livingHurtEvent.getAmount()));
//            livingHurtEvent.getSource().getTrueSource().sendMessage(new TextComponentString("dmg: " + livingHurtEvent.getAmount()));
        } else if (livingHurtEvent.getEntity() instanceof EntityPlayer && livingHurtEvent.getSource().getTrueSource() != null) {
            CombatMastery mastery = (CombatMastery) livingHurtEvent.getEntity().getCapability(MasteryProvider.MASTERY_CAPABILITY, null).getMasteries().get(MASTERY_SPEC.COMBAT);
            livingHurtEvent.setAmount(mastery.getDefenseDamageEffect(livingHurtEvent.getAmount()));
//            livingHurtEvent.getEntity().sendMessage(new TextComponentString("dmg: " + livingHurtEvent.getAmount()));
        }
    }

    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed breakingSpeed) {
        IMastery mastery = breakingSpeed.getEntityPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
        MiningMastery miningMastery = (MiningMastery) mastery.getMasteries().get(MASTERY_SPEC.MINING);
        float newSpeed = miningMastery.getMiningSpeed(breakingSpeed.getOriginalSpeed());
        breakingSpeed.setNewSpeed(newSpeed);
    }

}
