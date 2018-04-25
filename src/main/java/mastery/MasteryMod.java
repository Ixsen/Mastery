package mastery;

import mastery.networking.PacketHandler;
import mastery.proxy.CommonProxy;
import mastery.ui.LevelOverlayUi;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MasteryMod.modid, name = MasteryMod.name, version = MasteryMod.version)
public class MasteryMod {

    public static final String modid = "mastery";
    public static final String name = "MasteryMod";
    public static final String version = "1.12.2-0.0.1";

    @SidedProxy(clientSide = "mastery.proxy.ClientProxy", serverSide = "mastery.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(modid)
    public static MasteryMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        PacketHandler.registerMasteryExpMessages();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}