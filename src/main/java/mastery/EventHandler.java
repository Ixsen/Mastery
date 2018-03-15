package mastery;

import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        System.out.println("HADOUKEn");
    }
}
