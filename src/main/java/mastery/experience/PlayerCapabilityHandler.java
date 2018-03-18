package mastery.experience;

import mastery.MasteryMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Granis on 16/03/2018.
 */
public class PlayerCapabilityHandler {

    public static final ResourceLocation MASTERY_CAPABILITY = new ResourceLocation(MasteryMod.modId, "mastery");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(MASTERY_CAPABILITY, new MasteryProvider());

        }
    }

    @SubscribeEvent
    public void persistAcrossDeath(PlayerEvent.Clone respawnEvent) {
        IMastery originalMastery = respawnEvent.getOriginal().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
        respawnEvent.getEntityPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null).setMasteries(originalMastery.getMasteries());

    }
}
