package de.ixsen.minecraft.mastery.eventhandlers.combat;

import static net.minecraft.entity.SharedMonsterAttributes.*;

import java.util.UUID;

import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.CombatMastery;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.common.util.AttributeUtils;
import de.ixsen.minecraft.mastery.common.util.MasteryUtils;
import de.ixsen.minecraft.mastery.eventsystem.LevelUpEvent;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SubscribeToCommonEventBus
public class CombatEffects {

    private final UUID modifierUid = UUID.fromString("3ba0a923-a53f-4df4-9c32-6a7750b76007");

    public CombatEffects() {
        MasteryMod.getEventHandler().addListener(this::performEvent, LevelUpEvent.class);
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

    public void performEvent(LevelUpEvent masteryEvent) {
        EntityPlayer player = masteryEvent.getPlayer();
        CombatMastery combatMastery = MasteryUtils.getCombatMastery(player);
        AbstractAttributeMap attributeMap = player.getAttributeMap();

        AttributeUtils.applyModifier(combatMastery.getAttackModifier(),
                attributeMap.getAttributeInstance(ATTACK_DAMAGE), "Mastery Attack Modifier", this.modifierUid);
        AttributeUtils.applyModifier(combatMastery.getAttackSpeedModifier(),
                attributeMap.getAttributeInstance(ATTACK_SPEED), "Mastery Attack-Speed Modifier", this.modifierUid);
        AttributeUtils.applyModifier(combatMastery.getHealthModifier(), attributeMap.getAttributeInstance(MAX_HEALTH),
                "Mastery Health Modifier", this.modifierUid);

    }
}
