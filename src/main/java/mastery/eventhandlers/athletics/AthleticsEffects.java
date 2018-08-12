package mastery.eventhandlers.athletics;

import static mastery.eventsystem.MasteryEventType.PLAYER_LEVEL_UP;
import static net.minecraft.entity.EntityLivingBase.SWIM_SPEED;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import java.util.UUID;

import mastery.MasteryMod;
import mastery.capability.skillclasses.AthleticsMastery;
import mastery.capability.skillclasses.MasterySpec;
import mastery.eventsystem.MasteryEvent;
import mastery.util.MasteryUtils;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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

            this.applyModifier(athleticsMastery.getSpeedModifier(), attributeMap.getAttributeInstance(MOVEMENT_SPEED),
                    "Mastery Athletics Speed Modifier");
            this.applyModifier(athleticsMastery.getSwimModifier(), attributeMap.getAttributeInstance(SWIM_SPEED),
                    "Mastery Swimming Speed Modifier");
        }
    }

    private void applyModifier(double modifierValue, IAttributeInstance attribute, String attributeName) {
        AttributeModifier modifier = attribute.getModifier(this.modifierUid);
        if (modifier != null) {
            attribute.removeModifier(modifier);
        }
        AttributeModifier attributeSpeedModifier = new AttributeModifier(this.modifierUid, attributeName, modifierValue,
                0);
        attribute.applyModifier(attributeSpeedModifier);
    }

}
