package mastery.keybindings;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;


public class InputHandler {

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        if(KeyBindings.openGuiKey.isPressed()) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("KEY PRESSED"));
        }
    }
}
