package de.ixsen.minecraft.mastery.ui.views;

import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToClientEventBus;
import de.ixsen.minecraft.mastery.eventsystem.MasteryEvent;
import de.ixsen.minecraft.mastery.eventsystem.MasteryEventType;
import de.ixsen.minecraft.mastery.ui.components.ExperienceComponent;
import de.ixsen.minecraft.uilib.elements.core.UIMCOverlay;
import de.ixsen.minecraft.uilib.layout.FreeFormLayout;
import de.ixsen.minecraft.uilib.layout.UILayout;

@SubscribeToClientEventBus
public class ExperienceOverlay extends UIMCOverlay {

    public ExperienceOverlay() {
        super();

        MasteryMod.getEventHandler().addListener(this::processEvent);
    }

    @Override
    protected void initializeGui() {
        this.screenContainer.addElement(new ExperienceComponent());
    }

    @Override
    protected UILayout createLayout() {
        return new FreeFormLayout();
    }

    private void processEvent(MasteryEvent masteryEvent) {
        if (masteryEvent.getType() == MasteryEventType.PLAYER_EXP_CHANGED
                && masteryEvent.getSource() instanceof MasterySpec && masteryEvent.getTarget() instanceof Boolean) {
            this.setVisibleTemporarily(5000);
        }
    }

}
