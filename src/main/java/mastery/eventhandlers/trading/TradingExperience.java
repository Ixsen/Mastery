package mastery.eventhandlers.trading;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToCommonEventBus;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
@SubscribeToCommonEventBus
public class TradingExperience extends AbstractExperienceHandler {

    public TradingExperience() {
        this.spec = MasterySpec.TRADING;
    }
}
