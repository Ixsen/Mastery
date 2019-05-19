package mastery.eventhandlers.mining;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToCommonEventBus;
import mastery.eventhandlers.AbstractExperienceHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SubscribeToCommonEventBus
public class MiningExperience extends AbstractExperienceHandler {

    public MiningExperience() {
        this.spec = MasterySpec.MINING;
    }

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
            this.addExperience(breakEvent.getPlayer(), MiningUtils.expAmountForBlock(breakEvent.getState().getBlock(),
                    MiningUtils.hasSilkTouchEnchantedTool(breakEvent.getPlayer())));
        }
    }

    @SubscribeEvent
    public void explosion(ExplosionEvent.Detonate event) {
        if (event.getExplosion().getExplosivePlacedBy() instanceof EntityPlayer
                && !event.getExplosion().getExplosivePlacedBy().getEntityWorld().isRemote) {
            this.addExperience(event.getExplosion().getExplosivePlacedBy(),
                    MiningUtils.expForExplosion(event.getExplosion(), event.getWorld()));
        }
    }
}
