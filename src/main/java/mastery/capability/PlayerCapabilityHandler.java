package mastery.capability;

import mastery.MasteryMod;
import mastery.util.MasteryUtils;
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
    public void attachCapability(AttachCapabilitiesEvent<?> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(MASTERY_CAPABILITY, new MasteryProvider());
        }
    }

    @SubscribeEvent
    public void persistAcrossDeath(PlayerEvent.Clone respawnEvent) {
        IMastery originalMastery = MasteryUtils.getMasteries(respawnEvent.getOriginal());
        MasteryUtils.getMasteries(respawnEvent.getEntityPlayer()).setMasteries(originalMastery.getMasteries());
    }
}
