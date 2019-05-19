package mastery.ui.views;

import mastery.MasteryMod;
import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToClientEventBus;
import mastery.eventsystem.MasteryEvent;
import mastery.eventsystem.MasteryEventType;
import masteryUI.elements.core.UIMCOverlay;

@SubscribeToClientEventBus
public class ExperienceOverlay extends UIMCOverlay {

    public ExperienceOverlay() {
        super();

        MasteryMod.getEventHandler().addListener(this::processEvent);
    }

    private void processEvent(MasteryEvent masteryEvent) {
        if (masteryEvent.getType() == MasteryEventType.PLAYER_EXP_CHANGED
                && masteryEvent.getSource() instanceof MasterySpec && masteryEvent.getTarget() instanceof Boolean) {
            this.setVisibleTemporarily(5);
        }
    }
}
