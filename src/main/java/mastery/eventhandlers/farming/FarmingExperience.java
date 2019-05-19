package mastery.eventhandlers.farming;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToCommonEventBus;
import mastery.eventhandlers.AbstractExperienceHandler;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SubscribeToCommonEventBus
public class FarmingExperience extends AbstractExperienceHandler {

    public FarmingExperience() {
        this.spec = MasterySpec.FARMING;
    }

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        if (!breakEvent.getPlayer().getEntityWorld().isRemote
                && FarmingUtils.shouldApplyFarming(breakEvent.getState())) {
            this.addExperience(breakEvent.getPlayer(), 1);
        }
    }

}
