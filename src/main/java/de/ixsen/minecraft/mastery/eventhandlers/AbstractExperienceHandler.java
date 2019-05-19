package de.ixsen.minecraft.mastery.eventhandlers;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.util.MasteryUtils;
import de.ixsen.minecraft.mastery.common.util.NetworkUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class AbstractExperienceHandler {
    protected MasterySpec spec;

    protected void addExperience(Entity player, int amount) {
        if (amount <= 0) {
            return;
        }
        MasteryClass mastery = MasteryUtils.getMastery(player, this.spec);
        mastery.increaseExperience(amount);
        NetworkUtils.sendExpToPlayer(mastery, (EntityPlayerMP) player);
    }

    protected void addExperience(Entity player, ExperienceDictionary expType) {
        MasteryClass mastery = MasteryUtils.getMastery(player, this.spec);
        mastery.increaseExperience(expType.getValue());
        NetworkUtils.sendExpToPlayer(mastery, (EntityPlayerMP) player);
    }

}
