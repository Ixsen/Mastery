package mastery.eventhandlers.scavenging;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
public class ScavengingExperience extends AbstractExperienceHandler {

    public ScavengingExperience() {
        this.spec = MasterySpec.SCAVENGING;
    }
}
