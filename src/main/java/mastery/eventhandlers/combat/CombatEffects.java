package mastery.eventhandlers.combat;

import mastery.capability.skillclasses.CombatMastery;
import mastery.util.MasteryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CombatEffects {

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent livingHurtEvent) {
        if (livingHurtEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            CombatMastery mastery = MasteryUtils.getCombatMastery(livingHurtEvent.getSource().getTrueSource());
            float newDamage = mastery.getAttackDamageEffect(livingHurtEvent.getAmount());
            livingHurtEvent.setAmount(newDamage);
        } else if (livingHurtEvent.getEntity() instanceof EntityPlayer
                && livingHurtEvent.getSource().getTrueSource() != null) {
            CombatMastery mastery = MasteryUtils.getCombatMastery(livingHurtEvent.getEntity());
            float newDamage = mastery.getDefenseDamageEffect(livingHurtEvent.getAmount());
            livingHurtEvent.setAmount(newDamage);
        }
    }

}
