package mastery.eventhandlers;

import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EffectEventsHandler {


    /**
     * TODO HUSBANDRY -> maybe twins or shorter grow time
     *
     * @param babyEntitySpawnEvent --
     */
    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent babyEntitySpawnEvent) {

    }

    /**
     * TODO HUSBANDRY -> drops evtl scavanging
     *
     * @param dropsEvent --
     */
    @SubscribeEvent
    public void entityDropsItems(LivingDropsEvent dropsEvent) {

    }

    /**
     * TODO SURVIVAL reduce fall or negate if something is done!
     *
     * @param fallEvent --
     */
    @SubscribeEvent
    public void fallingDamage(LivingFallEvent fallEvent) {

    }
}
