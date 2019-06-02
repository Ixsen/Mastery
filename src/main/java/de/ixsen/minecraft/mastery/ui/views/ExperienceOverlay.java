package de.ixsen.minecraft.mastery.ui.views;

import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToClientEventBus;
import de.ixsen.minecraft.mastery.common.util.MasteryUtils;
import de.ixsen.minecraft.mastery.configuration.MasteryConfiguration;
import de.ixsen.minecraft.mastery.eventsystem.ExperienceGainEvent;
import de.ixsen.minecraft.mastery.eventsystem.LevelUpEvent;
import de.ixsen.minecraft.mastery.ui.components.ExperienceComponent;
import de.ixsen.minecraft.mastery.ui.components.LevelUpComponent;
import de.ixsen.minecraft.uilib.elements.core.UiOverlay;
import net.minecraft.client.Minecraft;

@SubscribeToClientEventBus
public class ExperienceOverlay extends UiOverlay<ExperienceComponent> {

    private LevelUpComponent levelUpComponent;

    public ExperienceOverlay() {
        super();
        MasteryMod.getEventHandler().addListener(this::experienceEvent, ExperienceGainEvent.class);
        MasteryMod.getEventHandler().addListener(this::levelUpEvent, LevelUpEvent.class);
    }

    @Override
    protected ExperienceComponent createScreenContainer() {
        ExperienceComponent experienceComponent = new ExperienceComponent();

        this.levelUpComponent = new LevelUpComponent();
        experienceComponent.addElements(this.levelUpComponent);
        return experienceComponent;
    }

    private void experienceEvent(ExperienceGainEvent event) {
        MasteryClass mastery = MasteryUtils.getMastery(Minecraft.getMinecraft().player, event.getMasterySpec());
        this.screenContainer.setMasteryInfo(mastery);
        if (event.shouldNotifyUI()) {
            this.setVisibleTemporarily(MasteryConfiguration.UI_OVERLAY.overlayTime);
        }

    }

    private void levelUpEvent(LevelUpEvent event) {
        MasteryClass mastery = MasteryUtils.getMastery(event.getPlayer(), event.getMasterySpec());
        this.levelUpComponent.setMasteryInfo(mastery);

    }

}
