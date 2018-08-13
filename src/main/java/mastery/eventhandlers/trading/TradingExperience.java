package mastery.eventhandlers.trading;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;

/**
 * @author Subaro
 */
public class TradingExperience extends AbstractExperienceHandler {

    public TradingExperience() {
        this.spec = MasterySpec.TRADING;
    }
}