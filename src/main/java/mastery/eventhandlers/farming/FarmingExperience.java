package mastery.eventhandlers.farming;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FarmingExperience extends AbstractExperienceHandler {

    public FarmingExperience() {
        this.spec = MasterySpec.FARMING;
    }

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        if (!breakEvent.getPlayer().getEntityWorld().isRemote
                && FarmingUtils.shouldGetFarmingExp(breakEvent.getState())) {
            this.addExperience(breakEvent.getPlayer(), 1);
        }
    }

}
