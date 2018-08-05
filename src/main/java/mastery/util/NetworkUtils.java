package mastery.util;

import mastery.experience.skillclasses.MasteryClass;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class NetworkUtils {

    public static void sendExpToPlayer(MasteryClass mastery, EntityPlayerMP player) {
        MasteryMessage message = new MasteryMessage(mastery.getSkillClass().order, mastery.getLevel(),
                mastery.getExperience(), true);
        PacketHandler.INSTANCE.sendTo(message, player);
    }
}
