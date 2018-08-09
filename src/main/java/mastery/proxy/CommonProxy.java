package mastery.proxy;

import mastery.MasteryBlocks;
import mastery.MasteryItems;
import mastery.MasteryMod;
import mastery.capability.IMastery;
import mastery.capability.MasteryPersistenceManager;
import mastery.capability.PlayerCapabilityHandler;
import mastery.eventhandlers.SaveLoadEventHandler;
import mastery.eventhandlers.alchemy.AlchemyEffects;
import mastery.eventhandlers.alchemy.AlchemyExperience;
import mastery.eventhandlers.athletics.AthleticsEffects;
import mastery.eventhandlers.athletics.AthleticsExperience;
import mastery.eventhandlers.combat.CombatEffects;
import mastery.eventhandlers.combat.CombatExperience;
import mastery.eventhandlers.crafting.CraftingEffects;
import mastery.eventhandlers.crafting.CraftingExperience;
import mastery.eventhandlers.farming.FarmingEffects;
import mastery.eventhandlers.farming.FarmingExperience;
import mastery.eventhandlers.husbandry.HusbandryEffects;
import mastery.eventhandlers.husbandry.HusbandryExperience;
import mastery.eventhandlers.mining.MiningEffects;
import mastery.eventhandlers.mining.MiningExperience;
import mastery.eventhandlers.survival.SurvivalEffects;
import mastery.eventhandlers.survival.SurvivalExperience;
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
        MinecraftForge.EVENT_BUS.register(new AlchemyExperience());
        MinecraftForge.EVENT_BUS.register(new AthleticsExperience());
        MinecraftForge.EVENT_BUS.register(new CombatExperience());
        MinecraftForge.EVENT_BUS.register(new CraftingExperience());
        MinecraftForge.EVENT_BUS.register(new FarmingExperience());
        MinecraftForge.EVENT_BUS.register(new HusbandryExperience());
        MinecraftForge.EVENT_BUS.register(new MiningExperience());
        MinecraftForge.EVENT_BUS.register(new SurvivalExperience());

        MinecraftForge.EVENT_BUS.register(new AlchemyEffects());
        MinecraftForge.EVENT_BUS.register(new AthleticsEffects());
        MinecraftForge.EVENT_BUS.register(new CombatEffects());
        MinecraftForge.EVENT_BUS.register(new CraftingEffects());
        MinecraftForge.EVENT_BUS.register(new FarmingEffects());
        MinecraftForge.EVENT_BUS.register(new HusbandryEffects());
        MinecraftForge.EVENT_BUS.register(new MiningEffects());
        MinecraftForge.EVENT_BUS.register(new SurvivalEffects());

        MinecraftForge.EVENT_BUS.register(new PlayerCapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new SaveLoadEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(MasteryMod.instance, new GuiProxy());
        this.registerCapabilities();
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
                mastery.capability.Mastery.class);
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

}
