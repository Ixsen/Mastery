package de.ixsen.minecraft.mastery.eventhandlers.athletics;

import static de.ixsen.minecraft.mastery.eventsystem.MasteryEventType.PLAYER_LEVEL_UP;
import static net.minecraft.entity.EntityLivingBase.SWIM_SPEED;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import java.util.UUID;

import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.AthleticsMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.eventsystem.MasteryEvent;
import de.ixsen.minecraft.mastery.common.util.AttributeUtils;
import de.ixsen.minecraft.mastery.common.util.MasteryUtils;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SubscribeToCommonEventBus
public class AthleticsEffects {

    private final UUID modifierUid = UUID.fromString("839fbc52-388f-4729-b2ce-5f45cdb72cdc");

    public AthleticsEffects() {
        MasteryMod.getEventHandler().addListener(this::levelUpEvent);
    }

    @SubscribeEvent
    public void jump(LivingEvent.LivingJumpEvent jumpEvent) {
        if (jumpEvent.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) jumpEvent.getEntity();
            player.motionY *= MasteryUtils.getAthleticsMastery(player).getJumpModifier();
        }
    }

    private void levelUpEvent(MasteryEvent masteryEvent) {
        if (masteryEvent.getType() == PLAYER_LEVEL_UP && masteryEvent.getSource() == MasterySpec.ATHLETICS) {
            EntityPlayer player = (EntityPlayer) masteryEvent.getTarget();
            AthleticsMastery athleticsMastery = MasteryUtils.getAthleticsMastery(player);
            AbstractAttributeMap attributeMap = player.getAttributeMap();

            AttributeUtils.applyModifier(athleticsMastery.getSpeedModifier(),
                    attributeMap.getAttributeInstance(MOVEMENT_SPEED), "Mastery Athletics Speed Modifier",
                    this.modifierUid);
            AttributeUtils.applyModifier(athleticsMastery.getSwimModifier(),
                    attributeMap.getAttributeInstance(SWIM_SPEED), "Mastery Swimming Speed Modifier", this.modifierUid);
        }
    }
}
