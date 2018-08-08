package mastery.eventhandlers.combat;

import mastery.capability.skillclasses.CombatMastery;
import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static mastery.eventhandlers.ExperienceDictionary.COMBAT_ENTITY_DAMAGED;
import static mastery.eventhandlers.ExperienceDictionary.COMBAT_PLAYER_DAMAGED;

public class CombatExperience extends AbstractExperienceHandler {

    public CombatExperience() {
        this.spec = MasterySpec.COMBAT;
    }

    @SubscribeEvent
    public void killEntity(LivingDeathEvent deathEvent) {
        if (!deathEvent.getEntity().getEntityWorld().isRemote
                && deathEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            this.addExperience(deathEvent.getSource().getTrueSource(),
                    CombatUtils.calculateMurderExperience(deathEvent.getEntityLiving()));
        }
    }

    @SubscribeEvent
    public void getHit(LivingHurtEvent livingHurtEvent) {
        if (livingHurtEvent.getSource().getTrueSource() instanceof EntityPlayer
                && livingHurtEvent.getAmount() >= CombatMastery.doDamageExpThreshold) {
            this.addExperience(livingHurtEvent.getSource().getTrueSource(), COMBAT_ENTITY_DAMAGED);
        }
        if (livingHurtEvent.getEntity() instanceof EntityPlayer
                && livingHurtEvent.getSource().getTrueSource() instanceof EntityLivingBase
                && livingHurtEvent.getAmount() >= CombatMastery.getDamagedExpThreshold) {
            this.addExperience(livingHurtEvent.getEntity(), COMBAT_PLAYER_DAMAGED);
        }
    }
}