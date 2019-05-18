package mastery.capability;

import mastery.MasteryMod;
import mastery.capability.player.IMastery;
import mastery.capability.player.MasteryProvider;
import mastery.capability.world.BlockInfoProvider;
import mastery.common.util.MasteryUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Granis on 16/03/2018.
 */
public class CapabilityHandler {

    public static final ResourceLocation MASTERY_CAPABILITY = new ResourceLocation(MasteryMod.MOD_ID, "mastery");

    @SubscribeEvent
    public void attachCapabilityToPlayer(AttachCapabilitiesEvent<Entity> entityEvent) {
        if (entityEvent.getObject() instanceof EntityPlayer) {
            entityEvent.addCapability(MASTERY_CAPABILITY, new MasteryProvider());
        }
    }

    @SubscribeEvent
    public void attachCapabilityToWorld(AttachCapabilitiesEvent<World> worldEvent) {
        if (worldEvent.getObject() != null) {
            worldEvent.addCapability(MASTERY_CAPABILITY, new BlockInfoProvider());
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
