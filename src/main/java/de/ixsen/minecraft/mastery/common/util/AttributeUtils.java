package de.ixsen.minecraft.mastery.common.util;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class AttributeUtils {

    public static void applyModifier(double modifierValue, IAttributeInstance attribute, String attributeName,
            UUID modifierUuid) {
        AttributeModifier modifier = attribute.getModifier(modifierUuid);
        if (modifier != null) {
            attribute.removeModifier(modifier);
        }
        AttributeModifier attributeModifier = new AttributeModifier(modifierUuid, attributeName, modifierValue, 0);
        attribute.applyModifier(attributeModifier);
    }
}
