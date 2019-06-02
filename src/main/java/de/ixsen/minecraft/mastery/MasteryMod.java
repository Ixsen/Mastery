package de.ixsen.minecraft.mastery;

import static de.ixsen.minecraft.mastery.MasteryItems.INGRIDIENT_RAINBOW_POWDER;
import static de.ixsen.minecraft.mastery.eventsystem.MasteryEventType.PLAYER_EXP_CHANGED;
import static de.ixsen.minecraft.mastery.eventsystem.MasteryEventType.PLAYER_LEVEL_UP;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.AnnotationProcessor;
import de.ixsen.minecraft.mastery.eventsystem.MasteryEvent;
import de.ixsen.minecraft.mastery.eventsystem.MasteryEventHandler;
import de.ixsen.minecraft.mastery.networking.PacketHandler;
import de.ixsen.minecraft.mastery.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = MasteryMod.MOD_ID, name = MasteryMod.NAME, version = MasteryMod.VERSION)
public class MasteryMod {

    public static final String MOD_ID = "mastery";
    public static final String NAME = "MasteryMod";
    public static final String VERSION = "1.12.2-0.0.1";

    @SidedProxy(clientSide = "de.ixsen.minecraft.mastery.proxy.ClientProxy", serverSide = "de.ixsen.mastery.minecraft.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MasteryMod.MOD_ID)
    public static MasteryMod INSTANCE;

    private MasteryEventHandler eventSystem;
    private AnnotationProcessor annotationProcessor;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.eventSystem = new MasteryEventHandler();

        this.annotationProcessor = new AnnotationProcessor();
        this.annotationProcessor.initialize();

        MasteryMod.proxy.preInit(event);
        PacketHandler.registerMasteryExpMessages();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MasteryMod.proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MasteryMod.proxy.postInit(event);
    }

    public static CreativeTabs TAB_MASTERY = new CreativeTabs("tab_mastery") {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(INGRIDIENT_RAINBOW_POWDER);
        }
    };

    public static void fireExpEvent(MasterySpec spec, boolean notifyUI) {
        MasteryMod.INSTANCE.eventSystem.fireEvent(new MasteryEvent(PLAYER_EXP_CHANGED, spec, notifyUI));
    }

    public static void fireLevelUpEvent(MasterySpec spec, EntityPlayer player) {
        MasteryMod.INSTANCE.eventSystem.fireEvent(new MasteryEvent(PLAYER_LEVEL_UP, spec, player));
    }

    public static MasteryEventHandler getEventHandler() {
        return MasteryMod.INSTANCE.eventSystem;
    }

    public static AnnotationProcessor getAnnotationProcessor() {
        return MasteryMod.INSTANCE.annotationProcessor;
    }

}