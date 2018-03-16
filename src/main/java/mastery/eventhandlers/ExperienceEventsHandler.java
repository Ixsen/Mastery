package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperienceEventsHandler {

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        IMastery mastery = breakEvent.getPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
        mastery.increaseMiningExp();
        breakEvent.getPlayer().sendMessage(new TextComponentString("Your mining exp is: " + mastery.getMiningMastery()));
    }

    @SubscribeEvent
    public void getHit(LivingHurtEvent getHitEvent) {
        if(getHitEvent.getSource().getTrueSource() instanceof  EntityPlayer)  {
            getHitEvent.getSource().getTrueSource().sendMessage(new TextComponentString("You hit " + getHitEvent.getEntity().getName() + " for " + getHitEvent.getAmount()));
        }
        if(getHitEvent.getEntity() instanceof EntityPlayer){
            getHitEvent.getEntity().sendMessage(new TextComponentString("You got hit!"));
        }
    }


}
