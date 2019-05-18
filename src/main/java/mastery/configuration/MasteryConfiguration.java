package mastery.configuration;

import mastery.MasteryMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MasteryMod.MOD_ID)
@Config.LangKey("mastery.config.title")
public class MasteryConfiguration {

    @Config.Name("UI Overlay")
    @Config.Comment("Contains configuration to modify the appearance of the overlay")
    public static UiOverlayConfiguration UI_OVERLAY = new UiOverlayConfiguration();

    @Mod.EventBusSubscriber(modid = MasteryMod.MOD_ID)
    private static class ConfigurationSyncingHandler {
        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event
         *            The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(MasteryMod.MOD_ID)) {
                ConfigManager.sync(MasteryMod.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}