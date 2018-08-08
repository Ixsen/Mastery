package mastery.eventhandlers;

import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperienceEventsHandler {

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
}