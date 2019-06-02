package de.ixsen.minecraft.mastery.eventhandlers.sneaking;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class SneakingExperience extends AbstractExperienceHandler {

    public SneakingExperience() {
        this.spec = MasterySpec.SNEAKING;
    }
}
