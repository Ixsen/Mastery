package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MiningMastery;
import net.minecraft.util.text.TextComponentString;
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
            int exp = mastery.getMasteries().get(MASTERY_SPEC.MINING).getExperience();
            harvestEvent.getHarvester().sendMessage(new TextComponentString("Exp: " + exp + " Level: " + level));
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
    public void getHit(LivingHurtEvent getHitEvent) { // TODO COMBAT (hitting, getting hit), SURVIVAL (falling damage)

    }

    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed breakingSpeed) {
        IMastery mastery = breakingSpeed.getEntityPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
        MiningMastery miningMastery = (MiningMastery) mastery.getMasteries().get(MASTERY_SPEC.MINING);
        float newSpeed = miningMastery.getMiningSpeed(breakingSpeed.getOriginalSpeed());
        breakingSpeed.setNewSpeed(newSpeed);
    }

}
