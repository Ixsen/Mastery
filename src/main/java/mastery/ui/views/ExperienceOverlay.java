package mastery.ui.views;

import mastery.MasteryMod;
import mastery.capability.player.skillclasses.MasterySpec;
import mastery.eventsystem.MasteryEvent;
import mastery.eventsystem.MasteryEventType;
import masteryUI.elements.core.UIMCScreen;

public class ExperienceOverlay extends UIMCScreen {

    public ExperienceOverlay() {
        super();

        MasteryMod.getEventHandler().addListener(this::processEvent);
    }

    private void processEvent(MasteryEvent masteryEvent) {
        if (masteryEvent.getType() == MasteryEventType.PLAYER_EXP_CHANGED
                && masteryEvent.getSource() instanceof MasterySpec && masteryEvent.getTarget() instanceof Boolean) {
//            this.setVisibleTemporarily(5);
        }
    }
}
