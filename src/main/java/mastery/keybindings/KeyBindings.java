package mastery.keybindings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static KeyBinding openGuiKey;

    public static void init() {
        openGuiKey = new KeyBinding("Open GUI", Keyboard.KEY_U, "Mastery");
        ClientRegistry.registerKeyBinding(openGuiKey);
    }
}
