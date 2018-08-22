package mastery.capability;

import mastery.MasteryMod;
import mastery.util.MasteryUtils;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Granis on 16/03/2018.
 */
public class PlayerCapabilityHandler {

    public static final ResourceLocation MASTERY_CAPABILITY = new ResourceLocation(MasteryMod.modid, "mastery");

    @SubscribeEvent
    public void attachCapability(@SuppressWarnings("rawtypes") AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(MASTERY_CAPABILITY, new MasteryProvider());
        }
    }

    @SubscribeEvent
    public void persistAcrossDeath(PlayerEvent.Clone respawnEvent) {
        EntityPlayer originalPlayer = respawnEvent.getOriginal();
        EntityPlayer newPlayer = respawnEvent.getEntityPlayer();

        AbstractAttributeMap attributeMap = newPlayer.getAttributeMap();
        for (IAttributeInstance attribute : originalPlayer.getAttributeMap().getAllAttributes()) {
            for (AttributeModifier modifier : attribute.getModifiers()) {
                attributeMap.getAttributeInstance(attribute.getAttribute()).applyModifier(modifier);
            }
        }
        IMastery originalMastery = MasteryUtils.getMasteries(originalPlayer);
        MasteryUtils.getMasteries(newPlayer).setMasteries(originalMastery.getMasteries());
    }
}
