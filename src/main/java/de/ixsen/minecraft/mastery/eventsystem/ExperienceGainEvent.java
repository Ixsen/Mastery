package de.ixsen.minecraft.mastery.eventsystem;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;

public class ExperienceGainEvent {

    private final MasterySpec masterySpec;
    private final boolean notifyUI;

    public ExperienceGainEvent(MasterySpec masterySpec, boolean notifyUI) {
        this.masterySpec = masterySpec;
        this.notifyUI = notifyUI;
    }

    public MasterySpec getMasterySpec() {
        return this.masterySpec;
    }

    public boolean shouldNotifyUI() {
        return this.notifyUI;
    }
}
