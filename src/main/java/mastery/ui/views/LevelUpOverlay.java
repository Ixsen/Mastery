package mastery.ui.views;

import mastery.MasteryMod;
import mastery.capability.player.skillclasses.MasterySpec;
import mastery.eventsystem.MasteryEvent;
import mastery.eventsystem.MasteryEventType;
import masteryUI.elements.core.UIMCOverlay;
import masteryUI.layout.VerticalLayout;

public class LevelUpOverlay extends UIMCOverlay {

    public LevelUpOverlay() {
        super();

        MasteryMod.getEventHandler().addListener(this::processEvent);

        this.createUI();
    }

    private void processEvent(MasteryEvent masteryEvent) {
        if (masteryEvent.getType() == MasteryEventType.PLAYER_LEVEL_UP
                && masteryEvent.getSource() instanceof MasterySpec && masteryEvent.getTarget() instanceof Boolean) {
            this.setVisibleTemporarily(5);
        }
    }

    private void createUI() {
        this.screenContainer.setLayout(new VerticalLayout());
    }

}
