package mastery.eventhandlers.mining;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MiningExperience extends AbstractExperienceHandler {

    public MiningExperience() {
        this.spec = MasterySpec.MINING;
    }

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
            this.addExperience(breakEvent.getPlayer(), MiningUtils.expAmountForBlock(breakEvent.getState().getBlock()));
        }
    }

    @SubscribeEvent
    public void explosion(ExplosionEvent.Detonate event) {
        if (event.getExplosion().getExplosivePlacedBy() instanceof EntityPlayer
                && !event.getExplosion().getExplosivePlacedBy().getEntityWorld().isRemote) {
            this.addExperience(event.getExplosion().getExplosivePlacedBy(),
                    MiningUtils.expForExplosion(event.getExplosion()));
        }
    }
}
