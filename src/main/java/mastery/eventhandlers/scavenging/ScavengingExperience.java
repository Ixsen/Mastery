package mastery.eventhandlers.scavenging;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToCommonEventBus;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class ScavengingExperience extends AbstractExperienceHandler {

    public ScavengingExperience() {
        this.spec = MasterySpec.SCAVENGING;
    }
}
