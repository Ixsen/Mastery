package mastery.eventhandlers.fishing;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToCommonEventBus;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class FishingExperience extends AbstractExperienceHandler {

    public FishingExperience() {
        this.spec = MasterySpec.FISHING;
    }
}
