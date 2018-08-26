package mastery.eventhandlers.fishing;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
public class FishingExperience extends AbstractExperienceHandler {

    public FishingExperience() {
        this.spec = MasterySpec.FISHING;
    }
}
