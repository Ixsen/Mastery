package mastery.proxy;

import mastery.MasteryBlocks;
import mastery.MasteryItems;
import mastery.configuration.MasteryConfiguration;
import mastery.eventhandlers.TooltipEventHandler;
import mastery.keybindings.InputHandler;
import mastery.keybindings.KeyBindings;
import mastery.oldui.LevelOverlayUi;
import mastery.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
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
        MinecraftForge.EVENT_BUS.register(new MasteryConfiguration());
        MinecraftForge.EVENT_BUS.register(new TooltipEventHandler());

        KeyBindings.init();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : MasteryItems.ALL_ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModel();
            }
        }
        for (Block block : MasteryBlocks.ALL_BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModel();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mastery.proxy.CommonProxy#registerItemRenderer(net.minecraft.item.Item, int, java.lang.String)
     */
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
}
