package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
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
    public void attackEntity(AttackEntityEvent attackEvent) {
        attackEvent.getEntityPlayer().sendMessage(new TextComponentString(attackEvent.getTarget().getName() + " hit"));
    }

    @SubscribeEvent
    public void criticalExperience(CriticalHitEvent criticalHitEvent) {
        criticalHitEvent.getEntityPlayer().sendMessage(new TextComponentString(criticalHitEvent.getTarget().getName() + " hit very hard"));
    }

    @SubscribeEvent
    public void getHit(LivingAttackEvent getHitEvent) {
        if(getHitEvent.getEntity() instanceof EntityPlayer){
            getHitEvent.getEntity().sendMessage(new TextComponentString("You got hit!"));
        }
    }


}
