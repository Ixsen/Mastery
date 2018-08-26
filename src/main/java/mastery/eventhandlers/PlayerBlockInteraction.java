package mastery.eventhandlers;

import mastery.util.BlockUtils;
import net.minecraft.block.BlockContainer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerBlockInteraction {

    @SubscribeEvent
    void place(BlockEvent.PlaceEvent placeEvent) {
        if (placeEvent.getPlayer() != null && !placeEvent.getPlayer().getEntityWorld().isRemote) {
            BlockUtils.playerPlacedBlock(placeEvent.getPos(), placeEvent.getWorld());
        }
    }

    @SubscribeEvent
    void openContainer(PlayerInteractEvent.RightClickBlock openEvent) {
        if (openEvent.getEntityPlayer() != null && !openEvent.getWorld().isRemote
                && openEvent.getWorld().getBlockState(openEvent.getPos()).getBlock() instanceof BlockContainer) {
            BlockUtils.playerOpenedContainer(openEvent.getPos(), openEvent.getWorld());
        }
    }

    @SubscribeEvent
    void destroyBlock(BlockEvent.HarvestDropsEvent destroyEvent) {
        if (!destroyEvent.getWorld().isRemote) {
            BlockUtils.blockWasBroken(destroyEvent.getPos(), destroyEvent.getWorld());
        }
    }
}
