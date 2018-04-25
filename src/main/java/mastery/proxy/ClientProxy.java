package mastery.proxy;

import mastery.keybindings.InputHandler;
import mastery.keybindings.KeyBindings;
import mastery.ui.LevelOverlayUi;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(new InputHandler());
    	MinecraftForge.EVENT_BUS.register(new LevelOverlayUi());
        KeyBindings.init();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    }
}
