package mastery;

import mastery.experience.IMastery;
import mastery.experience.PlayerExperience;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Mastery.modId, name = Mastery.name, version = Mastery.version)
public class Mastery {

    public static final String modId = "mastery";
    public static final String name = "Mastery";
    public static final String version = "1.12.2-0.0.1";

    @Mod.Instance(modId)
    public static Mastery instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        registerCapabilities();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    private void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IMastery.class, new PlayerExperience(), mastery.experience.Mastery.class);
    }

}