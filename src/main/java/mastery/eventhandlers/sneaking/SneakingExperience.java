package mastery.eventhandlers.sneaking;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToCommonEventBus;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class SneakingExperience extends AbstractExperienceHandler {

    public SneakingExperience() {
        this.spec = MasterySpec.SNEAKING;
    }
}
