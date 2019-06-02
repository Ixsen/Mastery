package de.ixsen.minecraft.mastery.proxy;

import de.ixsen.minecraft.mastery.MasteryBlocks;
import de.ixsen.minecraft.mastery.MasteryItems;
import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.capability.player.IMastery;
import de.ixsen.minecraft.mastery.capability.player.Mastery;
import de.ixsen.minecraft.mastery.capability.player.MasteryPersistenceManager;
import de.ixsen.minecraft.mastery.capability.world.BlockInfo;
import de.ixsen.minecraft.mastery.capability.world.BlockInfoPersistenceManager;
import de.ixsen.minecraft.mastery.capability.world.IBlockInfo;
import de.ixsen.minecraft.mastery.common.annotations.AnnotationProcessor;
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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(MasteryBlocks.ALL_BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(MasteryItems.ALL_ITEMS.toArray(new Item[0]));
    }

    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {

        AnnotationProcessor annotationProcessor = MasteryMod.getAnnotationProcessor();
        annotationProcessor.getCommonClasses().forEach(this::registerToEventBus);

        // MinecraftForge.EVENT_BUS.register(new PlayerBlockInteraction());

        // MinecraftForge.EVENT_BUS.register(new AlchemyExperience());
        // MinecraftForge.EVENT_BUS.register(new AthleticsExperience());
        // MinecraftForge.EVENT_BUS.register(new CombatExperience());
        // MinecraftForge.EVENT_BUS.register(new CraftingExperience());
        // MinecraftForge.EVENT_BUS.register(new FarmingExperience());
        // MinecraftForge.EVENT_BUS.register(new HusbandryExperience());
        // MinecraftForge.EVENT_BUS.register(new MiningExperience());
        // MinecraftForge.EVENT_BUS.register(new SurvivalExperience());
        // MinecraftForge.EVENT_BUS.register(new TradingExperience());
        // MinecraftForge.EVENT_BUS.register(new SneakingExperience());
        // MinecraftForge.EVENT_BUS.register(new ScavengingExperience());
        // MinecraftForge.EVENT_BUS.register(new FishingExperience());

        // MinecraftForge.EVENT_BUS.register(new AlchemyEffects());
        // MinecraftForge.EVENT_BUS.register(new AthleticsEffects());
        // MinecraftForge.EVENT_BUS.register(new CombatEffects());
        // MinecraftForge.EVENT_BUS.register(new CraftingEffects());
        // MinecraftForge.EVENT_BUS.register(new FarmingEffects());
        // MinecraftForge.EVENT_BUS.register(new HusbandryEffects());
        // MinecraftForge.EVENT_BUS.register(new MiningEffects());
        // MinecraftForge.EVENT_BUS.register(new SurvivalEffects());
        // MinecraftForge.EVENT_BUS.register(new TradingEffects());
        // MinecraftForge.EVENT_BUS.register(new SneakingEffects());
        // MinecraftForge.EVENT_BUS.register(new ScavengingEffects());
        // MinecraftForge.EVENT_BUS.register(new FishingEffects());

        // MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        // MinecraftForge.EVENT_BUS.register(new SaveLoadEventHandler());

        NetworkRegistry.INSTANCE.registerGuiHandler(MasteryMod.INSTANCE, new GuiProxy());
        this.registerCapabilities();

    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    private void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IMastery.class, new MasteryPersistenceManager(), Mastery::new);
        CapabilityManager.INSTANCE.register(IBlockInfo.class, new BlockInfoPersistenceManager(), BlockInfo::new);
    }

    /**
     * Assigns the right models for a certain item. Does nothing for the common proxy. Is overwritten for the client proxy to register the
     * models. Is not the same as the ModelRegistryEvent.
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

    /**
     * Registers the class to the MinecraftForge.EVENT_BUS
     *
     * @param clazz
     *            -
     */
    void registerToEventBus(Class<?> clazz) {
        try {
            MinecraftForge.EVENT_BUS.register(clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
