package de.ixsen.minecraft.mastery.eventhandlers.fishing;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class FishingExperience extends AbstractExperienceHandler {

    public FishingExperience() {
        this.spec = MasterySpec.FISHING;
    }
}
