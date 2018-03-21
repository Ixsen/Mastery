package mastery.proxy;

import mastery.MasteryMod;
import mastery.eventhandlers.EffectEventsHandler;
import mastery.eventhandlers.ExperienceEventsHandler;
import mastery.eventhandlers.SaveLoadEventHandler;
import mastery.experience.IMastery;
import mastery.experience.MasteryPersistenceManager;
import mastery.experience.PlayerCapabilityHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;


@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ExperienceEventsHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerCapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new EffectEventsHandler());
        MinecraftForge.EVENT_BUS.register(new SaveLoadEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(MasteryMod.instance, new GuiProxy());
        registerCapabilities();
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    }

    private void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IMastery.class, new MasteryPersistenceManager(), mastery.experience.Mastery.class);
    }

}
