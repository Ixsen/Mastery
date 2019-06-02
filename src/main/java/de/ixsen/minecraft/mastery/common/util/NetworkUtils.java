package de.ixsen.minecraft.mastery.common.util;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.mastery.networking.MasteryMessage;
import de.ixsen.minecraft.mastery.networking.PacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class NetworkUtils {

    public static void sendExpToPlayer(MasteryClass mastery, EntityPlayerMP player) {
        MasteryMessage message = new MasteryMessage(mastery.getSkillClass().order, mastery.getLevel(),
                mastery.getExperience(), true);
        PacketHandler.INSTANCE.sendTo(message, player);
    }
}
