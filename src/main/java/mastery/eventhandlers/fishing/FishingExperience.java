package mastery.eventhandlers.fishing;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
public class FishingExperience extends AbstractExperienceHandler {

    public FishingExperience() {
        this.spec = MasterySpec.FISHING;
    }
}
