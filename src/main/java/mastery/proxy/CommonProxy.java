package mastery.proxy;

import mastery.MasteryBlocks;
import mastery.MasteryItems;
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
		event.getRegistry().registerAll(MasteryBlocks.ALL_BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(MasteryItems.ALL_ITEMS.toArray(new Item[0]));
	}

	private void registerCapabilities() {
		CapabilityManager.INSTANCE.register(IMastery.class, new MasteryPersistenceManager(),
				mastery.experience.Mastery.class);
	}

	/**
	 * Assigns the right models for a certain item. Does nothing for the common
	 * proxy. Is overwritten for the client proxy to register the models. Is not the
	 * same as the ModelRegistryEvent.
	 * 
	 * @param item
	 *            Item to assign a model to.
	 * @param meta
	 *            Metadata for the item
	 * @param id
	 *            ID for the Resource location
	 */
	public void registerItemRenderer(Item item, int meta, String id) {
	}

}
