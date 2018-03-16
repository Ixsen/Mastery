package mastery.experience;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by Granis on 16/03/2018.
 */
public class TMPEventHandler {

    @SubscribeEvent
    public void onPlayerLogsIn(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        IMastery mastery = player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
        String testMessage = "Meine mastery ist: " + mastery.getMiningMastery();

        player.sendMessage(new TextComponentString(testMessage));
    }
}
