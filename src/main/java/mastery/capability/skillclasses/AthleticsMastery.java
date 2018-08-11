package mastery.capability.skillclasses;

import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;

public class AthleticsMastery extends MasteryClass {

    private final UUID modifierUid = UUID.fromString("839fbc52-388f-4729-b2ce-5f45cdb72cdc");

    public AthleticsMastery() {
        this.name = "Athletics";
    }

    @Override
    public MasterySpec getSkillClass() {
        return MasterySpec.ATHLETICS;
    }

    private double getSpeedModifier() {
        return Math.min(this.getLevel(), 1D);
    }

    public void reApplyAttributeSpeedModifier(EntityPlayer player) {
        IAttributeInstance attribute = player.getAttributeMap().getAttributeInstance(MOVEMENT_SPEED);
        AttributeModifier modifier = attribute.getModifier(this.modifierUid);
        if (modifier != null) {
            attribute.removeModifier(modifier);
        }
        AttributeModifier attributeSpeedModifier = new AttributeModifier(this.modifierUid,
                "Mastery Athletics Seed Modifier", this.getSpeedModifier(), 0);
        attribute.applyModifier(attributeSpeedModifier);
        System.out.println("LEVEL UP!" + attributeSpeedModifier.getAmount());
    }
}
