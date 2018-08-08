package mastery.eventhandlers;

import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperienceEventsHandler {

    public ExperienceEventsHandler() {
    }

    /**
     * TODO FARMING
     *
     * @param harvestCheck
     *            --
     */
    @SubscribeEvent
    public void harvestCrop(PlayerEvent.HarvestCheck harvestCheck) {

    }

    /**
     * TODO FARMING
     *
     * @param bonemealEvent
     *            --
     */
    @SubscribeEvent
    public void boneEvent(BonemealEvent bonemealEvent) {

    }

    /**
     * TODO FARMING
     *
     * @param useHoeEvent
     *            --
     */
    @SubscribeEvent
    public void hoeing(UseHoeEvent useHoeEvent) {

    }

    /**
     * TODO HUSBANDRY
     *
     * @param tameEvent
     *            --
     */
    @SubscribeEvent
    public void animalTame(AnimalTameEvent tameEvent) {
    }

    /**
     * TODO HUSBANDRY
     *
     * @param interactEvent
     *            --
     */
    @SubscribeEvent
    public void feed(PlayerInteractEvent.EntityInteractSpecific interactEvent) {

    }

    /*
     * TODO TNT MINING
     */
}