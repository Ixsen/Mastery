package de.ixsen.minecraft.mastery.eventhandlers.scavenging;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class ScavengingExperience extends AbstractExperienceHandler {

    public ScavengingExperience() {
        this.spec = MasterySpec.SCAVENGING;
    }
}
