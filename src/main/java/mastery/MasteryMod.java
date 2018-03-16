package mastery;

import mastery.eventhandlers.ExperienceEventsHandler;
import mastery.eventhandlers.SaveLoadEventHandler;
import mastery.experience.IMastery;
import mastery.experience.PlayerCapabilityHandler;
import mastery.experience.PlayerExperience;
import mastery.experience.TMPEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MasteryMod.modId, name = MasteryMod.name, version = MasteryMod.version)
public class MasteryMod {

    public static final String modId = "mastery";
    public static final String name = "MasteryMod";
    public static final String version = "1.12.2-0.0.1";

    @Mod.Instance(modId)
    public static MasteryMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ExperienceEventsHandler());
        MinecraftForge.EVENT_BUS.register(new SaveLoadEventHandler());
        MinecraftForge.EVENT_BUS.register(new TMPEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerCapabilityHandler());

        registerCapabilities();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    private void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IMastery.class, new PlayerExperience(), mastery.experience.Mastery.class);
    }

}