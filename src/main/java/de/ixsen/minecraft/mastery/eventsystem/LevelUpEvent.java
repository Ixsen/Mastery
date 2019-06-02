package de.ixsen.minecraft.mastery.eventsystem;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import net.minecraft.entity.player.EntityPlayer;

public class LevelUpEvent {

    private final MasterySpec masterySpec;
    private final EntityPlayer player;

    public LevelUpEvent(MasterySpec masterySpec, EntityPlayer player) {
        this.masterySpec = masterySpec;
        this.player = player;
    }

    public MasterySpec getMasterySpec() {
        return this.masterySpec;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }
}
