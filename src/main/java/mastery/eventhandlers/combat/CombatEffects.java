package mastery.eventhandlers.combat;

import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_SPEED;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;

import java.util.UUID;

import mastery.MasteryMod;
import mastery.capability.player.skillclasses.CombatMastery;
import mastery.capability.player.skillclasses.MasterySpec;
import mastery.common.annotations.SubscribeToCommonEventBus;
import mastery.eventsystem.MasteryEvent;
import mastery.eventsystem.MasteryEventType;
import mastery.common.util.AttributeUtils;
import mastery.common.util.MasteryUtils;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SubscribeToCommonEventBus
public class CombatEffects {

    private final UUID modifierUid = UUID.fromString("3ba0a923-a53f-4df4-9c32-6a7750b76007");

    public CombatEffects() {
        MasteryMod.getEventHandler().addListener(this::onLevelUp);
    }

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent livingHurtEvent) {
        if (livingHurtEvent.getEntity() instanceof EntityPlayer
                && livingHurtEvent.getSource().getTrueSource() != null) {
            float damageTaken = MasteryUtils.getCombatMastery(livingHurtEvent.getEntity())
                    .getDamageTaken(livingHurtEvent.getAmount());
            livingHurtEvent.setAmount(damageTaken);
        }
    }

    private void onLevelUp(MasteryEvent masteryEvent) {
        if (masteryEvent.getType() == MasteryEventType.PLAYER_LEVEL_UP
                && masteryEvent.getSource() == MasterySpec.COMBAT) {
            EntityPlayer player = (EntityPlayer) masteryEvent.getTarget();
            CombatMastery combatMastery = MasteryUtils.getCombatMastery(player);
            AbstractAttributeMap attributeMap = player.getAttributeMap();

            AttributeUtils.applyModifier(combatMastery.getAttackModifier(),
                    attributeMap.getAttributeInstance(ATTACK_DAMAGE), "Mastery Attack Modifier", this.modifierUid);
            AttributeUtils.applyModifier(combatMastery.getAttackSpeedModifier(),
                    attributeMap.getAttributeInstance(ATTACK_SPEED), "Mastery Attack-Speed Modifier", this.modifierUid);
            AttributeUtils.applyModifier(combatMastery.getHealthModifier(),
                    attributeMap.getAttributeInstance(MAX_HEALTH), "Mastery Health Modifier", this.modifierUid);

        }
    }
}
