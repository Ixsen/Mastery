package de.ixsen.minecraft.mastery.eventhandlers.trading;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class TradingExperience extends AbstractExperienceHandler {

    public TradingExperience() {
        this.spec = MasterySpec.TRADING;
    }
}
