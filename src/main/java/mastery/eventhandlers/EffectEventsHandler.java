package mastery.eventhandlers;

import mastery.capability.skillclasses.CombatMastery;
import mastery.capability.skillclasses.CraftingMastery;
import mastery.capability.skillclasses.MiningMastery;
import mastery.util.MasteryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EffectEventsHandler {

    /**
     * TODO MINING and FARMING: More drops here
     *
     * @param harvestEvent
     *            --
     */
    @SubscribeEvent
    public void harvestBlocks(BlockEvent.HarvestDropsEvent harvestEvent) {
        if (harvestEvent.getDrops().size() > 0 && harvestEvent.getHarvester() != null) {
            int level = MasteryUtils.getMiningMastery(harvestEvent.getHarvester()).getLevel();
            harvestEvent.getDrops().get(0).setCount(harvestEvent.getDrops().get(0).getCount() + level);
        }
    }

    /**
     * TODO HUSBANDRY -> maybe twins or shorter grow time
     *
     * @param babyEntitySpawnEvent
     *            --
     */
    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent babyEntitySpawnEvent) {

    }

    /**
     * TODO HUSBANDRY -> drops evtl scavanging
     *
     * @param dropsEvent
     *            --
     */
    @SubscribeEvent
    public void entityDropsItems(LivingDropsEvent dropsEvent) {

    }

    /**
     * TODO SURVIVAL reduce fall or negate if something is done!
     *
     * @param fallEvent
     *            --
     */
    @SubscribeEvent
    public void fallingDamage(LivingFallEvent fallEvent) {

    }

    /**
     * TODO COMBAT (hitting, getting hit), SURVIVAL (falling damage)
     *
     * @param livingHurtEvent
     *            --
     */
    @SubscribeEvent
    public void livingHurt(LivingHurtEvent livingHurtEvent) {
        if (livingHurtEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            CombatMastery mastery = MasteryUtils.getCombatMastery(livingHurtEvent.getSource().getTrueSource());
            float newDamage = mastery.getAttackDamageEffect(livingHurtEvent.getAmount());
            livingHurtEvent.setAmount(newDamage);
        } else if (livingHurtEvent.getEntity() instanceof EntityPlayer
                && livingHurtEvent.getSource().getTrueSource() != null) {
            CombatMastery mastery = MasteryUtils.getCombatMastery(livingHurtEvent.getEntity());
            float newDamage = mastery.getDefenseDamageEffect(livingHurtEvent.getAmount());
            livingHurtEvent.setAmount(newDamage);
        }
    }

    /**
     * Mining speed
     *
     * @param breakingSpeed
     *            --
     */
    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed breakingSpeed) {
        MiningMastery miningMastery = MasteryUtils.getMiningMastery(breakingSpeed.getEntityPlayer());
        float newSpeed = miningMastery.getMiningSpeed(breakingSpeed.getOriginalSpeed());
        breakingSpeed.setNewSpeed(newSpeed);
    }

    /**
     * Block crafting
     *
     * @param event
     *            --
     */
    @SubscribeEvent
    public void onItemCrafted(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event) {
        CraftingMastery craftingMastery = (MasteryUtils.getCraftingMastery(event.player));
        Random random = new Random();
        event.crafting.setCount(event.crafting.getCount() + (random.nextInt(100) + craftingMastery.getLevel()) / 100);
    }

}
