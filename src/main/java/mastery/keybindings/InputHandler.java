package mastery.keybindings;

import mastery.MasteryMod;
import mastery.common.annotations.SubscribeToClientEventBus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@SubscribeToClientEventBus
public class InputHandler {

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        if (KeyBindings.openGuiKey.isPressed()) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            player.openGui(MasteryMod.INSTANCE, 1, player.getEntityWorld(), player.getPosition().getX(),
                    player.getPosition().getY(), player.getPosition().getZ());
        }
    }
}
