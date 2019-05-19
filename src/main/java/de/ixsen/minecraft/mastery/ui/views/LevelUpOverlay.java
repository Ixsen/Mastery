package de.ixsen.minecraft.mastery.ui.views;

import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.eventsystem.MasteryEvent;
import de.ixsen.minecraft.mastery.eventsystem.MasteryEventType;
import de.ixsen.minecraft.uilib.elements.core.UIMCOverlay;
import de.ixsen.minecraft.uilib.layout.VerticalLayout;

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
