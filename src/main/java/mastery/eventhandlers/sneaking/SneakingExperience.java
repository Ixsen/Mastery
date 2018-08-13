package mastery.eventhandlers.sneaking;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
public class SneakingExperience extends AbstractExperienceHandler {

    public SneakingExperience() {
        this.spec = MasterySpec.SNEAKING;
    }
}
