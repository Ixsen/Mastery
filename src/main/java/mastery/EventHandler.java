package mastery;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        breakEvent.getPlayer().sendMessage(new TextComponentString(breakEvent.toString()));
    }

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load worldLoadEvent) {
        //TODO maybe initialize Player stuff here?
        System.out.println("World Loaded!");
    }

}
